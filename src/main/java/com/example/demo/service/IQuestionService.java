package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Question;

public interface IQuestionService {
	 public ResponseEntity<List<Question>> getAllQuestions();
	 public ResponseEntity<List<Question>> getQuestionsByCategory(String category);
	 public ResponseEntity<String> addQuestion(Question question);
	 
	 
	 
	 
}
