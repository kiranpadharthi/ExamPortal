package com.zoho.examportal.reponsedto;

import com.zoho.examportal.entity.LogicalReasoningOptions;
import com.zoho.examportal.entity.LogicalReasoningQuestions;
import lombok.Data;

import java.util.List;

@Data
public class LogicalReasoningResponse {

    private LogicalReasoningQuestions question;
    private List<LogicalReasoningOptions> options;

    public LogicalReasoningResponse(LogicalReasoningQuestions question, List<LogicalReasoningOptions> options){
        this.question = question;
        this.options = options;
    }
}
