package com.example.motorbikedrivinglicensequiz.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Domain lưu trữ bảng kết quả
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "test_results")
public class TestResults {
  // id bảng kết quả
  @Id
  @SequenceGenerator(
      name = "test_results_seq",
      sequenceName = "test_results_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_results_seq")
  @Column(name = "test_results_id")
  private Long testResultsId;

  // Số câu đúng
  @Column(name = "number_of_correct")
  private Integer numberOfCorrect;

  // Số câu sai
  @Column(name = "number_of_incorrect")
  private Integer numberOfIncorrect;

  // Số điểm bạn nhận được số câu đúng / tổng số câu
  @Column(name = "score")
  private String score;

  // true: đậu, flase: trượt
  @Column(name = "is_pass")
  private Boolean isPass;

  // Thời gian làm bài
  @Column(name = "date_and_time")
  private LocalDateTime dateAndTime;

}
