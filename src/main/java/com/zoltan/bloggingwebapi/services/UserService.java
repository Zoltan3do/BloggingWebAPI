package com.zoltan.bloggingwebapi.services;

import com.zoltan.bloggingwebapi.entities.BlogPost;
import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.entities.enums.UserTypes;
import com.zoltan.bloggingwebapi.exceptions.BadRequestException;
import com.zoltan.bloggingwebapi.exceptions.NotFoundException;
import com.zoltan.bloggingwebapi.payloads.UserDTO;
import com.zoltan.bloggingwebapi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder bcrypt;

    public User save(UserDTO payload) {
        this.userRepo.findByEmail(payload.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + payload.email() + "già in uso");
                }
        );
        User user = new User(payload.name(), payload.surname(), payload.email(), payload.birthday(), bcrypt.encode(payload.password()));
        return this.userRepo.save(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Nessun utente registrato con questa email"));
    }

    public User findById(UUID id) {
        return this.userRepo.findById(id).orElseThrow(() -> new NotFoundException("Nessun utente trovato con id: " + id));
    }

    public User updateUser(UUID id, UserDTO body) {
        User utente = this.findById(id);
        if (body.name() != null) utente.setName(body.name());
        if (body.surname() != null) utente.setSurname(body.surname());
        if (body.email() != null) utente.setEmail(body.email());
        if (body.birthday() != null) utente.setBirthday(body.birthday());
        if (body.password() != null) utente.setPassword(bcrypt.encode(body.password()));

        utente.setAvatar("https://ui-avatars.com/api/?name=" + utente.getName() + "+" + utente.getSurname());

        return userRepo.save(utente);
    }

    public void deleteUser(UUID id) {
        User user = this.findById(id);
        userRepo.delete(user);
    }

    public User changeAuthority(UUID id) {
        User found = this.findById(id);

        if (found.getRole().equals(UserTypes.ADMIN)) {
            found.setRole(UserTypes.AUTHOR);
        } else {
            found.setRole(UserTypes.ADMIN);
        }

        return userRepo.save(found);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }
}
