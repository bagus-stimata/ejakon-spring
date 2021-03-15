package com.desgreen.gov.database.data.source.local.jpa_dao;

import com.desgreen.gov.database.data.source.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Kenapa tidak perlu untuk di tuliskan annotation @Repository atau @RepositoryBean
// Karena pada file Induknya "JpaRepository" sudah diberikan Annotation tersebut

public interface PersonJPARepository extends JpaRepository<Person, Integer>{
    /*
    OTOMATIS TERDAPAT STANDART CRUD STANDART
    */

    //Automatic Query: This Is Powerful tapi harus Match Exact
    // Person findById(Integer id);
    List<Person> findByName(String name);

    @Query("SELECT u FROM Person u WHERE u.id = 2")
    List<Person> findAllActivePerson();

    //Native Query tidak Case Sensitif tabel
    @Query(
        value = "SELECT * FROM Person u WHERE u.id = 1", 
        nativeQuery = true)
      List<Person> findAllActivePersonNative();
      
      @Query("SELECT u FROM Person u WHERE u.name LIKE ?1 and u.address LIKE ?2")
      Person findUserByNameAndAddress(String nama, String alamat);


      @Query("SELECT u FROM Person u WHERE u.name LIKE :name_ and u.address LIKE :alamat")
      Person findUserByNameAndAddress_NamedParam(@Param("name_") String name_, @Param("alamat") String alamat);

        @Modifying
        @Query( value = "INSERT INTO Person (id, name, address) values (:id_, :name_, :address_ )",
        nativeQuery = true)
        void insertUser(@Param("id_") Integer id_, @Param("name_") String name_, @Param("address_") String status_ );
}