package com.baseball.application.service;

import com.baseball.adapter.persistence.BaseballGameHistoryRepository;
import com.baseball.adapter.persistence.BaseballGameRepository;
import com.baseball.application.port.BaseballGameAnswerUseCase;
import com.baseball.application.port.BaseballGameHistoryQuery;
import com.baseball.application.port.BaseballGameResultQuery;
import com.baseball.application.port.BaseballGameStartUseCase;
import com.baseball.common.error.exception.BaseballGameNotFoundException;
import com.baseball.domain.Answer;
import com.baseball.domain.BaseballGame;
import com.baseball.domain.BaseballGameHistory;
import com.baseball.domain.data.GuessResult;
import com.baseball.domain.generator.AnswerGenerator;
import com.baseball.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BaseballService implements BaseballGameStartUseCase, BaseballGameAnswerUseCase, BaseballGameResultQuery, BaseballGameHistoryQuery {

    private final BaseballGameRepository baseballGameRepository;
    private final BaseballGameHistoryRepository baseballGameHistoryRepository;
    private final AnswerGenerator answerGenerator;

    @Value("${baseball.maxAnswerCount}")
    private int maxAnswerCount;

    @Override
    @Transactional
    public BaseballGameStartResponse executeStart() {
        BaseballGame newGame = BaseballGame.create(null, null, null, answerGenerator.generate(), maxAnswerCount);
        BaseballGame baseballGame = baseballGameRepository.save(newGame);

        return new BaseballGameStartResponse(baseballGame.getRoomId());
    }

    @Override
    @Transactional
    public BaseballGameAnswerResponse executeAnswer(long roomId, BaseballGameAnswerRequest request) {
        BaseballGame baseballGame = baseballGameRepository
                .findByRoomId(roomId).orElseThrow(BaseballGameNotFoundException::new);

        GuessResult guessResult = baseballGame.guess(request.getAnswer());

        baseballGameHistoryRepository.save(
                new BaseballGameHistory(
                    null,
                    baseballGame.getRoomId(),
                    request.getAnswer(),
                    guessResult.getStrike(),
                    guessResult.getBall(),
                    guessResult.getOut()));

        return BaseballGameAnswerResponse.of(baseballGame.getAnswer().getRemainingCount(), guessResult);
    }

    @Override
    public BaseballGameResultResponse findGameByRoomId(Long roomId) {
        BaseballGame baseballGame = baseballGameRepository
                .findByRoomId(roomId).orElseThrow(BaseballGameNotFoundException::new);
        Answer answer = baseballGame.getAnswer();

        return new BaseballGameResultResponse(answer.getRemainingCount(), answer.getAnswerCount());
    }

    @Override
    public List<BaseballHistoryResponse> findAllHistoryByRoomId(Long roomId) {
        baseballGameRepository.findByRoomId(roomId).orElseThrow(BaseballGameNotFoundException::new);
        List<BaseballGameHistory> baseballGameHistories = baseballGameHistoryRepository
                .findAllHistoryByRoomId(roomId);

        return baseballGameHistories.stream()
                .map(baseballGameHistory ->
                        new BaseballHistoryResponse(baseballGameHistory.getAnswer(),
                                new BaseballGameAnswerResultResponse(
                                        baseballGameHistory.getStrike(),
                                        baseballGameHistory.getBall(),
                                        baseballGameHistory.getOut()))).collect(Collectors.toList());
    }
}
