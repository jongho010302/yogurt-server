package com.yogurt.validation;

import com.yogurt.article.service.admin.AdminArticleService;
import com.yogurt.validation.annotation.ArticleValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ArticleValidator implements ConstraintValidator<ArticleValid, Long> {

    private final AdminArticleService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
