package valeriafarinosi.U5_W2_D4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import valeriafarinosi.U5_W2_D4.payloads.ErrorsDTO;
import valeriafarinosi.U5_W2_D4.payloads.ValidationErrorsDTO;

import java.time.LocalDateTime;

//    classe responsabile della gestione degli errori di tutta l'applicazione
//    le future eccezioni arriveranno tutte dai metodi di questa classe
@RestControllerAdvice
public class ErrorsHandler {

    // VALIDATION EXCEPTION
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public ValidationErrorsDTO handleValidation(ValidationException ex) {
        return new ValidationErrorsDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsList());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsDTO handleGenericException(Exception ex) {

        ex.printStackTrace();

        return new ErrorsDTO("BackEnd error, will fix soon", LocalDateTime.now());
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
//    public ErrorsDTO handleEnumException(HttpMessageNotReadableException ex) {
//        return new ErrorsDTO(
//                "Invalid blog category.",
//                LocalDateTime.now()
//        );
//    }

}
