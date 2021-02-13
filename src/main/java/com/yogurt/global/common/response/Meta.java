package com.yogurt.global.common.response;

import org.springframework.data.domain.Pageable;

public class Meta {

    public int limit;
    public int page;
    public long offset;
    public int totalCount;

    private Meta(Pageable pageable, int totalCount) {
        this.limit = pageable.getPageSize();
        this.page = pageable.getPageNumber();
        this.offset = pageable.getOffset();
        this.totalCount = totalCount;
    }

    public static Meta of(Pageable pageable, int totalCount) {
        return new Meta(pageable, totalCount);
    }
}
