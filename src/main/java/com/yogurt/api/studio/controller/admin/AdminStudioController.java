package com.yogurt.api.studio.controller.admin;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.api.studio.domain.Studio;
import com.yogurt.api.studio.dto.SaveStudioRequest;
import com.yogurt.api.studio.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/studios")
public class AdminStudioController {

    private final StudioService service;

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated SaveStudioRequest saveStudioRequest) {
        Studio studio = service.save(saveStudioRequest.toEntity());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("센터가 저장되었습니다.", studio), HttpStatus.OK);
    }

}
