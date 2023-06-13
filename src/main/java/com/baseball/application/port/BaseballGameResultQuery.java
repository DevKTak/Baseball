package com.baseball.application.port;

import com.baseball.dto.BaseballGameResultResponse;

public interface BaseballGameResultQuery {

    BaseballGameResultResponse findGameByRoomId(Long roomId);
}
