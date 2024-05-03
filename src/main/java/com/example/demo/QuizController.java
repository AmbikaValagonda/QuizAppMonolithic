package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Response;
import com.example.demo.service.IQuizService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	IQuizService quizService;
	
	@PostMapping("create/{category}/{numQ}/{title}")
	//@PostMapping("create")
	//public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ,@RequestParam String title){
	public ResponseEntity<String> createQuiz(@PathVariable String category, @PathVariable int numQ,@PathVariable String title){
		return quizService.createQuiz(category, numQ, title);
		
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> createQuiz(@PathVariable int id){
		
		return quizService.getQuizQuestions(id);
		
		
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {
		return quizService.calculateResult(id, response);
		
	}
}
