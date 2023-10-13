package com.example.motorbikedrivinglicensequiz.models.tests;

import com.example.motorbikedrivinglicensequiz.domains.Question;
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
  // Thông tin câu hỏi
  private Question question;

  // id câu trả lời
  private Long answerChoiceId;

  // Nội dung câu trả lời
  private String answerChoiceText;

}
