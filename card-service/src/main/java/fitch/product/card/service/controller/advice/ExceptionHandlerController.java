package fitch.product.card.service.controller.advice;

import fitch.product.card.service.exceptions.CardNotFoundException;
import fitch.product.card.service.exceptions.CustomException;
import fitch.product.card.service.model.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Map<Class<? extends CustomException>, HttpStatus> HTTP_STATUS_FOR_CUSTOM = Map.ofEntries(
            Map.entry(CardNotFoundException.class, HttpStatus.NOT_FOUND)
    );

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> handleCustom(CustomException exc) {
        HttpStatus status = HTTP_STATUS_FOR_CUSTOM.getOrDefault(exc.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("{} => Custom exception: {}", status, exc.getMessage(), exc);
        return ResponseEntity.status(status).body(
                new ApiError(
                        status.value(),
                        exc.getMessage()
                )
        );
    }


}
