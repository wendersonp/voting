package com.wendersonp.voting.application.util;

public class ErrorMessages {

    public static final String SORT_FIELD_DOESNT_EXIST = "Campo para ordenacao nao existe";
    public static final String CANDIDATE_CANNOT_BE_DELETED = "Candidato já recebeu voto, não pode ser excluído";
    public static final String VOTER_CANNOT_BE_DELETED = "Eleitor já votou, não pode ser excluído";
    public static final String POSITION_ALREADY_EXISTS = "Posição já existe";
    public static final String CANDIDATE_ALREADY_EXISTS = "Candidato já existe";
    public static final String VOTER_ALREADY_EXISTS = "Eleitor já existe";
    public static final String VOTE_INVALID = "Voto em questão já existe ou seção foi encerrada";
    public static final String SECTION_CANNOT_BE_OPENED = "Sessão não pode ser aberta ou já está aberta";
    public static final String SECTION_CANNOT_BE_CLOSED = "Sessão não pode ser fechada ou já está fechada";
    public static final String SECTION_CANNOT_COUNT_VOTES = "Apenas seção fechada pode ser apurada";
    public static final String POSITION_NOT_FOUND = "Cargo não foi encontrado";
    public static final String CANDIDATE_NOT_FOUND = "Candidato não foi encontrado";
    public static final String SECTION_NOT_FOUND = "Seção não foi encontrada";
    public static final String VOTER_NOT_FOUND = "Eleitor não foi encontrado";
    public static final String FIELD_CANNOT_HAVE_MORE_THAN_20 = "Campo não pode ser maior que 20 caracteres";
    public static final String FIELD_CANNOT_BE_EMPTY = "Campo não pode estar vazio";
    public static final String FIELD_CANNOT_BE_NULL = "Campo não pode ser nulo";
    public static final String INVALID_REQUEST_BODY = "Corpo da requisição invalido";
    private ErrorMessages() {
    }
}
