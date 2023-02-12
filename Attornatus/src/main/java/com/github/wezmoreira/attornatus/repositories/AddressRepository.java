package com.github.wezmoreira.attornatus.repositories;

import com.github.wezmoreira.attornatus.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
