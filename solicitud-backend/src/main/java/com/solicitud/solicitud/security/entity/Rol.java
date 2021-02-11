package com.solicitud.solicitud.security.entity;

import com.solicitud.solicitud.security.enums.RolNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ma_rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rol_nombre")
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return this.id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return this.rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
