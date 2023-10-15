package com.example.motorbikedrivinglicensequiz.repositories;

import com.example.motorbikedrivinglicensequiz.domains.TestResults;
import com.example.motorbikedrivinglicensequiz.models.tests.TestResultsDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultsRepository extends JpaRepository<TestResults, Long>,
    JpaSpecificationExecutor<TestResults> {

  @Query(
      value =
          "select count(number_of_correct) as numberOfCorrect, count(number_of_incorrect) as numberOfIncorrect\n"
              + "from test_results",
      nativeQuery = true)
  Optional<TestResultsDTO> countNumberOfCorrectAndIncorrect();
}
