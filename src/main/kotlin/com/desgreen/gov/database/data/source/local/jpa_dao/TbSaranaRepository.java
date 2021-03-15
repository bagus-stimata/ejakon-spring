package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbSarana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TbSaranaRepository extends JpaRepository<TbSarana, Long> {
    TbSarana findById(long id);
    List<TbSarana> findByKode1(String kode1);

    // @Query("SELECT u FROM TbSarana u WHERE u.description LIKE :description_")
    // List<TbSarana> findByDescription(@Param("description_") String description_);


}
