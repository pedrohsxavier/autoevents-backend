package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public static final Long expirationTime = 3600000L;
    private String key = "AutoEvents Secret Key";

    public String generateToken(Usuario usuario){
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(usuario.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
