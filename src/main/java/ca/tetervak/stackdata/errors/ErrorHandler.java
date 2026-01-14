package ca.tetervak.stackdata.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Ensures 404 statuses are set
    public ModelAndView handleNoResourceFoundException(NoResourceFoundException ex) {
        String path = ex.getResourcePath();
        ModelAndView mav = new ModelAndView("error-404");
        mav.addObject("method", ex.getHttpMethod());
        mav.addObject("path", path);
        log.error("Error 404: {}", path);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        log.error("Error: {}", ex.getMessage(), ex);
        ModelAndView mav = new ModelAndView("general-error");
        mav.addObject("exception", ex.getClass().getSimpleName());
        mav.addObject("message", ex.getMessage());
        return mav;
    }
}
