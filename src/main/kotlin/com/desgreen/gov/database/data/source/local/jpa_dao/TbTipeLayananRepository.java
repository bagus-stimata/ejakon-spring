package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbTipeLayanan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TbTipeLayananRepository extends JpaRepository<TbTipeLayanan, Integer> {
    TbTipeLayanan findById(int id);
    List<TbTipeLayanan> findByKode1(String kode1);

    // @Query("SELECT u FROM TbTipeLayanan u WHERE u.description LIKE :description_")
    // List<TbTipeLayanan> findByDescription(@Param("description_") String description_);


}
