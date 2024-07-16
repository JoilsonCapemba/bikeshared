package com.uan.bikeshared.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    private String password;

    private String email;

    private String telephone;

    private boolean estado;

    private int saldo;

    private int type;

    private String macAddress;
}
