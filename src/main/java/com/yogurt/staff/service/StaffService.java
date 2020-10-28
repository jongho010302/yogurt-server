package com.yogurt.staff.service;

import com.yogurt.staff.domain.Staff;
import com.yogurt.staff.dto.SaveStaffRequest;
import com.yogurt.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {

    Staff getById(Long id);

    Staff getByUser(User user);

    List<Staff> getAllWithFilter(Pageable pageable, Boolean isDisabled);

    Staff saveStaff(SaveStaffRequest saveStaffRequest);

    void deactivate(Long id);

    void resetPassword(long userId);

    boolean existsById(Long id);
}