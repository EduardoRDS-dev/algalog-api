package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.entity.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
}
