package com.yogurt.api.studio.infra;

import com.yogurt.api.studio.domain.Studio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudioRepository extends JpaRepository<Studio, Long> {

    boolean existsById(Long id);
}
