package com.yogurt.domain.staff.infra;

import com.yogurt.domain.staff.domain.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminStaffRepositoryCustom {

    List<Staff> getAllWithFilter(Pageable pageable, Boolean isDeleted);
}
