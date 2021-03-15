package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbRekomendasi;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TbRekomendasiRepository extends JpaRepository<TbRekomendasi, Long> {
    TbRekomendasi findById(long id);
    // List<TbRekomendasi> findByKode1(String kode1);

    // @Query("SELECT u FROM TbRekomendasi u WHERE u.description LIKE :description_")
    // List<TbRekomendasi> findByDescription(@Param("description_") String description_);

    // @Query("SELECT u FROM TbPermohonan u WHERE u.fuserBean.id = :userBeanId")
    // List<TbRekomendasi> findByUserId(@Param("userBeanId") int userBeanId);


}
