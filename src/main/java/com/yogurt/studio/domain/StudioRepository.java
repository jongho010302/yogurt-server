package com.yogurt.studio.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StudioRepository extends JpaRepository<Studio, Long> {

    boolean existsById(Long id);
}
