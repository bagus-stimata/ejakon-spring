package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbJenisBuktiKepemilikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbJenisBuktiKepemilikanRepository extends JpaRepository<TbJenisBuktiKepemilikan, Integer> {
    TbJenisBuktiKepemilikan findById(int id);
    List<TbJenisBuktiKepemilikan> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisBuktiKepemilikan u WHERE u.description LIKE :description_")
    List<TbJenisBuktiKepemilikan> findByDescription(@Param("description_") String description_);


}
