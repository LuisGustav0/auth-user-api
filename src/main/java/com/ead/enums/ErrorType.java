package com.ead.enums;

public enum ErrorType {

    USER_NOT_FOUND("Usuário não encontrado."),
    LOGIN_IS_ALREADY_BEING_USED("Login já esta sendo usado por outro usuário."),
    PASSWORD_REQUIRED("Senha é obrigatório."),
    CONFIRM_PASSWORD_REQUIRED("Confimação de senha é obrigatório."),
    PASSWORD_DIFFERENT_CONFIRM_PASSWORD_REQUIRED("Senha e confirmação de senha não são iguais."),
    EMAIL_IS_ALREADY_BEING_USED("E-Mail já esta sendo usado por outro usuário.");

    private String error;

    ErrorType(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.error;
    }
}
