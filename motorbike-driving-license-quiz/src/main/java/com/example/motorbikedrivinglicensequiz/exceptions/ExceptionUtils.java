package com.example.motorbikedrivinglicensequiz.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Class khai báo exception
 *
 * @author nga
 */
public class ExceptionUtils {
  public static final String E_INTERNAL_SERVER = "E_INTERNAL_SERVER";
  public static final String TEST_NUMBER_IS_NOT_AVAILABLE = "TEST_NUMBER_IS_NOT_AVAILABLE";
  public static final String QUESTION_IS_NOT_AVAILABLE = "QUESTION_IS_NOT_AVAILABLE";
  public static Map<String, String> messages;

  static {
    messages = new HashMap<>();
    messages.put(ExceptionUtils.E_INTERNAL_SERVER, "Server không phản hồi.");
    messages.put(ExceptionUtils.TEST_NUMBER_IS_NOT_AVAILABLE, "Không tìm thấy bất kỳ đề nào có sẵn.");
    messages.put(ExceptionUtils.QUESTION_IS_NOT_AVAILABLE, "Không tìm thấy bất kỳ câu hỏi nào cho đề này");
  }
}
