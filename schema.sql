CREATE DATABASE IF NOT EXISTS exam_survey_db;
USE exam_survey_db;

-- 1. Tablo: Kullanıcılar
CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL
    );

-- 2. Tablo: Sınavlar ve Anketler
CREATE TABLE IF NOT EXISTS Assessments (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           title VARCHAR(200) NOT NULL,
    type VARCHAR(20) NOT NULL, -- 'EXAM' veya 'SURVEY'
    created_by INT,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL
    );

-- 3. Tablo: Sorular (Sınav/Anket ile 1-N İlişkili)
CREATE TABLE IF NOT EXISTS questions (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         assessment_id INT NOT NULL,
                                         question_text TEXT NOT NULL,
                                         correct_answer VARCHAR(250),
    FOREIGN KEY (assessment_id) REFERENCES Assessments(id) ON DELETE CASCADE
    );