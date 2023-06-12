package com.baseball.application.port;

import com.baseball.dto.BaseballGameAnswerRequest;
import com.baseball.dto.BaseballGameAnswerResponse;

public interface BaseballGameAnswerUseCase {

    BaseballGameAnswerResponse executeAnswer(long roomId, BaseballGameAnswerRequest request);

}
