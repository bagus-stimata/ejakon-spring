package com.desgreen.gov.database.data.source.local.jpa_dao;


import com.desgreen.gov.database.data.source.entity.TbStatusWilayah;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TbStatusWilayahRepository extends JpaRepository<TbStatusWilayah, Integer> {
    TbStatusWilayah findById(int id);
    // List<TbStatusWilayah> findByKode1(String kode1);

    // @Query("SELECT u FROM TbOpd u WHERE u.description LIKE :description_")
    // List<TbStatusWilayah> findByDescription(@Param("description_") String description_);


}
