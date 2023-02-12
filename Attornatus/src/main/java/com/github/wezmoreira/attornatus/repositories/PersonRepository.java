package com.github.wezmoreira.attornatus.repositories;

import com.github.wezmoreira.attornatus.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
