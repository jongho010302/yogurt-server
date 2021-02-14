package com.yogurt.domain.staff.service.admin;

import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.domain.staff.dto.admin.request.SaveStaffRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {

    Staff getById(Long id);

    List<Staff> getAllWithFilter(Pageable pageable);

    Staff saveStaff(SaveStaffRequest saveStaffRequest);

    void deleteById(Long id);

    void resetPassword(long userId);

    boolean existsById(Long id);
}