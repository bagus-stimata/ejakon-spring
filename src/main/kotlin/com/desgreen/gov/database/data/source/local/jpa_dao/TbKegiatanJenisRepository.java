package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbKegiatanJenis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbKegiatanJenisRepository extends JpaRepository<TbKegiatanJenis, Integer> {
    TbKegiatanJenis findById(int id);
    List<TbKegiatanJenis> findByKode1(String kode1);

    @Query("SELECT u FROM TbKegiatanJenis u WHERE u.description LIKE :description_")
    List<TbKegiatanJenis> findByDescription(@Param("description_") String description_);


}
