package com.anhvt.cosmetic.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="role")
public class Role {
    public static enum RoleName{
    ADMIN, CUSTOMER
}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

//    public boolean isAdmin(){
//        return this.name == RoleName.ADMIN;
//    }
//    public boolean isCustomer(){
//        return this.name == RoleName.CUSTOMER;
//    }

}
