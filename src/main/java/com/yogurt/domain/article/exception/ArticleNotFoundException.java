package com.yogurt.domain.article.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class ArticleNotFoundException extends EntityNotFoundException {

    public ArticleNotFoundException(long target) {
        super(target + " is not found");
    }
}
