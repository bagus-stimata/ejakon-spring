package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbRencanaPemanfaatan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TbRencanaPemanfaatanRepository extends JpaRepository<TbRencanaPemanfaatan, Integer> {
    TbRencanaPemanfaatan findById(int id);
    List<TbRencanaPemanfaatan> findByKode1(String kode1);

    // @Query("SELECT u FROM TbRencanaPemanfaatan u WHERE u.description LIKE :description_")
    // List<TbRencanaPemanfaatan> findByDescription(@Param("description_") String description_);


}
