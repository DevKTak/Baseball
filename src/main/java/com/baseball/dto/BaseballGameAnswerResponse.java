package com.baseball.dto;

public record BaseballGameAnswerResponse(boolean correct,
                                         int remainingCount,
                                         int strike,
                                         int ball,
                                         int out) {
}
