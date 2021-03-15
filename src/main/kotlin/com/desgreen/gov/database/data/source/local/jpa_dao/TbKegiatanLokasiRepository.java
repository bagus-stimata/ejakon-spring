package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbKegiatanLokasi;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TbKegiatanLokasiRepository extends JpaRepository<TbKegiatanLokasi, Integer> {
    TbKegiatanLokasi findById(int id);
    // List<TbKegiatanLokasi> findByKode1(String kode1);

    // @Query("SELECT u FROM TbDesa u WHERE u.description LIKE :description_")
    // List<TbKegiatanLokasi> findByDescription(@Param("description_") String description_);

}
