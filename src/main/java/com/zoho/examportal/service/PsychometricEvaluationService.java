package com.zoho.examportal.service;

import com.zoho.examportal.entity.LogicalReasoningOptions;
import com.zoho.examportal.entity.LogicalReasoningQuestions;
import com.zoho.examportal.entity.PsychometricEvaluationOptions;
import com.zoho.examportal.entity.PsychometricEvaluationQuestions;
import com.zoho.examportal.repository.LogicalReasoningORepo;
import com.zoho.examportal.repository.LogicalReasoningQRepo;
import com.zoho.examportal.repository.PsychometricEvaluationORepo;
import com.zoho.examportal.repository.PsychometricEvaluationQRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PsychometricEvaluationService {
    @Autowired
    private PsychometricEvaluationQRepo psychometricEvaluationQRepo;

    @Autowired
    private PsychometricEvaluationORepo psychometricEvaluationORepo;

    public PsychometricEvaluationQuestions getQuestion(int level, LocalDateTime localDateTime) {
        return psychometricEvaluationQRepo.getQuestionByLevelAndTime(level,localDateTime).get(0);
        //return new LogicalReasoningQuestions();
    }
    public List<PsychometricEvaluationQuestions> getAllQuestions() {
        return psychometricEvaluationQRepo.findAll();
    }

    public List<PsychometricEvaluationOptions> getOptions(int questionId){
        return psychometricEvaluationORepo.findByQuestionId(questionId);
    }

    public boolean getAnswer(int optionId){
        Optional<Boolean> result = psychometricEvaluationORepo.getAnswer(optionId);
        return result.orElse(false);
    }

    public void  saveQuestion(PsychometricEvaluationQuestions question){
        psychometricEvaluationQRepo.save(question);
    }

    public int getQuestionLevel(Integer questionId) {
        return psychometricEvaluationQRepo.getLevel(questionId);
    }
}

