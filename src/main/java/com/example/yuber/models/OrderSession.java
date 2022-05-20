package com.example.yuber.models;

public final class OrderSession { // Singleton
    private static OrderSession instance = new OrderSession();
    private static OrderModel order;

    private OrderSession() {}

    public static OrderSession getInstance() {
        return instance;
    }

    public static void setOrder(OrderModel order) {
        OrderSession.order = order;
    }

    public static OrderModel getOrder() {
        return order;
    }

    public static void deleteOrder() {
        if (order != null)
            order = null;
    }
}