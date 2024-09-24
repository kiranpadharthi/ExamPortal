-- Helps to create the database for our exam portal application
CREATE DATABASE IF NOT EXISTS zohoExamPortalDB;

-- Switching to that database
USE zohoExamPortalDB;

-- Creating the candidate table to store candidate details
CREATE TABLE IF NOT EXISTS candidate (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    PRIMARY KEY (id),
    UNIQUE (email)
);

-- Creating the table to store the Logical Reasoning questions

CREATE TABLE logical_reasoning_questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    question_text TEXT NOT NULL,
    difficulty_level INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fetched_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

-- Creating the table to store the Logical Reasoning Options

CREATE TABLE logical_reasoning_options (
    option_id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    option_text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (question_id) REFERENCES logical_reasoning_questions(question_id)
);

-- Creating a table to store the Logical Reasoning Results

CREATE TABLE logical_reasoning_results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    candidate_id INT NOT NULL,
    score INT DEFAULT 0,
    attempted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (candidate_id) REFERENCES candidates(id),
    UNIQUE (candidate_id)
);


