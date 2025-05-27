package com.Surf.class_surf.repository;

import com.Surf.class_surf.models.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    Optional<Classes> findByDate(LocalDate date);
    Optional<Classes> findByNameClass(String nameClass);
}
