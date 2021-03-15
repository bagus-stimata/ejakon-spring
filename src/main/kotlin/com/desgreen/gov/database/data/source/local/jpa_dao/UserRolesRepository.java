package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.FUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRolesRepository extends JpaRepository<FUserRoles, Integer> {
    // Optional<FUser> findByEmail(String email);

    // Optional<FUser> findByUsername(String username);


}
