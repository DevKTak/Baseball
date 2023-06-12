package com.baseball.domain.data;

public class GuessResult {

    private final int strike;
    private final int ball;
    private final int out;

    public GuessResult(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
        this.out = 3 - (strike + ball);
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public int getOut() {
        return out;
    }

    public boolean isCorrect() {
        return this.strike == 3;
    }
}
