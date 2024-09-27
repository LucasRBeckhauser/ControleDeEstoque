package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "tb_users")
public class Usuario extends EntityId{

    @Column(name = "nome_usuario", nullable = false)
    private String usuario;

    @Column(name = "senha_usuario", nullable = false)
    private String senha;


}
