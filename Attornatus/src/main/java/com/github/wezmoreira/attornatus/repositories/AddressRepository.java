package com.github.wezmoreira.attornatus.repositories;

import com.github.wezmoreira.attornatus.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Person p JOIN p.address a WHERE p.id = :personId AND a.mainAddress = true")
    Address findPersonByMainAddress(@Param("personId") Long personId);
}
