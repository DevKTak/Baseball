package com.baseball.application.port;

import com.baseball.dto.BaseballHistoryResponse;

import java.util.List;

public interface BaseballGameHistoryQuery {

    List<BaseballHistoryResponse> findAllHistoryByRoomId(Long roomId);
}
