package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.Chats;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatsRepository extends JpaRepository<Chats, Long> {
    Chats findById(long id);
    // List<Chats> findByKode1(String kode1);

    // @Query("SELECT u FROM Chats u WHERE u.description LIKE :description_")
    // List<Chats> findByDescription(@Param("description_") String description_);


}
