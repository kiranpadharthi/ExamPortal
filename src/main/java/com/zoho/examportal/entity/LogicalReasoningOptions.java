package com.zoho.examportal.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "logical_reasoning_options")
public class LogicalReasoningOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Integer optionId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private LogicalReasoningQuestions question;

    @Column(name = "option_text", nullable = false)
    private String optionText;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;

}

