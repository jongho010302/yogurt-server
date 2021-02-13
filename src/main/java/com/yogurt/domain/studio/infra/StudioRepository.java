package com.yogurt.domain.studio.infra;

import com.yogurt.domain.studio.domain.Studio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudioRepository extends JpaRepository<Studio, Long> {

    boolean existsById(Long id);
}
