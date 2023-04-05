package com.example.orderserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "sche", catalog = "sche")
@Getter
@Setter
@ToString
public class OrderEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, insertable = false)
    private Integer id;

    @Column(name = "account_id", length = 64)
    private String accountId;

    @Column(name = "orderId", length = 64)
    private String orderId;

    @Column(name = "order_detail", length = 256)
    private String orderDetail;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(orderId, that.orderId)
                && Objects.equals(accountId, that.accountId)
                && Objects.equals(orderDetail, that.orderDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, orderId, orderDetail);
    }
}
