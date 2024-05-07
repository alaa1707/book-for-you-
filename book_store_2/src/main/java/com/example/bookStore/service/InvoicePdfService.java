package com.example.bookStore.service;


import com.example.bookStore.model.entities.Invoice;
import com.example.bookStore.model.entities.OrderItem;
import com.example.bookStore.model.repo.InvoiceRepo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class InvoicePdfService {

    @Autowired
    private InvoiceRepo invoiceRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrderService orderService;

    public ByteArrayInputStream generateInvoicePdf(int orderId) {
        int invoiceId= orderService.getOrder(orderId).getInvoice().getId();
        Invoice invoice= invoiceService.getInvoice(invoiceId).get();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();


            addInvoiceDetails(document, invoice);
            addOrderItems(document, invoice.getOrder().getOrderItems());
            addTotalAmount(document, invoice.getTotalPrice());

            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addInvoiceDetails(Document document, Invoice invoice) throws DocumentException {
        document.add(new Paragraph("Invoice ID: " + invoice.getId()));
        document.add(new Paragraph("Invoice Date: " + invoice.getDate()));
        document.add(new Paragraph("Customer Name: " + invoice.getOrder().getUser().getName()));
    }

    private void addOrderItems(Document document, List<OrderItem> orderItems) throws DocumentException {
        document.add(new Paragraph("Order Items: "));
        for (OrderItem orderItem : orderItems) {
            document.add(new Paragraph("Book Title: " + orderItem.getBook().getBookName()));
            document.add(new Paragraph("Book Quantity: " + orderItem.getQuantity()));
            document.add(new Paragraph("---------------"));
        }
    }

    private void addTotalAmount(Document document, double totalPrice) throws DocumentException {
        document.add(new Paragraph("Total Price: " + totalPrice));
    }
}
