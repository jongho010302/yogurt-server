package com.yogurt.studio.controller.common;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.studio.domain.Studio;
import com.yogurt.studio.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/common/studios")
public class CommonStudioController {

    private final StudioService studioService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getStudioList() {
        List<Studio> studioList = studioService.getAll();
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("센터 리스트입니다.", studioList), HttpStatus.OK);
    }
}
