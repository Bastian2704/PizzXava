package com.ACFMO.PizzXava.modelo;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;


@Entity
@Getter @Setter
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList
    Ingrediente ingrediente;

    @Required
    @Digits(integer = 8, fraction = 2)
    double cantidad;

    @Required
    @Money
    BigDecimal precioUnitario;

    @ReadOnly
    @Money
    @Depends("cantidad, precioUnitario")
    public BigDecimal getSubtotal() {
        if (precioUnitario == null) return BigDecimal.ZERO;
        return precioUnitario.multiply(new BigDecimal(cantidad));
    }
    @ManyToOne(fetch = FetchType.LAZY)
    Compra compra;
}
