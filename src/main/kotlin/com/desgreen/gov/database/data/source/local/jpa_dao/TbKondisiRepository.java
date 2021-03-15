package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbKondisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbKondisiRepository extends JpaRepository<TbKondisi, Integer> {
    TbKondisi findById(int id);
    List<TbKondisi> findByKode1(String kode1);

    @Query("SELECT u FROM TbKondisi u WHERE u.description LIKE :description_")
    List<TbKondisi> findByDescription(@Param("description_") String description_);


}
