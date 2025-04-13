package com.zoltan.bloggingwebapi.controllers;

import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.exceptions.BadRequestException;
import com.zoltan.bloggingwebapi.payloads.UserDTO;
import com.zoltan.bloggingwebapi.payloads.validationGroups.Update;
import com.zoltan.bloggingwebapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/utenti")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public User updateProfile(@AuthenticationPrincipal User currentUser, @RequestBody @Validated(Update.class) UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload? " + message);
        }
        return userService.updateUser(currentUser.getId(), body);
    }

    @PutMapping("/users/{id}")
    public User updateProfile(
            @PathVariable UUID id,
            @RequestBody @Validated(Update.class) UserDTO body,
            BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload? " + message);
        }

        return userService.updateUser(id, body);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deleteMe")
    public void removeProfile(@AuthenticationPrincipal User currentUser) {
        userService.deleteUser(currentUser.getId());
    }

    @DeleteMapping("/users/{id}")
    public void removeProfile(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        User found = userService.findById(id);
        return ResponseEntity.ok(found);
    }

    @PatchMapping("/changeRole/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> changeRole(@PathVariable UUID id) {
        User found = userService.changeAuthority(id);
        return ResponseEntity.ok(found);
    }


    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.getAll();
    }

}
