package com.yogurt.staff.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffRepositoryCustom {

    List<Staff> getAllWithFilter(Pageable pageable, Boolean isDisabled);
}
