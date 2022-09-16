package com.ead.enums;

public enum ErrorType {

    METHOD_ARG_NOT_VALID_ERROR("Argumento ou valor(es) inválido(s)."),
    UNEXPECTED_ERROR("Um erro inesperado ocorreu, por favor, verifique os logs."),
    SERVICE_UNAVAILABLE("O serviço da api de course está indisponível no momento, tente novamente mais tarde."),
    USER_NOT_FOUND("Usuário não encontrado."),
    USER_BLOCKED("Usuário esta bloqueado."),
    LOGIN_IS_ALREADY_BEING_USED("Login já esta sendo usado por outro usuário."),
    PASSWORD_REQUIRED("Senha é obrigatório."),
    CONFIRM_PASSWORD_REQUIRED("Confimação de senha é obrigatório."),
    PASSWORD_DIFFERENT_CONFIRM_PASSWORD_REQUIRED("Senha e confirmação de senha não são iguais."),
    EMAIL_IS_ALREADY_BEING_USED("E-Mail já esta sendo usado por outro usuário."),
    SUBSCRIPTION_USER_COURSE_AND_EXISTS_ERROR("Inscrição do usuário ja existe nesse curso."),
    USER_COURSE_BY_COURSE_ID_NOT_FOUND("Curso do usuario não encontrado.");

    private String error;

    ErrorType(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.error;
    }
}
