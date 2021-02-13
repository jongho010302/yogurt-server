package com.yogurt.file.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class FileUploadException extends BusinessException {

    public FileUploadException() {
        super(ErrorCode.FILE_UPLOAD);
    }
}
