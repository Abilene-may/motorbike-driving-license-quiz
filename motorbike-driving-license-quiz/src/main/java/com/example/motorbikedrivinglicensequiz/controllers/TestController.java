package com.example.motorbikedrivinglicensequiz.controllers;

import com.example.motorbikedrivinglicensequiz.exceptions.ExceptionUtils;
import com.example.motorbikedrivinglicensequiz.exceptions.QuizException;
import com.example.motorbikedrivinglicensequiz.models.common.ErrorDTO;
import com.example.motorbikedrivinglicensequiz.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
  private final TestService testService;


  // API hiển thị DS các câu hỏi theo từng đề
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

}
