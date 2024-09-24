package com.zoho.examportal.service;

import com.zoho.examportal.entity.LogicalReasoningOptions;
import com.zoho.examportal.entity.LogicalReasoningQuestions;
import com.zoho.examportal.repository.LogicalReasoningORepo;
import com.zoho.examportal.repository.LogicalReasoningQRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LogicalReasoningService {
    @Autowired
    private LogicalReasoningQRepo logicalReasoningQRepo;

    @Autowired
    private LogicalReasoningORepo logicalReasoningORepo;

    public LogicalReasoningQuestions getQuestion(int level, LocalDateTime localDateTime) {
        return logicalReasoningQRepo.getQuestionByLevelAndTime(level,localDateTime).get(0);
        //return new LogicalReasoningQuestions();
    }
    public List<LogicalReasoningQuestions> getAllQuestions() {
        return logicalReasoningQRepo.findAll();
    }

    public List<LogicalReasoningOptions> getOptions(int questionId){
        return logicalReasoningORepo.findByQuestionId(questionId);
    }

    public boolean getAnswer(int optionId){
        Optional<Boolean> result = logicalReasoningORepo.getAnswer(optionId);
        return result.orElse(false);
    }

    public void  saveQuestion(LogicalReasoningQuestions question){
        logicalReasoningQRepo.save(question);
    }

    public int getQuestionLevel(Integer questionId) {
        return logicalReasoningQRepo.getLevel(questionId);
    }
}
