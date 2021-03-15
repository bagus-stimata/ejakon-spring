package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbKecamatan;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TbKecamatanRepository extends JpaRepository<TbKecamatan, Integer> {
    TbKecamatan findById(int id);
    // List<TbKecamatan> findByKode1(String kode1);

    // @Query("SELECT u FROM TbKecamatan u WHERE u.description LIKE :description_")
    // List<TbKecamatan> findByDescription(@Param("description_") String description_);


}
