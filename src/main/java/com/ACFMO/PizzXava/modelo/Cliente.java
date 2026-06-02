package com.ACFMO.PizzXava.modelo;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @Column(length = 50)
    @Required
    String nombre;

    @Column(length = 15)
    String telefono;

    @Column(length = 100)
    String direccion;

    @Column(length = 60)
    String email;
}
