package com.example.motorbikedrivinglicensequiz.services;

import com.example.motorbikedrivinglicensequiz.domains.Test;
import com.example.motorbikedrivinglicensequiz.domains.TestResults;
import com.example.motorbikedrivinglicensequiz.exceptions.QuizException;
import com.example.motorbikedrivinglicensequiz.models.tests.QuestionAndAnswerDisplay;
import com.example.motorbikedrivinglicensequiz.models.tests.ReviewAndResultsTable;
import com.example.motorbikedrivinglicensequiz.models.tests.ReviewTest;
import com.example.motorbikedrivinglicensequiz.models.tests.TestReqDTO;
import java.util.List;

public interface TestService {
  // random 1 đề test có sẵn trong DB
  List<QuestionAndAnswerDisplay> testDisplay() throws QuizException;

  // Lưu thông tin câu trả lời của người làm bài test
  List<Test> saveChoicesOfTest(List<Test> tests) throws QuizException;

  // Hiển thị bảng kết quả cuối bài test
  ReviewAndResultsTable resultsOfTest(int testNumber) throws QuizException;

  // Hiển thi đáp án đúng và đáp án người dùng đã chọn ở phần review bài test
  List<ReviewTest> reviewTest(int testNumber) throws QuizException;
}
