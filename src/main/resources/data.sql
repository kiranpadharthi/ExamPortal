-- Inserting some questions related to logical reasoning

INSERT INTO logical_reasoning_questions (question_text, difficulty_level, fetched_at) VALUES
('What is the capital of France?', 0, NOW()),
('What is the largest planet in our solar system?', 1, NOW()),
('What is 2 + 2?', 0, NOW()),
('Who wrote "To Kill a Mockingbird"?', 2, NOW()),
('What is the chemical symbol for water?', 0, NOW()),
('Which element has the atomic number 1?', 1, NOW()),
('What is the boiling point of water?', 0, NOW()),
('Who painted the Mona Lisa?', 1, NOW()),
('What is the largest ocean on Earth?', 2, NOW()),
('Which gas is most abundant in Earth’s atmosphere?', 0, NOW()),
('What is the speed of light?', 2, NOW()),
('What is the main ingredient in guacamole?', 1, NOW()),
('What is the powerhouse of the cell?', 0, NOW()),
('What is the currency used in Japan?', 1, NOW()),
('Who was the first President of the United States?', 0, NOW()),
('What is the largest mammal?', 2, NOW()),
('What is the most common gas in the Earth’s atmosphere?', 1, NOW()),
('What is the main language spoken in Brazil?', 0, NOW()),
('Who discovered penicillin?', 2, NOW()),
('What is the smallest unit of life?', 1, NOW()),
('What is the largest organ in the human body?', 0, NOW());

-- Inserting options for those questions

INSERT INTO logical_reasoning_options (question_id, option_text, is_correct) VALUES
(1, 'Paris', TRUE),
(1, 'London', FALSE),
(1, 'Rome', FALSE),
(1, 'Berlin', FALSE),

(2, 'Earth', FALSE),
(2, 'Mars', FALSE),
(2, 'Jupiter', TRUE),
(2, 'Saturn', FALSE),

(3, '3', FALSE),
(3, '4', TRUE),
(3, '5', FALSE),
(3, '6', FALSE),

(4, 'Harper Lee', TRUE),
(4, 'Mark Twain', FALSE),
(4, 'Ernest Hemingway', FALSE),
(4, 'F. Scott Fitzgerald', FALSE),

(5, 'H2O', TRUE),
(5, 'O2', FALSE),
(5, 'CO2', FALSE),
(5, 'NaCl', FALSE),

(6, 'Hydrogen', TRUE),
(6, 'Helium', FALSE),
(6, 'Carbon', FALSE),
(6, 'Oxygen', FALSE),

(7, '50°C', FALSE),
(7, '75°C', FALSE),
(7, '100°C', TRUE),
(7, '150°C', FALSE),

(8, 'Leonardo da Vinci', TRUE),
(8, 'Vincent van Gogh', FALSE),
(8, 'Pablo Picasso', FALSE),
(8, 'Claude Monet', FALSE),

(9, 'Atlantic Ocean', FALSE),
(9, 'Indian Ocean', FALSE),
(9, 'Southern Ocean', FALSE),
(9, 'Pacific Ocean', TRUE),

(10, 'Oxygen', FALSE),
(10, 'Carbon Dioxide', FALSE),
(10, 'Nitrogen', TRUE),
(10, 'Argon', FALSE),

(11, '300,000 km/s', TRUE),
(11, '150,000 km/s', FALSE),
(11, '600,000 km/s', FALSE),
(11, '1,000,000 km/s', FALSE),

(12, 'Tomato', FALSE),
(12, 'Avocado', TRUE),
(12, 'Pepper', FALSE),
(12, 'Onion', FALSE),

(13, 'Nucleus', FALSE),
(13, 'Mitochondria', TRUE),
(13, 'Ribosome', FALSE),
(13, 'Endoplasmic Reticulum', FALSE),

(14, 'Yuan', FALSE),
(14, 'Dollar', FALSE),
(14, 'Yen', TRUE),
(14, 'Won', FALSE),

(15, 'Abraham Lincoln', FALSE),
(15, 'George Washington', TRUE),
(15, 'Thomas Jefferson', FALSE),
(15, 'John Adams', FALSE),

(16, 'Elephant', FALSE),
(16, 'Blue Whale', TRUE),
(16, 'Giraffe', FALSE),
(16, 'Rhino', FALSE),

(17, 'Oxygen', FALSE),
(17, 'Hydrogen', FALSE),
(17, 'Carbon Dioxide', FALSE),
(17, 'Nitrogen', TRUE),

(18, 'Spanish', FALSE),
(18, 'Portuguese', TRUE),
(18, 'English', FALSE),
(18, 'French', FALSE),

(19, 'Marie Curie', FALSE),
(19, 'Alexander Fleming', TRUE),
(19, 'Louis Pasteur', FALSE),
(19, 'Albert Einstein', FALSE),

(20, 'Tissue', FALSE),
(20, 'Organ', FALSE),
(20, 'Cell', TRUE),
(20, 'Molecule', FALSE),

(21, 'Heart', FALSE),
(21, 'Liver', FALSE),
(21, 'Skin', TRUE),
(21, 'Lung', FALSE);

