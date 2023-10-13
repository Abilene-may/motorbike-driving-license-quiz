package com.example.motorbikedrivinglicensequiz.models.tests;

import com.example.motorbikedrivinglicensequiz.domains.Question;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO hiển thị danh sách câu hỏi và câu trả lời cho bài test

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionAndAnswerDisplay {
  // Thông tin câu hỏi
  private Question question;

  // Danh sách các câu trả lời
  private List<AnswerChoicesDTO> answerChoicesDTOList;
}
