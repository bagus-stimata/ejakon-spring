package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.TbJenisPengerasan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TbJenisPengerasanRepository extends JpaRepository<TbJenisPengerasan, Integer> {
    TbJenisPengerasan findById(int id);
    List<TbJenisPengerasan> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisPengerasan u WHERE u.description LIKE :description_")
    List<TbJenisPengerasan> findByDescription(@Param("description_") String description_);


}
