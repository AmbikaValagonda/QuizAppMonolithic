package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Response;

public interface IQuizService {
	public ResponseEntity<String> createQuiz(String category, int numQ, String title);

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id);

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response);
	
}
