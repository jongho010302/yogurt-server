package com.yogurt.domain.staff.infra.common;

import com.yogurt.domain.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommonStaffRepository extends JpaRepository<Staff, Long> {
}
