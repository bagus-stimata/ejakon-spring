package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbPrasarana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbPrasaranaRepository extends JpaRepository<TbPrasarana, Long> {
    TbPrasarana findById(long id);
    List<TbPrasarana> findByKode1(String kode1);

    @Query("SELECT u FROM TbPrasarana u WHERE u.description LIKE :description_")
    List<TbPrasarana> findByDescription(@Param("description_") String description_);


}
