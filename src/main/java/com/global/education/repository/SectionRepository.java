package com.global.education.repository;

import com.global.education.model.learning.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    List<SectionEntity> findAllByCourseName(String name);

    SectionEntity findByCourseNameAndId(String name, Long id);

}
