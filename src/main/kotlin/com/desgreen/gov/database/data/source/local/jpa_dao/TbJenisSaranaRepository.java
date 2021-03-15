package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbJenisSarana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbJenisSaranaRepository extends JpaRepository<TbJenisSarana, Integer> {
    TbJenisSarana findById(int id);
    List<TbJenisSarana> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisSarana u WHERE u.description LIKE :description_")
    List<TbJenisSarana> findByDescription(@Param("description_") String description_);


}
