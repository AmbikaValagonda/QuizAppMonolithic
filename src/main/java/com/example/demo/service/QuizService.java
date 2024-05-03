package com.example.demo.service;


import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;

@Service
public class QuizService implements IQuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired 
	QuestionDao questionDao;
	
		
	@Override
	public ResponseEntity<String> createQuiz(String c, int n, String title) {
		// TODO Auto-generated method stub
		System.out.println("Before category" + c);
		System.out.println("Before numQ " + n);
		System.out.println("Before title " + title);
		//List<Question> questions = questionDao.findRandomQuestionsByCategory(c, n);
		//List<Question> questions = questionDao.findByCategory(c);
		List<Question> questions = questionDao.findByTopic(c, n);
		System.out.println("After");
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		if(quiz.isPresent()) {
			List<Question> questionsfromDB = quiz.get().getQuestions();
			
			for(Question q:questionsfromDB) {
				QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
				questionsForUser.add(qw);
			}
			
		}
		return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.OK);
		
		
		
	}


	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int rightAnswers = 0;
		int i=0;
		for(Response resp: response)
		{
			if(resp.getResponse().equals(questions.get(i).getRightAnswer())) {
				rightAnswers++;
			}
			i++;
		}
		
		return new ResponseEntity<Integer>(rightAnswers, HttpStatus.OK);
	}

}
