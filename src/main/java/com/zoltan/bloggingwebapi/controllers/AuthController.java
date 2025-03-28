package com.zoltan.bloggingwebapi.controllers;

import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.exceptions.BadRequestException;
import com.zoltan.bloggingwebapi.payloads.LoginDTO;
import com.zoltan.bloggingwebapi.payloads.UserDTO;
import com.zoltan.bloggingwebapi.payloads.UserLoginResponseDTO;
import com.zoltan.bloggingwebapi.payloads.validationGroups.Create;
import com.zoltan.bloggingwebapi.services.SecurityService;
import com.zoltan.bloggingwebapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private SecurityService ss;

    @Autowired
    private UserService us;

    @PostMapping("/login")
    public UserLoginResponseDTO LoginResponseDTO(@RequestBody @Validated LoginDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return new UserLoginResponseDTO(this.ss.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated(Create.class) UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.us.save(body);
    }


}
