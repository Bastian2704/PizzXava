package com.ACFMO.PizzXava.modelo;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter @Setter
@View(members =
        "nombre;" +
                "descripcion;" +
                "porcentaje;" +
                "fechaInicio, fechaFin;" +
                "productos;" +
                "categorias"
)
public class Promocion {

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

    @Required
    @Digits(integer = 3, fraction = 2)
    double porcentaje;

    @Required
    LocalDate fechaInicio;

    @Required
    LocalDate fechaFin;

    @ManyToMany
    @JoinTable(
            name = "PROMOCION_PRODUCTO",
            joinColumns = @JoinColumn(name = "id_promocion"),
            inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    @ListProperties("nombre, precio, categoria.nombre")
    Collection<Producto> productos;

    @ManyToMany
    @JoinTable(
            name = "PROMOCION_CATEGORIA",
            joinColumns = @JoinColumn(name = "id_promocion"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    @ListProperties("nombre")
    Collection<Categoria> categorias;

    public boolean estaActiva() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
    }
}