package com.zoho.examportal.controller;

import com.zoho.examportal.entity.Candidate;
import com.zoho.examportal.entity.LogicalReasoningOptions;
import com.zoho.examportal.entity.LogicalReasoningQuestions;
import com.zoho.examportal.entity.LogicalReasoningResults;
import com.zoho.examportal.reponsedto.LogicalReasoningResponse;
import com.zoho.examportal.reponsedto.LogicalReasoningResultResponse;
import com.zoho.examportal.repository.CandidateRepo;
import com.zoho.examportal.requestdto.LogicalReasoningRequest;
import com.zoho.examportal.requestdto.LogicalReasoningResultRequest;
import com.zoho.examportal.service.CandidateService;
import com.zoho.examportal.service.LogicalReasoningResService;
import com.zoho.examportal.service.LogicalReasoningService;
import com.zoho.examportal.utils.DifficultyFinder;
import com.zoho.examportal.utils.ExamConstants;
import com.zoho.examportal.utils.RandomNumber;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.zoho.examportal.utils.ExamConstants;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/logical-reasoning")
public class LogicalReasoningController {

    @Autowired
    private LogicalReasoningService logicalReasoningService;

    @Autowired
    private LogicalReasoningResService logicalReasoningResService;

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
        Optional<LogicalReasoningResults> entry = logicalReasoningResService.findByCandidateId(candidateId);
        if(entry.isEmpty()){
            //Save an entry in results table
            LogicalReasoningResults newEntry = new LogicalReasoningResults(candidate.get(),0, ExamConstants.False,LocalDateTime.now());
            logicalReasoningResService.saveResult(newEntry);
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
    public ResponseEntity<LogicalReasoningResponse> getQuestion(@Validated @RequestBody LogicalReasoningRequest logicalReasoningRequest) {

        // Fetching the current score
        Optional<LogicalReasoningResults> currScore = logicalReasoningResService.findByCandidateId(logicalReasoningRequest.getCandidateId());

        // Setting up the difficulty level based on score
        int level = difficultyFinder.getLevel(currScore.get().getScore());
        LocalDateTime tenMinutesBefore = LocalDateTime.now().minusMinutes(10);

        //Fetching the question from database based on level and fetched time
        LogicalReasoningQuestions question = logicalReasoningService.getQuestion(level,tenMinutesBefore);
        question.setFetchedAt(LocalDateTime.now());

        //Updating the fetched time so that same question won't displayed to another candidate
        logicalReasoningService.saveQuestion(question);

        //Extracting the options based on questions
        List<LogicalReasoningOptions> options = logicalReasoningService.getOptions(question.getId());

        //Forming the response
        LogicalReasoningResponse response = new LogicalReasoningResponse(question,options);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/start/question/submit")
    public ResponseEntity<LogicalReasoningResultResponse> checkAnswer(@Validated @RequestBody LogicalReasoningResultRequest logicalReasoningResultRequest){

        String result;
        // fetching the current score
        Optional<LogicalReasoningResults> score = logicalReasoningResService.findByCandidateId(logicalReasoningResultRequest.getCandidateId());
        int difficultyLevel = logicalReasoningService.getQuestionLevel(logicalReasoningResultRequest.getQuestionId());
        int updatedScore = 0;

        if(score.isPresent()){
            updatedScore = score.get().getScore();
        }

        if(logicalReasoningService.getAnswer(logicalReasoningResultRequest.getOptionId())){

            // Updating the score if the selected option is correct
            updatedScore+=1+difficultyLevel;
            score.get().setScore(updatedScore);

            logicalReasoningResService.saveResult(score.get());

            result = "Correct option selected";
        }
        else{
            result = "In correct option selected";
        }

        LogicalReasoningResultResponse response = new LogicalReasoningResultResponse(logicalReasoningResultRequest.getCandidateId(),result,updatedScore);
        return new ResponseEntity<LogicalReasoningResultResponse>(response,HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String,Object>> submitAll( @RequestParam("candidateId") Integer candidateId){
        // Fetching the total score
        Optional<LogicalReasoningResults> score = logicalReasoningResService.findByCandidateId(candidateId);
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
        logicalReasoningResService.saveResult(score.get());
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

