package com.epam.esm.db.data;

import com.epam.esm.db.DAOConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = DAOConstants.ORDER_DETAILS_TABLE)
public class OrderDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DAOConstants.ORDER_DETAILS_ID)
    private Long id;
    @Column(name = DAOConstants.ORDER_DETAILS_COST)
    private BigDecimal cost;
    @OneToOne(targetEntity = GiftCertificate.class, fetch = FetchType.EAGER)
    @JoinColumn(name = DAOConstants.ORDER_DETAILS_CERTIFICATE_ID, nullable = false, updatable = false)
    private GiftCertificate giftCertificate;
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
//    @JoinColumn(name = DAOConstants.ORDER_DETAILS_ORDER_ID, nullable = false, updatable = false)
//    private Order order;

    public OrderDetails(BigDecimal cost, GiftCertificate giftCertificate, Order order) {
        this.cost = cost;
        this.giftCertificate = giftCertificate;

    }

    public OrderDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(giftCertificate, that.giftCertificate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, giftCertificate);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", cost=" + cost +
                ", giftCertificate=" + giftCertificate +
                '}';
    }
}
