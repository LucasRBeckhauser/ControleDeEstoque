package br.com.beckhauser.ControleDeEstoque.model.user;

public record RegistroDto(String login, String senha, UserRole role) {
}
