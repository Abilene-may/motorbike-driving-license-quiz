package com.example.motorbikedrivinglicensequiz.controllers;

import com.example.motorbikedrivinglicensequiz.domains.Test;
import com.example.motorbikedrivinglicensequiz.exceptions.ExceptionUtils;
import com.example.motorbikedrivinglicensequiz.exceptions.QuizException;
import com.example.motorbikedrivinglicensequiz.models.common.ErrorDTO;
import com.example.motorbikedrivinglicensequiz.services.TestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
  private final TestService testService;


  /**
   * API hiển thị DS các câu hỏi theo từng đề
   *
   * @return
   */
  @GetMapping("/list-question")
  public ResponseEntity<Object> listQuestionOfTest(){
    try {
      var display = testService.testDisplay();
      return new ResponseEntity<>(display, HttpStatus.OK);
    } catch (QuizException e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(
          new ErrorDTO(e.getMessageKey(), e.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * API gửi đáp án người dùng đã chọn
   *
   * @param tests
   * @return
   */
  @PostMapping("/choice-of-test")
  public ResponseEntity<Object> saveChoicesOfTest(@RequestBody List<Test> tests){
    try {
      var test = testService.saveChoicesOfTest(tests);
      return new ResponseEntity<>(test, HttpStatus.OK);
    } catch (QuizException e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(
          new ErrorDTO(e.getMessageKey(), e.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * API hiển thị bảng kết quả của người dùng sau khi hoàn thành bài test
   *
   * @return
   */
  @GetMapping("/show-test-results/{testNumber}")
  public ResponseEntity<Object> showTestResults(@PathVariable int testNumber){
    try {
      var testResults = testService.resultsOfTest(testNumber);
      return new ResponseEntity<>(testResults, HttpStatus.OK);
    } catch (QuizException e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(
          new ErrorDTO(e.getMessageKey(), e.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/review-test/{testNumber}")
  public ResponseEntity<Object> reviewTest(@PathVariable int testNumber){
    try {
      var reviewTests = testService.reviewTest(testNumber);
      return new ResponseEntity<>(reviewTests, HttpStatus.OK);
    } catch (QuizException e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(
          new ErrorDTO(e.getMessageKey(), e.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
