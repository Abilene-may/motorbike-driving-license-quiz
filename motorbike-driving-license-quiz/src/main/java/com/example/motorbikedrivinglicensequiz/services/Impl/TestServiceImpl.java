package com.example.motorbikedrivinglicensequiz.services.Impl;

import com.example.motorbikedrivinglicensequiz.domains.AnswerChoices;
import com.example.motorbikedrivinglicensequiz.domains.Question;
import com.example.motorbikedrivinglicensequiz.domains.Test;
import com.example.motorbikedrivinglicensequiz.domains.TestResults;
import com.example.motorbikedrivinglicensequiz.exceptions.ExceptionUtils;
import com.example.motorbikedrivinglicensequiz.exceptions.QuizException;
import com.example.motorbikedrivinglicensequiz.models.tests.AnswerChoicesDTO;
import com.example.motorbikedrivinglicensequiz.models.tests.QuestionAndAnswerDisplay;
import com.example.motorbikedrivinglicensequiz.models.tests.ReviewAndResultsTable;
import com.example.motorbikedrivinglicensequiz.models.tests.ReviewTest;
import com.example.motorbikedrivinglicensequiz.models.tests.TestReqDTO;
import com.example.motorbikedrivinglicensequiz.repositories.AnswerChoicesRepository;
import com.example.motorbikedrivinglicensequiz.repositories.QuestionRepositoy;
import com.example.motorbikedrivinglicensequiz.repositories.TestRepository;
import com.example.motorbikedrivinglicensequiz.repositories.TestResultsRepository;
import com.example.motorbikedrivinglicensequiz.services.TestService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {
  private final QuestionRepositoy questionRepositoy;
  private final AnswerChoicesRepository answerChoicesRepository;
  private final TestRepository testRepository;
  private final TestResultsRepository testResultsRepository;
  public TestServiceImpl(
      @Lazy QuestionRepositoy questionRepositoy,
      @Lazy AnswerChoicesRepository answerChoicesRepository,
      @Lazy TestRepository testRepository,
      @Lazy TestResultsRepository testResultsRepository
  ){
    super();
    this.questionRepositoy = questionRepositoy;
    this.answerChoicesRepository = answerChoicesRepository;
    this.testRepository = testRepository;
    this.testResultsRepository = testResultsRepository;
  }


  /**
   * Hàm show danh sách các câu hỏi của 1 bài test
   *
   * @return
   * @throws QuizException
   */
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

  /**
   * Hàm lưu thông tin câu trả lời của user
   *
   * @param tests
   * @return
   * @throws QuizException
   */
  @Override
  @Transactional
  public List<Test> saveChoicesOfTest(List<Test> tests) throws QuizException {
      testRepository.saveAll(tests);
    return tests;
  }

  /**
   * Hàm trả ra bảng kết quả cuối cùng sau khi hoàn thành bài test
   *
   * @return
   * @throws QuizException
   */
  @Override
  @Transactional
  public ReviewAndResultsTable resultsOfTest(int testNumber) throws QuizException {
    // Lấy tổng số câu trả lời đúng
    int numberOfCorrect = testRepository.countNumberOfCorrect();

    // Lấy tổng số câu trả lời sai
    int numberOfIncorrect = testRepository.countNumberOfIncorrect();

    var tests = testRepository.findAll();
    int total = numberOfCorrect + numberOfIncorrect;
    // Số điểm bạn nhận được là số câu đúng / tổng số câu
    String score = String.format("%d/%d", numberOfCorrect, total);

    // Kiểm tra đỗ hay trượt. Nếu >=21 câu đúng thì đỗ
    Boolean isPass = false;
    if (numberOfCorrect >= 21) {
      isPass = true;
    }

    // Lấy thông tin ngày giờ hiện tại
    LocalDateTime localDateTime = LocalDateTime.now();
    TestResults testResults =
        TestResults.builder()
            .numberOfCorrect(numberOfCorrect)
            .numberOfIncorrect(numberOfIncorrect)
            .score(score)
            .isPass(isPass)
            .testNumber(testNumber)
            .dateAndTime(localDateTime)
            .build();
    testResultsRepository.save(testResults);
    var reviewTests = this.reviewTest(testNumber);
    ReviewAndResultsTable reviewAndResultsTable =
        ReviewAndResultsTable.builder()
            .testResults(testResults)
            .reviewTestList(reviewTests)
            .build();
    return reviewTests;
  }

  /**
   * Hàm trả ra các thông tin để xem lại bài kiểm tra
   *
   * @return
   * @throws QuizException
   */
  @Override
  public List<ReviewTest> reviewTest(int testNumber) throws QuizException {
    // Lấy thông tin DS câu hỏi với đề tương ứng
    List<Question> questionList = questionRepositoy.getAllByTestNumber(testNumber);
    if(questionList.isEmpty()){
      throw new QuizException(
          ExceptionUtils.QUESTION_IS_NOT_AVAILABLE,
          ExceptionUtils.messages.get(ExceptionUtils.QUESTION_IS_NOT_AVAILABLE));
    }

    // Lấy thông tin câu trả lời của người dùng
    var listTestOfUser = testRepository.findAll();
    // khởi tạo list câu hỏi xem review
    List<ReviewTest> reviewTests = new ArrayList<>();
    // Lấy thông tin các lựa chọn của câu trả lời cho câu hỏi
    int i = 0;
    for (Question question : questionList) {
      List<AnswerChoices> answerChoices =
          answerChoicesRepository.findAllByQuestionId(question.getQuestionId());
      AnswerChoices correctAnswer = answerChoices.stream()
          .filter(answer -> Boolean.TRUE.equals(answer.getIsCorrect()))
          .findFirst()
          .orElse(null);
      // Đáp án của người dùng chọn
      var test = listTestOfUser.get(i);
      var answerText = correctAnswer.getAnswerText();
      if(answerText.equals(null)){
        answerText = String.valueOf(false);
      }
      ReviewTest display = ReviewTest.builder().questionText(question.getQuestionText())
          .answerChoices(answerChoices)
          .answerText(test.getAnswerChoicesText())
          .isCorrect(test.getIsCorrect())
          .correctAnswer(answerText).build();
      reviewTests.add(i, display);
      i++;
    }

  // xóa sạch thông tin đã lưu của bài kiểm tra trong bảng test
    testRepository.deleteAll(listTestOfUser);
    return reviewTests;
  }
}
