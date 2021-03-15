package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbKegiatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbKegiatanRepository extends JpaRepository<TbKegiatan, Long> {
    TbKegiatan findById(long id);
    List<TbKegiatan> findByKode1(String kode1);

    @Query("SELECT u FROM TbKegiatan u WHERE u.description1 LIKE :description1_")
    List<TbKegiatan> findByDescription(@Param("description1_") String description1_);


}
