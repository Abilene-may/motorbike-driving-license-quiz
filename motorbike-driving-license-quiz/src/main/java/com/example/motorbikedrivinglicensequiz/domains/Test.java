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

// Domain lưu trữ thông tin bài test
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "test")
public class Test {
  // id bài test
  @Id
  @SequenceGenerator(
      name = "test_seq",
      sequenceName = "test_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
  @Column(name = "test_id")
  private Long testId;

  // id câu hỏi
  @Column(name = "question_id")
  private Long questionId;

  // Thông tin câu hỏi
  @Column(name = "question_text")
  private String question;

  // id câu trả lời của người dùng
  @Column(name = "answer_id")
  private Long answerId;

  // Thông tin câu trả lời của người dùng
  @Column(name = "answer_choices_text")
  private String answerChoicesText;

  // câu trả lời đúng hay sai
  @Column(name = "is_correct")
  private Boolean isCorrect;

}
