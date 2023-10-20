package com.example.motorbikedrivinglicensequiz.models.tests;

public interface AnswerChoicesDTO {
  // id câu trả lời
  Long getAnswerId();

  // Nội dung câu trả lời
  String getAnswerText();

  // id của câu hỏi liên quan
  Long getQuestionId();

  Boolean getIsCorrect();
}
