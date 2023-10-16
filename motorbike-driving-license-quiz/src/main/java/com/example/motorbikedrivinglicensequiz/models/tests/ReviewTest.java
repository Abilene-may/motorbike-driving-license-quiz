package com.example.motorbikedrivinglicensequiz.models.tests;

import com.example.motorbikedrivinglicensequiz.domains.AnswerChoices;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewTest {
  // nội dung câu hỏi
  private  String questionText;

  // nội dung các câu trả lời
  List<AnswerChoices> answerChoices;

  // Câu trả lời của người dùng
  private String answerText;

  // người dùng trả lời đúng hay sai ?
  private Boolean isCorrect;

  // Nội dung đáp án đúng
  private String correctAnswer;
}
