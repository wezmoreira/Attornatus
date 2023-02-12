package com.github.wezmoreira.attornatus.repositories;

import com.github.wezmoreira.attornatus.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p JOIN FETCH p.address a WHERE a.mainAddress = true ORDER BY a.mainAddress DESC")
    List<Person> findAllPersonByMainAddress();

    @Query("SELECT p FROM Person p JOIN FETCH p.address a WHERE p.id=:id ORDER BY a.mainAddress DESC")
    Person getPersonByMainAddress(@Param("id") Long id);
}
