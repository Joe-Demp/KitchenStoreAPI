package ie.dempsey.kitchenstore.application.advice;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseBody
    @ExceptionHandler(NoSuchProductException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(NoSuchProductException ex) {
        return ex.getMessage();
    }
}
