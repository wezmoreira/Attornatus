package com.github.wezmoreira.attornatus.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "RL_person_address",
            joinColumns = @JoinColumn(
                    name = "person_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "address_id"
            )
    )
    private List<Address> address;
}
