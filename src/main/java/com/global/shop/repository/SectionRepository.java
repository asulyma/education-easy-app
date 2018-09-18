package com.global.shop.repository;

import com.global.shop.model.learning.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findAllByCourseId(Long id);

}
