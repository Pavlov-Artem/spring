package com.epam.esm.db.data;

import com.epam.esm.db.DAOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = DAOConstants.ORDER_TABLE)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DAOConstants.ORDER_ID)
    private Long id;
    @Column(name = DAOConstants.ORDER_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private Timestamp purchaseDate;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(targetEntity = OrderDetails.class, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
//    @JoinColumn(name = DAOConstants.ORDER_DETAILS_ORDER_ID)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Order(Timestamp purchaseDate, User user, List<OrderDetails> orderDetails) {
        this.purchaseDate = purchaseDate;
        this.user = user;
        this.orderDetails = orderDetails;
    }

    public Order(Timestamp purchaseDate, User user) {
        this.purchaseDate = purchaseDate;
        this.user = user;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(purchaseDate, order.purchaseDate) &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderDetails, order.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, user, orderDetails);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", user=" + user +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
