package com.example.bookStore.controller;


import com.example.bookStore.model.entities.Invoice;
import com.example.bookStore.model.entities.Offer;
import com.example.bookStore.service.InvoicePdfService;
import com.example.bookStore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpHeaders;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("bookStore")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoicePdfService invoicePdfService;

    @GetMapping("/invoices")
    public String showInvoicesPage(Model model) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("invoices", invoices);
        return "Admin_view/payments";
    }


    @GetMapping("/invoice/pdf/{id}")
    public ResponseEntity<InputStreamResource> downloadInvoicePdf(@PathVariable int id) {
        ByteArrayInputStream bis = invoicePdfService.generateInvoicePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=invoice_" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
