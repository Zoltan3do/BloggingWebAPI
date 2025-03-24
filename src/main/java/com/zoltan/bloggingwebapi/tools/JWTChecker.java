package com.zoltan.bloggingwebapi.tools;

import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.exceptions.UnauthorizedException;
import com.zoltan.bloggingwebapi.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class JWTChecker extends OncePerRequestFilter {

    @Autowired
    private JWT jwt;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire token nell' Authorization Header nel formato corretto !");
        String accessToken = authorizationHeader.split(" ")[1];
        jwt.verifyToken(accessToken);
        UUID idUtente = UUID.fromString(jwt.getIdFromToken(accessToken));
        User utenteCorrente = this.userService.findById(idUtente);
        // Authentication authentication = new UPAT...
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utenteCorrente, null, utenteCorrente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher apm = new AntPathMatcher();
        List<String> paths = Arrays.asList("/auth/**","/swagger-ui/**","/v3/api-docs/**");
        return paths.stream().anyMatch(path -> apm.match(path, request.getServletPath()));
    }



}
