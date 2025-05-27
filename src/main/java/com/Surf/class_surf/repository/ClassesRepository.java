package com.Surf.class_surf.repository;

import com.Surf.class_surf.models.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
}
