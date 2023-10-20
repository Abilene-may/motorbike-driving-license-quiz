package com.example.motorbikedrivinglicensequiz.repositories;

import com.example.motorbikedrivinglicensequiz.domains.AnswerChoices;
import com.example.motorbikedrivinglicensequiz.models.tests.AnswerChoicesDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerChoicesRepository extends JpaRepository<AnswerChoices, Long>,
    JpaSpecificationExecutor<AnswerChoices> {

  @Query(
      value =
          "select ac.answer_id as answerId, ac.answer_text as answerText, ac.question_id as questionId "
              + ", ac.is_correct as isCorrect from answer_choices as ac\n"
              + "where ac.question_id = :questionId",
      nativeQuery = true)
  List<AnswerChoicesDTO> getAllByQuestionId(Long questionId);

  @Query(
      value =
          "select * from answer_choices where question_id = :questionId",
      nativeQuery = true)
  List<AnswerChoices> findAllByQuestionId(Long questionId);
}
