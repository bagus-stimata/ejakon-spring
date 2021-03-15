package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbProvinsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbProvinsiRepository extends JpaRepository<TbProvinsi, Integer> {
    TbProvinsi findById(int id);
    List<TbProvinsi> findByKode1(String kode1);

    @Query("SELECT u FROM TbProvinsi u WHERE u.description LIKE :description_")
    List<TbProvinsi> findByDescription(@Param("description_") String description_);


}
