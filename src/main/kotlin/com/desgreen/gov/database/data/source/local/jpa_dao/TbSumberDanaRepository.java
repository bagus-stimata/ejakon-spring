package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbSumberDana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TbSumberDanaRepository extends JpaRepository<TbSumberDana, Integer> {
    TbSumberDana findById(int id);
    List<TbSumberDana> findByKode1(String kode1);

    // @Query("SELECT u FROM TbSumberDana u WHERE u.description LIKE :description_")
    // List<TbSumberDana> findByDescription(@Param("description_") String description_);


}
