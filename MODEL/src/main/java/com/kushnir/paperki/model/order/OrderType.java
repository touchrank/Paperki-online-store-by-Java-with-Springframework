package com.kushnir.paperki.model.order;

public enum OrderType {
    CUSTOMER(1),
    ENTERPRISE(2);

    private int i;

    OrderType(int i) {
        this.i = i;
    }
}
