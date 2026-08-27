package com.clientes.clientes_api.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente_sebas")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "seqidcliente", allocationSize = 1)
    private Long id;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false, unique = true)
    private String correo;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private Estado estado;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public Cliente(){

    }

    public Cliente(Long id, String nombre, String correo, Estado estado, LocalDateTime fechaCreacion){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    @PrePersist
    protected void OnCreate(){
        this.fechaCreacion = LocalDateTime.now();
        if(this.estado == null){
            this.estado = Estado.ACTIVO;
        }
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCorreo(){
        return this.correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public Estado getEstado(){
        return this.estado;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion(){
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}