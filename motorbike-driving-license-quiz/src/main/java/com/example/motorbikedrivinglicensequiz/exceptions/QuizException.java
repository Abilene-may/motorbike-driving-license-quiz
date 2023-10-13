package com.example.motorbikedrivinglicensequiz.exceptions;

import com.example.motorbikedrivinglicensequiz.models.common.ValidateError;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nga
 *
 * Class NotFoundException kế thừa Exception phục vụ việc xử lý và tổng hợp lỗi
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuizException extends Exception{
  private String messageKey;
  private String message;
  private Throwable throwable;
  private List<String> messages;
  private List<ValidateError> validateErrors; // NOSONAR

  public QuizException(List<ValidateError> validateErrors) {
    this.messageKey = "VALIDATE_ERROR";
    this.validateErrors = validateErrors;
    this.messages = new ArrayList<>();
    validateErrors.forEach(validateError -> this.messages.add(validateError.getErrorMessage()));
  }

  public QuizException(List<ValidateError> validateErrors, String messageKey) {
    this.messageKey = messageKey;
    this.validateErrors = validateErrors;
    this.messages = new ArrayList<>();
    validateErrors.forEach(validateError -> this.messages.add(validateError.getErrorMessage()));
  }

  public QuizException(String msgKey) {
    this.messageKey = msgKey;
  }

  public QuizException(String msgKey, String msg) {
    this.messageKey = msgKey;
    this.message = msg;
  }

  public List<String> getMessages() {
    return messages;
  }

  public String getMessage() {
    if (StringUtils.isNotEmpty(this.message)) {
      return message;
    }
    if (StringUtils.isNotEmpty(this.messageKey)) {
      if (this.messageKey.equals("VALIDATE_ERROR") && this.validateErrors != null) {
        var messages =
            this.validateErrors.stream()
                .map(ValidateError::getErrorMessage)
                .collect(Collectors.toList());
        this.message = String.join(", ", messages);
      } else {
        this.message = String.format(ExceptionUtils.messages.get(this.messageKey));
      }
    }
    return message;
  }
}
