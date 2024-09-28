package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "tb_users")
public class Usuario extends EntityId{

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha_usuario", nullable = false)
    private String senha;

    @Column (name = "role", nullable = false)
    private String role;


}
