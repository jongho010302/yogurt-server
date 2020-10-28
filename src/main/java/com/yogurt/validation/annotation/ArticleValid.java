package com.yogurt.validation.annotation;

import com.yogurt.validation.ArticleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ArticleValidator.class)
@Documented
public @interface ArticleValid {
    String message() default "존재하지 않는 게시글입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
