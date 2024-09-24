package com.zoho.examportal.reponsedto;


import lombok.Data;

@Data
public class PsychometricEvaluationResultResponse {

    private Integer candidateId;
    private String result;
    private Integer score;

    public PsychometricEvaluationResultResponse(Integer candidateId, String result, Integer score) {
        this.candidateId = candidateId;
        this.result = result;
        this.score = score;
    }
}
