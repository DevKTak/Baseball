package com.baseball.application.service;

import com.baseball.adapter.persistence.BaseballGameRepository;
import com.baseball.application.port.BaseballGameStartUseCase;
import com.baseball.domain.BaseballGame;
import com.baseball.domain.generator.AnswerGenerator;
import com.baseball.dto.BaseballGameStartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BaseballService implements BaseballGameStartUseCase {

    private final BaseballGameRepository baseballGameRepository;

    private final AnswerGenerator answerGenerator;

    @Value("${baseball.maxAnswerCount}")
    private int maxAnswerCount;

    @Override
    @Transactional
    public BaseballGameStartResponse execute() {
        BaseballGame newGame = BaseballGame.create(
                null,
                null,
                null,
                answerGenerator.generate(),
                maxAnswerCount
        );
        BaseballGame baseballGame = baseballGameRepository.save(newGame);

        return new BaseballGameStartResponse(baseballGame.getRoomId());
    }
}
