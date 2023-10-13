package com.example.motorbikedrivinglicensequiz.repositories;

import com.example.motorbikedrivinglicensequiz.domains.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepositoy extends JpaRepository<Question, Long>,
    JpaSpecificationExecutor<Question> {

  @Query(value = "select * from question where test_number = :randomNumber", nativeQuery = true)
  List<Question> getAllByTestNumber(Integer randomNumber);

  @Query(value = "select distinct test_number from question", nativeQuery = true)
  List<Integer> getAllTestNumber();
}
