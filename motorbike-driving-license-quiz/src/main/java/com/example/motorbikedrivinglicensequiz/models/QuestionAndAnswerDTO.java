package com.example.motorbikedrivinglicensequiz.models;

import com.example.motorbikedrivinglicensequiz.domains.AnswerChoices;
import com.example.motorbikedrivinglicensequiz.domains.Question;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO thông tin về câu hỏi và câu trả lời lieen quan
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionAndAnswerDTO {
  // câu hỏi
  private Question question;

  // Nội dung các câu trả lời
  List<AnswerChoices> listAnswerChoices;
}
