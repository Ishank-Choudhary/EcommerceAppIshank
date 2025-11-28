package com.ecommerce.sb_ecom_project.model.user;

import com.ecommerce.sb_ecom_project.model.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userID")
    private Long userID;
    @NotBlank
    @Size(max=25)
    @Column(name="username")
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name="email")
    private String email;
    @NotBlank
    @Size(max=20)
    private String password;

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Setter
    @Getter
//  This tells JPA what operations should “cascade” (i.e., be automatically applied) to the related entities.
//  PERSIST and MERGE basically will save the data automatically when one of them is updated or saved
//  then the other one will also save
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id") //user_id → foreign key to User & role_id → foreign key to Role
    )
    private Set<Role> roles = new HashSet<>();

//  USER and ADDRESS mapping
//  when we have a @ManyToMany table in that case we have to implement the @joinTable because you can’t store the foreign key in just one table —
//  otherwise, one side could only link to one record.
    @Setter
    @Getter
    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="user_address",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="address_id"))
    private List<Address> addresses = new ArrayList<>();

//  USER and PRODUCTS mapping
//  the table which has the mapped by is not the owning side of the table relationship
//  in below case the PRODUCT is the owner of the user FOREIGN KEY
    @Setter
    @Getter
    @OneToMany(mappedBy = "user",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = true)
    private Set<Product> products;

}




















