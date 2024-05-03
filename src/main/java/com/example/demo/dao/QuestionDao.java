package com.example.demo.dao;

import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;
@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

    List<Question> findByCategory(String category);

   
    @Query(value = "SELECT * FROM question q Where category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findByTopic(String category, Integer numQ);
    //List<Question> findByCategoryOrderById(String c);

}
