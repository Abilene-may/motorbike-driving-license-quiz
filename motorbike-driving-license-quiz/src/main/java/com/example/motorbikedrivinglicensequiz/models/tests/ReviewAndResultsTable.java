package com.example.motorbikedrivinglicensequiz.models.tests;

import com.example.motorbikedrivinglicensequiz.domains.TestResults;
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
public class ReviewAndResultsTable {
  // Baảng kết quả của bài test
  private TestResults testResults;

  // Xem lại bài kiểm tra đã làm
  private List<ReviewTest> reviewTestList;
}
