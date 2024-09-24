package com.zoho.examportal.controller;

import com.zoho.examportal.entity.Candidate;
import com.zoho.examportal.entity.PsychometricEvaluationOptions;
import com.zoho.examportal.entity.PsychometricEvaluationQuestions;
import com.zoho.examportal.entity.PsychometricEvaluationResults;
import com.zoho.examportal.reponsedto.PsychometricEvaluationResponse;
import com.zoho.examportal.reponsedto.PsychometricEvaluationResultResponse;
import com.zoho.examportal.requestdto.PsychometricEvaluationRequest;
import com.zoho.examportal.requestdto.PsychometricEvaluationResultRequest;
import com.zoho.examportal.service.CandidateService;
import com.zoho.examportal.service.PsychometricEvaluationResService;
import com.zoho.examportal.service.PsychometricEvaluationService;
import com.zoho.examportal.utils.DifficultyFinder;
import com.zoho.examportal.utils.ExamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/psychometric-evaluation")
public class PsychometricEvaluationController {

    @Autowired
    private PsychometricEvaluationService PsychometricEvaluationService;

    @Autowired
    private PsychometricEvaluationResService PsychometricEvaluationResService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private DifficultyFinder difficultyFinder;
    @PostMapping("/start")
    public ResponseEntity<Map<String,Object>> startTest(@RequestParam("id") int candidateId){

        Optional<Candidate> candidate = candidateService.getCandidateById(candidateId);

        //Checking if candidate registered or not
        if(candidate.isEmpty()){
            return handleError("Candidate is not registered");
        }

        // Check whether candidate exists and submitted the test or not
        Optional<PsychometricEvaluationResults> entry = PsychometricEvaluationResService.findByCandidateId(candidateId);
        if(entry.isEmpty()){
            //Save an entry in results table
            PsychometricEvaluationResults newEntry = new PsychometricEvaluationResults(candidate.get(),0, ExamConstants.False,LocalDateTime.now());
            PsychometricEvaluationResService.saveResult(newEntry);
        }
        else{
            if(entry.get().getAttempted()) {
                return handleError("Candidate already submitted the test");
            }
        }

        Map<String,Object> res = new HashMap<String,Object>();
        res.put("Candidate id",candidateId);
        res.put("Candidate name", candidate.get().getName());
        res.put("Score",0);
        res.put("Test status", "Test started successfully");
        res.put("Questions Attempted",0);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/start/question")
    public ResponseEntity<PsychometricEvaluationResponse> getQuestion(@Validated @RequestBody PsychometricEvaluationRequest PsychometricEvaluationRequest) {

        // Fetching the current score
        Optional<PsychometricEvaluationResults> currScore = PsychometricEvaluationResService.findByCandidateId(PsychometricEvaluationRequest.getCandidateId());

        // Setting up the difficulty level based on score
        int level = difficultyFinder.getLevel(currScore.get().getScore());
        LocalDateTime tenMinutesBefore = LocalDateTime.now().minusMinutes(10);

        //Fetching the question from database based on level and fetched time
        PsychometricEvaluationQuestions question = PsychometricEvaluationService.getQuestion(level,tenMinutesBefore);
        question.setFetchedAt(LocalDateTime.now());

        //Updating the fetched time so that same question won't displayed to another candidate
        PsychometricEvaluationService.saveQuestion(question);

        //Extracting the options based on questions
        List<PsychometricEvaluationOptions> options = PsychometricEvaluationService.getOptions(question.getId());

        //Forming the response
        PsychometricEvaluationResponse response = new PsychometricEvaluationResponse(question,options);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/start/question/submit")
    public ResponseEntity<PsychometricEvaluationResultResponse> checkAnswer(@Validated @RequestBody PsychometricEvaluationResultRequest PsychometricEvaluationResultRequest){

        String result;
        // fetching the current score
        Optional<PsychometricEvaluationResults> score = PsychometricEvaluationResService.findByCandidateId(PsychometricEvaluationResultRequest.getCandidateId());
        int difficultyLevel = PsychometricEvaluationService.getQuestionLevel(PsychometricEvaluationResultRequest.getQuestionId());
        int updatedScore = 0;

        if(score.isPresent()){
            updatedScore = score.get().getScore();
        }

        if(PsychometricEvaluationService.getAnswer(PsychometricEvaluationResultRequest.getOptionId())){

            // Updating the score if the selected option is correct
            updatedScore+=1+difficultyLevel;
            score.get().setScore(updatedScore);

            PsychometricEvaluationResService.saveResult(score.get());

            result = "Correct option selected";
        }
        else{
            result = "In correct option selected";
        }

        PsychometricEvaluationResultResponse response = new PsychometricEvaluationResultResponse(PsychometricEvaluationResultRequest.getCandidateId(),result,updatedScore);
        return new ResponseEntity<PsychometricEvaluationResultResponse>(response,HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String,Object>> submitAll( @RequestParam("candidateId") Integer candidateId){
        // Fetching the total score
        Optional<PsychometricEvaluationResults> score = PsychometricEvaluationResService.findByCandidateId(candidateId);
        int finalScore = 0;
        if(score.isPresent()){
            finalScore = score.get().getScore();
            score.get().setAttempted(ExamConstants.True);
        }

        String status;
        Map<String,Object> response = new HashMap<String,Object>();
        response.put("candidate id",candidateId);
        response.put("candidate score",finalScore);
        response.put("Maximum score",25);

        if(finalScore>=12){
            status  = "Passed";
        }
        else{
            status = "failed";
        }
        response.put("Status",status);

        // Making the attempted to true in Logical Reasoning Results to avoid duplications of entries
        PsychometricEvaluationResService.saveResult(score.get());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    public ResponseEntity<Map<String, Object>> handleError(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", message);
        response.put("timestamp", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

