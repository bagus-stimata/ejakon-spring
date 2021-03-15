package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbPrasaranaPics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbPrasaranaPicsRepository extends JpaRepository<TbPrasaranaPics, Long> {
    TbPrasaranaPics findById(long id);
    List<TbPrasaranaPics> findByTitle(String title);

    @Query("SELECT u FROM TbPrasaranaPics u WHERE u.description LIKE :description_")
    List<TbPrasaranaPics> findByDescription(@Param("description_") String description_);


}
