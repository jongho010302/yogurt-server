package com.yogurt.staff.infra;

import com.yogurt.staff.domain.Staff;
import com.yogurt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StaffRepository extends JpaRepository<Staff, Long>, StaffRepositoryCustom {

    Optional<Staff> findByUser(User user);
}
