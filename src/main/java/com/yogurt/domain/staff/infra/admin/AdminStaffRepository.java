package com.yogurt.domain.staff.infra.admin;

import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminStaffRepository extends JpaRepository<Staff, Long>, AdminStaffRepositoryCustom {

    Optional<Staff> findByUser(User user);
}
