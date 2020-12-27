package com.yogurt.api.staff.service;

import com.yogurt.api.staff.domain.Staff;
import com.yogurt.api.staff.dto.SaveStaffRequest;
import com.yogurt.api.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {

    Staff getById(Long id);

    List<Staff> getAllWithFilter(Pageable pageable, Boolean isDeleted);

    Staff saveStaff(SaveStaffRequest saveStaffRequest);

    void delete(Long id);

    void resetPassword(long userId);

    boolean existsById(Long id);
}