package br.com.beckhauser.ControleDeEstoque.enterprise;

public class ValidationException extends RuntimeException{
    public ValidationException (String message) {
        super(message);
    }
}
