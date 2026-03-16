package com.example.studiz.domain.user.presentation.controller;

import com.example.studiz.domain.user.service.ReissueService;
import com.example.studiz.global.jwt.dto.request.ReissueRequest;
import com.example.studiz.global.jwt.dto.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class ReissueController {

    private final ReissueService reissueService;

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(
            @RequestBody @Valid ReissueRequest reissueRequest
    ) {

        TokenResponse response = reissueService.reissue(reissueRequest.getRefreshToken());
        return ResponseEntity.ok(response);
    }
}
