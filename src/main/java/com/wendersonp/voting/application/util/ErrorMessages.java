package com.wendersonp.voting.application.util;

public class ErrorMessages {

    public static final String SORT_FIELD_DOESNT_EXIST = "Campo para ordenacao nao existe";
    public static final String CANDIDATE_CANNOT_BE_DELETED = "Candidato já recebeu voto, não pode ser excluído";
    public static final String VOTER_CANNOT_BE_DELETED = "Eleitor já votou, não pode ser excluído";
    public static final String POSITION_ALREADY_EXISTS = "Posição já existe";
    public static final String CANDIDATE_ALREADY_EXISTS = "Candidato já existe";
    public static final String VOTER_ALREADY_EXISTS = "Eleitor já existe";
    public static final String VOTE_ALREADY_EXISTS = "Voto em questão já existe";

    public static final String SECTION_CANNOT_BE_OPENED = "Sessão não pode ser aberta ou já está aberta";
    public static final String SECTION_CANNOT_BE_CLOSED = "Sessão não pode ser fechada ou já está fechada";
    public static final String SECTION_CANNOT_COUNT_VOTES = "Apenas seção fechada pode ser apurada";




    private ErrorMessages() {
    }
}
