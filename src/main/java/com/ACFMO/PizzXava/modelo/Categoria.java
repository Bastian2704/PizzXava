package com.ACFMO.PizzXava.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @Column(length = 50)
    @Required
    String nombre;
}
