package com.baseball.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BaseballGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name =  "answer_id")
    private Answer answer;

    private Status status;

    private Instant createdAt;

    private Instant updatedAt;

    private BaseballGame(Long roomId, Answer answer, Instant createdAt, Instant updatedAt) {
        this.roomId = roomId;
        this.answer = answer;
        this.status = Status.IN_PROGRESS;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
        this.updatedAt = updatedAt == null ? Instant.now() : updatedAt;
    }

//    public GuessResult guess(int guessNumber) {
//        if (this.status == Status.CLOSED) throw new BaseballGameAlreadyClosedException();
//        GuessResult result = this.answer.guess(guessNumber);
//
//        if (!this.answer.canGuess() || result.isCorrect()) {
//            this.status = Status.CLOSED;
//        }
//
//        return result;
//    }

    public enum Status {
        IN_PROGRESS,
        CLOSED
    }

    public static BaseballGame create(Long roomId, Instant createdAt, Instant updatedAt, int answer, int maxAnswerCount) {
        Answer newAnswer = Answer.create(answer, maxAnswerCount);
        return new BaseballGame(
                roomId,
                newAnswer,
                createdAt,
                updatedAt
        );
    }
}
