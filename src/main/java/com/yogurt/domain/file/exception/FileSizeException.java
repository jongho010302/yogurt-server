package com.yogurt.domain.file.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class FileSizeException extends BusinessException {

    public FileSizeException() {
        super(ErrorCode.FILE_SIZE);
    }
}
