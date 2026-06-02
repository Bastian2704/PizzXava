package com.ACFMO.PizzXava.modelo;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentLocalDateCalculator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter @Setter
@View(members =
        "fecha;" +
                "proveedor;" +
                "detalles;" +
                "total"
)
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class)
    LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList
    Proveedor proveedor;

    @OneToMany(mappedBy = "compra")
    @ListProperties("ingrediente.nombre, ingrediente.unidadMedida, cantidad, precioUnitario, subtotal")
    Collection<DetalleCompra> detalles;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.subtotal)")
    BigDecimal total;

}
