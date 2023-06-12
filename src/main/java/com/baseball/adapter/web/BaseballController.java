package com.baseball.adapter.web;

import com.baseball.application.port.BaseballGameAnswerUseCase;
import com.baseball.application.port.BaseballGameStartUseCase;
import com.baseball.common.api.ApiResponse;
import com.baseball.dto.BaseballGameAnswerRequest;
import com.baseball.dto.BaseballGameAnswerResponse;
import com.baseball.dto.BaseballGameStartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class BaseballController {

    private final BaseballGameStartUseCase baseballGameStartUseCase;
    private final BaseballGameAnswerUseCase baseballGameAnswerUseCase;

    @PostMapping("/start")
    public ApiResponse<BaseballGameStartResponse> gameStart() {
        return ApiResponse.success(baseballGameStartUseCase.executeStart());
    }

    @PostMapping("/{roomId}/answer")
    public ApiResponse<BaseballGameAnswerResponse> answer(
            @PathVariable Long roomId,
            @RequestBody BaseballGameAnswerRequest request) {
        return ApiResponse.success(baseballGameAnswerUseCase.executeAnswer(roomId, request));
    }
}
