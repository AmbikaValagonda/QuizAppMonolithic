package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.model.Question;

@Service
public class QuestionService implements IQuestionService{
	
	@Autowired
	 QuestionDao questionDao;
	 

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {

        	return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        	
        	
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
          //List<Question> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
    	  //List<Question> questions = questionDao.findByCategory(categoryName);
    	List<Question> questions = questionDao.findByTopic(categoryName, numQuestions);
          return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    

}
