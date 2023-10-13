package com.example.motorbikedrivinglicensequiz.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

// domain quản lý các lựa chọn của từng câu hỏi
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "answer_choices")
public class AnswerChoices {
  // id cuar mỗi lựa chọn
  @Id
  @SequenceGenerator(
      name = "answer_choices_seq",
      sequenceName = "answer_choices_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_choices_seq")
  @Column(name = "answer_id")
  private Long answerId;

  // Nội dung câu trả lời
  @Column(name = "answer_text")
  private String answerText;

  // id của câu hỏi liên quan
  @Column(name = "question_id")
  private Long questionId;

  // true đúng flase sai
  @Column(name = "is_correct")
  private Boolean isCorrect;

}
