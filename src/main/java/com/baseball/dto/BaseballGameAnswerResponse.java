package com.baseball.dto;

import com.baseball.domain.data.GuessResult;

public record BaseballGameAnswerResponse(boolean correct,
                                         int remainingCount,
                                         int strike,
                                         int ball,
                                         int out) {
    public static BaseballGameAnswerResponse of(int remainingCount, GuessResult guessResult) {
        return new BaseballGameAnswerResponse(
                guessResult.isCorrect(),
                remainingCount,
                guessResult.getStrike(),
                guessResult.getBall(),
                guessResult.getOut()
        );
    }
}
