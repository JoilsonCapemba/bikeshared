package com.uan.bikeshared.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "telephone"))
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    private String password;

    private String email;

    @Column(unique = true)
    private String telephone;

    private boolean estado;

    private int saldo;

    private int type;

    private String macAddress;
}
