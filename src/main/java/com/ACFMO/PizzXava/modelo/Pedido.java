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
        "fecha, estado;" +
                "cliente;" +
                "detalles;" +
                "total"
)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class)
    LocalDate fecha;

    @Required
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    EstadoPedido estado = EstadoPedido.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList
    Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    @ListProperties("producto.nombre, cantidad, subtotal, descuentoAplicado, subtotalConDescuento")
    Collection<DetallePedido> detalles;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.subtotalConDescuento)")
    BigDecimal total;
}
