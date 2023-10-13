package com.example.motorbikedrivinglicensequiz.services;

import com.example.motorbikedrivinglicensequiz.exceptions.QuizException;
import com.example.motorbikedrivinglicensequiz.models.tests.QuestionAndAnswerDisplay;
import java.util.List;

public interface TestService {
  // random 1 đề test có sẵn trong DB
  List<QuestionAndAnswerDisplay> testDisplay() throws QuizException;

}
