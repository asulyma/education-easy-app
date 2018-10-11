package com.global.shop.repository;

import com.global.shop.model.learning.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class for working with DataBases.
 * Only for {@link Section} entity.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findAllByCourseName(String name);

    Section findByCourseNameAndId(String name, Long id);

}
