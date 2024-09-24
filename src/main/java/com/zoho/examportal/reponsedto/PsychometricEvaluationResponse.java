package com.zoho.examportal.reponsedto;

import com.zoho.examportal.entity.LogicalReasoningOptions;
import com.zoho.examportal.entity.LogicalReasoningQuestions;
import com.zoho.examportal.entity.PsychometricEvaluationOptions;
import com.zoho.examportal.entity.PsychometricEvaluationQuestions;
import lombok.Data;

import java.util.List;

@Data
public class PsychometricEvaluationResponse {

    private PsychometricEvaluationQuestions question;
    private List<PsychometricEvaluationOptions> options;

    public PsychometricEvaluationResponse(PsychometricEvaluationQuestions question, List<PsychometricEvaluationOptions> options){
        this.question = question;
        this.options = options;
    }
}

