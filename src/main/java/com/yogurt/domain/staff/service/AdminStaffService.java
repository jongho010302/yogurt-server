package com.yogurt.domain.staff.service;

import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.domain.staff.dto.admin.request.SaveStaffRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminStaffService {

    Staff getById(Long id);

    List<Staff> getAllWithFilter(Pageable pageable, Boolean isDeleted);

    Staff saveStaff(SaveStaffRequest saveStaffRequest);

    void delete(Long id);

    void resetPassword(long userId);

    boolean existsById(Long id);
}