package com.example.demo.open_close_principle.bad_case;

import com.example.demo.single_responsibility_principle.good_case.Invoice;

public class InvoicePersistence {
    Invoice invoice;

    public InvoicePersistence(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToFile(String[] filename){
        // Creates a file with given name and writes the invoice
    }

    public void saveToDatabase(){
        // Saves the invoice to database
    }
}
