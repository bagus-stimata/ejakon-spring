package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbArahanFungsiLahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbArahanFungsiLahanRepository extends JpaRepository<TbArahanFungsiLahan, Integer> {
    TbArahanFungsiLahan findById(int id);
    List<TbArahanFungsiLahan> findByKode1(String kode1);

    @Query("SELECT u FROM TbOpd u WHERE u.description LIKE :description_")
    List<TbArahanFungsiLahan> findByDescription(@Param("description_") String description_);


}
