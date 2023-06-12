package com.baseball.domain;

import com.baseball.common.error.exception.IllegalAnswerArgumentException;
import com.baseball.common.error.exception.IllegalAnswerStateException;
import com.baseball.domain.data.GuessResult;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int answer;

    private int answerCount;

    private int maxAnswerCount;

    @OneToOne(mappedBy = "answer", fetch = FetchType.LAZY)
    private BaseballGame baseballGame;

    private Answer(int answer, int maxAnswerCount) {
        this.answer = answer;
        this.answerCount = 0;
        this.maxAnswerCount = maxAnswerCount;
    }

    public int getRemainingCount() {
        return this.maxAnswerCount - this.answerCount;
    }

    public GuessResult guess(int guessNumber) {
        if (!this.canGuess()) {
            throw new IllegalAnswerStateException();
        }

        this.answerCount++;
        int[] answerArray = Integer.toString(this.answer).chars().map(c -> c-'0').toArray();
        int[] guessAnswerArray = Integer.toString(guessNumber).chars().map(c -> c-'0').toArray();

        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < answerArray.length; i++) {
            for (int j = 0; j < guessAnswerArray.length; j++) {
                if (answerArray[i] == guessAnswerArray[j]) {
                    if (i == j) {
                        strikeCount++;
                    } else {
                        ballCount++;
                    }
                }
            }
        }

        return new GuessResult(strikeCount, ballCount);
    }

    public boolean canGuess() {
        return this.maxAnswerCount > this.answerCount;
    }

    public static Answer create(int answerNumber, int maxAnswerCount) {
        validateAnswer(answerNumber);
        return new Answer(answerNumber, maxAnswerCount);
    }

    private static void validateAnswer(int answerNumber) {
        LinkedList<Integer> stack = new LinkedList<>();
        while (answerNumber > 0) {
            stack.push(answerNumber % 10);
            answerNumber = answerNumber / 10;
        }
        if (new HashSet<>(stack).size() != 3) {
            throw new IllegalAnswerArgumentException();
        }
    }
}
