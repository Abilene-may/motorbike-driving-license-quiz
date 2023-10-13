package com.example.motorbikedrivinglicensequiz.services.Impl;

import com.example.motorbikedrivinglicensequiz.domains.Question;
import com.example.motorbikedrivinglicensequiz.exceptions.ExceptionUtils;
import com.example.motorbikedrivinglicensequiz.exceptions.QuizException;
import com.example.motorbikedrivinglicensequiz.models.tests.AnswerChoicesDTO;
import com.example.motorbikedrivinglicensequiz.models.tests.QuestionAndAnswerDisplay;
import com.example.motorbikedrivinglicensequiz.repositories.AnswerChoicesRepository;
import com.example.motorbikedrivinglicensequiz.repositories.QuestionRepositoy;
import com.example.motorbikedrivinglicensequiz.services.TestService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
  private final QuestionRepositoy questionRepositoy;
  private final AnswerChoicesRepository answerChoicesRepository;
  public TestServiceImpl(
      @Lazy QuestionRepositoy questionRepositoy,
      @Lazy AnswerChoicesRepository answerChoicesRepository
  ){
    super();
    this.questionRepositoy = questionRepositoy;
    this.answerChoicesRepository = answerChoicesRepository;
  }

  // Hàm show danh sách các câu hỏi của 1 bài test
  @Override
  public List<QuestionAndAnswerDisplay> testDisplay() throws QuizException {
    // Lấy danh sách các đề bài có trong DB. VD: đề 1, đề 2,...
    List<Integer> testNumberList = questionRepositoy.getAllTestNumber();
    if(testNumberList.isEmpty()){
      throw new QuizException(
          ExceptionUtils.TEST_NUMBER_IS_NOT_AVAILABLE,
          ExceptionUtils.messages.get(ExceptionUtils.TEST_NUMBER_IS_NOT_AVAILABLE));
    }
    // random đề có trong DB
    Random random = new Random();
    int randomIndex = random.nextInt(testNumberList.size());
    Integer randomNumber = testNumberList.get(randomIndex);

    List<QuestionAndAnswerDisplay> questionAndAnswerDisplay = new ArrayList<>();
    // Lấy thông tin DS câu hỏi với đề tương ứng
    List<Question> questionList = questionRepositoy.getAllByTestNumber(randomNumber);
    if(questionList.isEmpty()){
      throw new QuizException(
          ExceptionUtils.QUESTION_IS_NOT_AVAILABLE,
          ExceptionUtils.messages.get(ExceptionUtils.QUESTION_IS_NOT_AVAILABLE));
    }
    // Lấy thông tin các lựa chọn của câu trả lời cho câu hỏi
    int i = 0;
    for (Question question : questionList) {
      List<AnswerChoicesDTO> answerChoicesDTOList =
          answerChoicesRepository.getAllByQuestionId(question.getQuestionId());
      QuestionAndAnswerDisplay display =
          QuestionAndAnswerDisplay.builder()
              .question(question)
              .answerChoicesDTOList(answerChoicesDTOList)
              .build();
      questionAndAnswerDisplay.add(i, display);
      i++;
    }

    return questionAndAnswerDisplay;
  }
}
