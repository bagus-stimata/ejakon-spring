package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbKlasifikasiKegiatan;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TbKlasifikasiKegiatanRepository extends JpaRepository<TbKlasifikasiKegiatan, Integer> {
    TbKlasifikasiKegiatan findById(int id);
    // List<TbKlasifikasiKegiatan> findByKode1(String kode1);

    // @Query("SELECT u TbKlasifikasiKegiatan TbOpd u WHERE u.description LIKE :description_")
    // List<TbKlasifikasiKegiatan> findByDescription(@Param("description_") String description_);


}
