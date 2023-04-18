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
@Table(name="blog")
public class Blog {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="image")
    private String image;

    @Column(name="created_at", length = 10)
    private String created_at;

    @Column(name="status")
    private Byte status;

}
