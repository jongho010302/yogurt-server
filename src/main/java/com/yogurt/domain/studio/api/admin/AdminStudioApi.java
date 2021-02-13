package com.yogurt.domain.studio.api.admin;

import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.studio.dto.SaveStudioRequest;
import com.yogurt.domain.studio.service.admin.AdminStudioService;
import com.yogurt.global.common.response.ApiResponse;
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
public class AdminStudioApi {

    private final AdminStudioService service;

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated SaveStudioRequest saveStudioRequest) {
        Studio studio = service.create(saveStudioRequest.toEntity());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("센터가 저장되었습니다.", studio), HttpStatus.OK);
    }

}
