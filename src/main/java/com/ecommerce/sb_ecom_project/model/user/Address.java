package com.ecommerce.sb_ecom_project.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="address_id")
    private Integer addressID;
    @NotBlank
    private String houseNumber;
    @NotBlank
    @Size(max=100)
    private String street;
    @NotBlank
    private String landmark;

    @ManyToMany(mappedBy = "addresses")
    List<Users> users = new ArrayList<>();

}
