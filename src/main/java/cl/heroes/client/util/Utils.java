package cl.heroes.client.util;


import cl.heroes.client.dto.ResponseWrapper;
import cl.heroes.client.exception.ConflictException;
import cl.heroes.client.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Utils {

  public static void verifyForConflict(boolean condition, String message)
      throws ConflictException {
    if (condition) {
      throw new ConflictException(message);
    }
  }

  public static void verifyForNotFound(boolean condition, String message)
      throws NotFoundException {
    if (condition) {
      throw new NotFoundException(message);
    }
  }

  public static ResponseWrapper formatResponse(int statusCode, String message, Object payload) {

    return ResponseWrapper.builder().statusCode(statusCode).message(message).payload(payload).build();
  }

}
