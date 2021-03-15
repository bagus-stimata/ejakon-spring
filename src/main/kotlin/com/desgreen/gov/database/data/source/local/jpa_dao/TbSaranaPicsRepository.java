package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbSaranaPics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TbSaranaPicsRepository extends JpaRepository<TbSaranaPics, Long> {
    TbSaranaPics findById(long id);
    List<TbSaranaPics> findByTitle(String title);

    // @Query("SELECT u FROM TbSaranaPics u WHERE u.description LIKE :description_")
    // List<TbSaranaPics> findByDescription(@Param("description_") String description_);


}
