package com.baseball.application.port;

import com.baseball.dto.BaseballGameResultResponse;

public interface BaseballGameResultQuery {

    BaseballGameResultResponse findByRoomId(Long roomId);
}
