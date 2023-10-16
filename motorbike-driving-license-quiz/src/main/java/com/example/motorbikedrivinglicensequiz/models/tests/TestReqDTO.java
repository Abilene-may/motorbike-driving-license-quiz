package com.example.motorbikedrivinglicensequiz.models.tests;

import com.example.motorbikedrivinglicensequiz.domains.Question;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO gửi yêu cầu về câu hỏi và các câu trả lời mà người dùng đã chọn
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestReqDTO {
  private Long questionId;

  // Thông tin câu hỏi
  private String questionText;

  // id câu trả lời của người dùng
  private Long answerId;

  // Thông tin câu trả lời của người dùng
  private String answerChoicesText;

  // đúng hay sai
  private Boolean isCorrect;

  // đề số?
  private int testNumber;

}
