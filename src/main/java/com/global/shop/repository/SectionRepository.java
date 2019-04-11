package com.global.shop.repository;

import com.global.shop.model.learning.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class for working with DataBases. Only for {@link SectionEntity} entity.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    List<SectionEntity> findAllByCourseName(String name);

    SectionEntity findByCourseNameAndId(String name, Long id);

}
