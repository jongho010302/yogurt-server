package com.yogurt.domain.staff.service.common;

import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.domain.staff.exception.StaffNotFoundException;
import com.yogurt.domain.staff.infra.common.CommonStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommonStaffServiceImpl implements CommonStaffService {

    private final CommonStaffRepository repository;

    @Transactional
    public Staff getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new StaffNotFoundException(id));
    }

    @Transactional
    public Staff create(Staff staff) {
        return repository.save(staff);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
