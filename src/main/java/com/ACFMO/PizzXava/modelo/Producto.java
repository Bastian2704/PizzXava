package com.ACFMO.PizzXava.modelo;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @Column(length = 50)
    @Required
    String nombre;

    @Column(length = 200)
    @TextArea
    String descripcion;

    @Money
    @Required
    BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList
    Categoria categoria;
}
