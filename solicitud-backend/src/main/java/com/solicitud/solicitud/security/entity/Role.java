package com.solicitud.solicitud.security.entity;

import com.solicitud.solicitud.security.enums.RoleName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ma_rol")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rol_nombre")
    private RoleName roleName;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return this.id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
