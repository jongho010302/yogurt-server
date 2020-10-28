package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtLackOfCouponCountException extends YogurtException {

    public YogurtLackOfCouponCountException(String message) {
        super(ErrorType.LACK_OF_COUPON_COUNT, message);
    }

    public YogurtLackOfCouponCountException(String message, List<Object> errorData) {
        super(ErrorType.LACK_OF_COUPON_COUNT, message, errorData);
    }
}
