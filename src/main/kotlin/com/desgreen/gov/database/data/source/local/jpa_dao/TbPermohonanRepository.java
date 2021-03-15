package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbPermohonan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbPermohonanRepository extends JpaRepository<TbPermohonan, Long> {
    TbPermohonan findById(long id);
    // List<TbPermohonan> findByKode1(String kode1);

    // @Query("SELECT u FROM TbPermohonan u WHERE u.description LIKE :description_")
    // List<TbPermohonan> findByDescription(@Param("description_") String description_);

    @Query("SELECT u FROM TbPermohonan u WHERE u.fuserBean.id = :userBeanId")
    List<TbPermohonan> findByUserId(@Param("userBeanId") int userBeanId);


}
