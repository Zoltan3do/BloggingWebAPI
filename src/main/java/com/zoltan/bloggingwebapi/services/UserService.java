package com.zoltan.bloggingwebapi.services;

import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.exceptions.BadRequestException;
import com.zoltan.bloggingwebapi.payloads.UserDTO;
import com.zoltan.bloggingwebapi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User save(UserDTO payload) {
        this.userRepo.findByEmail(payload.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + payload.email() + "già in uso");
                }
        );
        User user = new User(payload.name(), payload.surname(), payload.email(), payload.birthday(), payload.password());
        return this.userRepo.save(user);
    }
}
