package com.ACFMO.PizzXava.modelo;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.validation.constraints.Digits;


@Entity
@Getter @Setter
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList
    Producto producto;

    @Required
    @Digits(integer = 4, fraction = 0)
    Integer cantidad;

    @ReadOnly
    @Money
    @Depends("producto.precio, cantidad")
    public BigDecimal getSubtotal() {
        if (producto == null || producto.getPrecio() == null) return BigDecimal.ZERO;
        return producto.getPrecio().multiply(new BigDecimal(cantidad));
    }

    @ReadOnly
    @Money
    @Depends("producto.precio, cantidad")
    public BigDecimal getSubtotalConDescuento() {
        BigDecimal base = getSubtotal();
        if (base.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;

        double mejorDescuento = obtenerMejorDescuento();
        if (mejorDescuento == 0) return base;

        BigDecimal factor = BigDecimal.ONE
                .subtract(new BigDecimal(mejorDescuento).divide(new BigDecimal(100)));
        return base.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }

    @ReadOnly
    @Digits(integer = 3, fraction = 2)
    @Depends("producto.precio, cantidad")
    public double getDescuentoAplicado() {
        return obtenerMejorDescuento();
    }

    private double obtenerMejorDescuento() {
        if (producto == null) return 0;

        double mejor = 0;

        try {
            List<Promocion> todasPromociones = org.openxava.jpa.XPersistence
                    .getManager()
                    .createQuery("SELECT p FROM Promocion p", Promocion.class)
                    .getResultList();

            for (Promocion promo : todasPromociones) {
                if (!promo.estaActiva()) continue;

                boolean aplica = false;

                if (promo.getProductos() != null) {
                    for (Producto p : promo.getProductos()) {
                        if (p.getId().equals(producto.getId())) {
                            aplica = true;
                            break;
                        }
                    }
                }

                if (!aplica && promo.getCategorias() != null
                        && producto.getCategoria() != null) {
                    for (Categoria c : promo.getCategorias()) {
                        if (c.getId().equals(producto.getCategoria().getId())) {
                            aplica = true;
                            break;
                        }
                    }
                }

                if (aplica && promo.getPorcentaje() > mejor) {
                    mejor = promo.getPorcentaje();
                }
            }
        } catch (Exception e) {
            return 0;
        }

        return mejor;
    }
}