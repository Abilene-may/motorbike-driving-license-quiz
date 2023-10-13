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

/**
 * domain quản lý câu hỏi
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "question")
public class Question {
  // id câu hỏi
  @Id
  @SequenceGenerator(
      name = "question_seq",
      sequenceName = "question_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
  @Column(name = "question_id")
  private Long questionId;

  // Câu hỏi
  @Column(name = "question_text")
  private String questionText;

  // đề số?
  @Column(name = "test_number")
  private int testNumber;

}
