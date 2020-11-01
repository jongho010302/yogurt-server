package com.yogurt.base.exception.handler;

import com.yogurt.base.dto.ExceptionResponse;
import com.yogurt.base.exception.*;
import com.yogurt.base.exception.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    /**
     * Custom handling with 400 response.
     */
    @ExceptionHandler(value = {
            YogurtAlreadyBookedException.class,
            YogurtAlreadyDataExistsException.class,
            YogurtAlreadyDataUseException.class,
            YogurtDataNotExistsException.class,
            YogurtDifferentVerificationCodeException.class,
            YogurtEntityNotFountException.class,
            YogurtBookingCancelTimeExceedException.class,
            YogurtBookingTimeExceedException.class,
            YogurtBookingEntryExceedException.class,
            YogurtFileSizeException.class,
            YogurtFileUploadException.class,
            YogurtInvalidFormatException.class,
            YogurtInvalidSameEmailException.class,
            YogurtInvalidSamePasswordException.class,
            YogurtInvalidSameUsernameException.class,
            YogurtInvalidParamException.class,
            YogurtLackOfCouponCountException.class,
            YogurtVerifyTimeoutException.class,
            YogurtStudioDifferentException.class,
            YogurtWrongPasswordException.class,
    })
    public ResponseEntity<ExceptionResponse> customHandleBadRequestException(YogurtException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode(), e.getErrorMessage(), e.getErrorData(), e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Custom handling with 401 response.
     */
    @ExceptionHandler(value = {
            YogurtNoAuthException.class,
    })
    public ResponseEntity<ExceptionResponse> customHandleUnauthorizedException(YogurtException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode(), e.getErrorMessage(), e.getErrorData(), e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle custom exception by response FORBIDDEN.
     */
    @ExceptionHandler(value = {
            YogurtNotVerifiedEmailException.class, // 이메일 인증이 안 된 것이지 사용자가 누구인지 알고 있으므로 403 Error를 준다.
    })
    public ResponseEntity<ExceptionResponse> customHandleForbiddenException(YogurtException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode(), e.getErrorMessage(), e.getErrorData(), e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(YogurtNotFoundException.class)
    public ResponseEntity<ExceptionResponse> customHandleNotFoundException(YogurtException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode(), e.getErrorMessage(), e.getErrorData(), e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(YogurtInternalServerException.class)
    public ResponseEntity<ExceptionResponse> customHandleInternalServerException(YogurtException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode(), e.getErrorMessage(), e.getErrorData(), e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Custom handle exception for http request body argument by response BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> customMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorType errorType = ErrorType.METHOD_ARGUMENT_NOT_VALID;

        BindingResult result = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError err : list) {
                errorMessage.append(String.format("%s\n", err.getDefaultMessage()));
            }
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorType.toString(), errorType.getErrorMessage(), null, errorMessage.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Custom handle exception for HttpMessageNotReadableException by response BAD_REQUEST.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("HttpMessageNotReadableException", "HttpMessageNotReadableException", null, "HttpMessageNotReadableException");

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Custom handle exception for ConstraintViolationException by response BAD_REQUEST.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> customHandleConstraintViolationException(ConstraintViolationException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("ConstraintViolationException", e.getMessage(), null, e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Custom handle exception for MissingServletRequestParameterException by response BAD_REQUEST.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> customHandleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("INVALID_PARAMETER", "invalid.parameter", null, e.getParameterName() + " (은)는 필수 값입니다.");

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Custom handle exception for MissingServletRequestParameterException by response BAD_REQUEST.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> customHandleMissingServletRequestParameterException(IllegalArgumentException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("IllegalArgumentException", e.getMessage(), null, e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Custom handle exception for MissingServletRequestParameterException by response BAD_REQUEST.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> customHandleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("HttpRequestMethodNotSupportedException", e.getMessage(), null, e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



}
