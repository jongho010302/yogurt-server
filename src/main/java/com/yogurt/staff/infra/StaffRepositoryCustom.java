package com.yogurt.staff.infra;

import com.yogurt.staff.domain.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffRepositoryCustom {

    List<Staff> getAllWithFilter(Pageable pageable, Boolean isDisabled);
}
