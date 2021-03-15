package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbDesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbDesaRepository extends JpaRepository<TbDesa, Integer> {
    TbDesa findById(int id);
    List<TbDesa> findByKode1(String kode1);

    @Query("SELECT u FROM TbDesa u WHERE u.description LIKE :description_")
    List<TbDesa> findByDescription(@Param("description_") String description_);


}
