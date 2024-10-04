package br.com.beckhauser.ControleDeEstoque.controller;

import br.com.beckhauser.ControleDeEstoque.infra.security.TokenService;
import br.com.beckhauser.ControleDeEstoque.model.user.AuthenticationDto;
import br.com.beckhauser.ControleDeEstoque.model.user.LoginResponseDTO;
import br.com.beckhauser.ControleDeEstoque.model.user.RegistroDto;
import br.com.beckhauser.ControleDeEstoque.model.user.Usuario;
import br.com.beckhauser.ControleDeEstoque.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistroDto data) {
    if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
    Usuario usuario = new Usuario(data.login(), encryptedPassword,data.role());

    this.repository.save(usuario);
    return ResponseEntity.ok().build();

    }

}

