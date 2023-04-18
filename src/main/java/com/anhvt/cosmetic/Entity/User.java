package com.anhvt.cosmetic.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", insertable = true, referencedColumnName = "id")
    private Role role;

//    @NotBlank(message="Not blank")
    @Column(name="fullname")
    private String fullname;

    @NotBlank(message="Not blank")
    @Column(name="username")
    private String username;

    @NotBlank(message="Not blank")
    @Column(name="password")
    private String password;

    @NotBlank(message="Not blank")
    @Email(message="Email is invalid")
    @Column(name="email")
    private String email;

    @NotBlank(message="Not blank")
    @Column(name="phone")
    private String phone;

    @NotBlank(message="Not blank")
    @Column(name="address")
    private String address;

    @Column(name="created_at")
    private String created_at;

    @Column(name="status")
    private Byte status;

//    @OneToMany
//    private Set<Cart> carts;
//
//    @OneToMany
//    private Set<Order> orders;
//
//    @OneToMany
//    private Set<Delivery> deliveries;

//    public String bCryptPasswordEncoder(){
//        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//        return bcrypt.encode(getPassword());
//    }
//    public Date parse(Timestamp timestamp){
//        timestamp =new Timestamp(getCreated_at());
//        Date date =new Date(timestamp.getTime());
//        return date;
//    }
}
