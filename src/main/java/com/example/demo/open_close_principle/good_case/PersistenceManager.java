package com.example.demo.open_close_principle.good_case;

public class PersistenceManager {
    InvoicePersistence invoicePersistence;

    public PersistenceManager(InvoicePersistence invoicePersistence) {
        this.invoicePersistence = invoicePersistence;
    }
}
