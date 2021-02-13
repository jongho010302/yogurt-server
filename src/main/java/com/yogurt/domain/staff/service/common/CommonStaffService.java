package com.yogurt.domain.staff.service.common;

import com.yogurt.domain.staff.domain.Staff;

public interface CommonStaffService {

    Staff getById(Long id);

    Staff create(Staff staff);

    void deleteById(Long id);

    boolean existsById(Long id);
}