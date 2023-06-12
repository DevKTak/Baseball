package com.baseball.common.error.handler;

import com.baseball.common.api.ApiResponse;
import com.baseball.common.error.exception.BaseballGameAlreadyClosedException;
import com.baseball.common.error.exception.BaseballGameNotFoundException;
import com.baseball.common.error.exception.IllegalAnswerStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseballExceptionHandler {

    @ExceptionHandler(BaseballGameNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    ApiResponse<Object> handle(BaseballGameNotFoundException e) {
        return ApiResponse.fail(
                "BASEBALL_GAME_NOT_FOUND",
                e.getMessage()
        );
    }

    @ExceptionHandler(BaseballGameAlreadyClosedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    ApiResponse<Object> handle(BaseballGameAlreadyClosedException e) {
        return ApiResponse.fail(
                "CLOSED_GAME",
                e.getMessage()
        );
    }

    @ExceptionHandler(IllegalAnswerStateException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    ApiResponse<Object> handle(IllegalAnswerStateException e) {
        return ApiResponse.fail(
                "ILLEGAL_ANSWER_STATE",
                e.getMessage()
        );
    }
}
