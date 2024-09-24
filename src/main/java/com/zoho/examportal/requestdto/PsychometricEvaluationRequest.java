package com.zoho.examportal.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PsychometricEvaluationRequest {

    @NotNull(message = "Candidate id is required")
    private Integer candidateId;

    @NotNull(message = "Question number is requried")
    private Integer questionNo;

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Integer questionNo) {
        this.questionNo = questionNo;
    }
}

