package com.example.motorbikedrivinglicensequiz.repositories;

import com.example.motorbikedrivinglicensequiz.domains.Test;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>,
    JpaSpecificationExecutor<Test> {

  @Query(
      value =
          "select count(*) from test where is_correct = 'true'",
      nativeQuery = true)
  int countNumberOfCorrect();

  @Query(
      value =
          "select count(*) from test where is_correct = 'false' or is_correct is null",
      nativeQuery = true)
  int countNumberOfIncorrect();

  @Query(value = "select * from test order by question_id "
  , nativeQuery = true)
  List<Test> findAll();
}
