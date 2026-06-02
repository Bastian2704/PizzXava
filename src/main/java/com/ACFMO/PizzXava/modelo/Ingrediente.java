package com.ACFMO.PizzXava.modelo;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Getter @Setter
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @Column(length = 50)
    @Required
    String nombre;

    @Column(length = 20)
    @Required
    String unidadMedida;

    @Digits(integer = 8, fraction = 2)
    double stockActual;

    @Digits(integer = 8, fraction = 2)
    double stockMinimo;
}
