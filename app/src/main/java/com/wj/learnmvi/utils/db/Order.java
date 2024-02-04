package com.wj.learnmvi.utils.db;

/**
 * Author:WJ
 * Date:2024/1/26 10:18
 * Describe：
 */
public class Order {
    public long  id;
    public long userId;
    public String product;

    public Order(long id, long userId, String product) {
        this.id = id;
        this.userId = userId;
        this.product = product;
    }

    public Order( String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", product='" + product + '\'' +
                '}';
    }
}
