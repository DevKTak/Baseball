package com.baseball.adapter.web;

import com.baseball.application.port.BaseballGameStartUseCase;
import com.baseball.common.ApiResponse;
import com.baseball.dto.BaseballGameStartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class BaseballController {

    private final BaseballGameStartUseCase baseballGameStartUseCase;

    @PostMapping("/start")
    public ApiResponse<BaseballGameStartResponse> gameStart() {
        return ApiResponse.success(baseballGameStartUseCase.execute());
    }
}
