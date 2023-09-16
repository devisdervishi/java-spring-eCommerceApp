package devis.springboot.app.errors;

import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class EntityNotFoundExceptionController extends ResponseEntityExceptionHandler{
@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFound(EntityNotFoundException exception, WebRequest request){

        ErrorMessage errorMessage=new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND,
                ((ServletWebRequest)request).getRequest().getRequestURL().toString());
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }
}