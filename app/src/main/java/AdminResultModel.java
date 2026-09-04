package com.example.onlinevotingsystem;

public class AdminResultModel {

    String candidateId;
    String candidateName;
    String autoVote;

    public AdminResultModel(
            String candidateId,
            String candidateName,
            String autoVote
    ){
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.autoVote = autoVote;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getAutoVote() {
        return autoVote;
    }
}