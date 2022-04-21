package events.exceptions.handler;

import events.exceptions.EventNotFoundException;
import events.exceptions.errorDetails.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EventTrackerGlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EventNotFoundException.class})
    public ResponseEntity<?> handleEventNotFound(EventNotFoundException eventNotFoundException,
                                                 WebRequest request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.valueOf("application/json"));

        return super.handleExceptionInternal(eventNotFoundException,
                eventNotFoundException.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

//    @ExceptionHandler(EventNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleEventNotFound(EventNotFoundException eventNotFoundException) {
//        ErrorDetails errorDetails = new ErrorDetails(eventNotFoundException.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
//    }

}
