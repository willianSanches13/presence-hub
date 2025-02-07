package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.config.JwtService;
import com.oficina.presence_hub.dtos.CredenciaisDTO;
import com.oficina.presence_hub.dtos.TokenDTO;
import com.oficina.presence_hub.entities.User;
import com.oficina.presence_hub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public TokenDTO autenticator(@RequestBody CredenciaisDTO credenciais){
        try{
            User usuario =  new User();
            usuario.setLogin(credenciais.getLogin());
            usuario.setPassword(credenciais.getPassword());
            userService.authenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        }catch(UsernameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
