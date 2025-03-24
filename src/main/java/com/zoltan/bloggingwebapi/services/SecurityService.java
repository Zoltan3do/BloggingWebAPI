package com.zoltan.bloggingwebapi.services;

import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.exceptions.UnauthorizedException;
import com.zoltan.bloggingwebapi.payloads.LoginDTO;
import com.zoltan.bloggingwebapi.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWT jwt;

    public String checkCredentialsAndGenerateToken(LoginDTO body){
        User user = this.userService.findByEmail(body.email());
        if(bcrypt.matches(body.password(), user.getEmail())){
            return jwt.createToken(user);
        }else{
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
