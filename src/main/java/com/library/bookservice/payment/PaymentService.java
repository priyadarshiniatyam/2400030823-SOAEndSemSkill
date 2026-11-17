package com.library.bookservice.payment;

public class PaymentService {

    public boolean validatePaymentDetails(
            String paymentId, double amount) {

        if (paymentId == null || paymentId.isBlank()) {
            throw new IllegalArgumentException(
                "Payment details are missing");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Invalid payment amount");
        }

        return true;
    }

    public String processPayment(
            String paymentId, double amount) {

        validatePaymentDetails(paymentId, amount);

        return "Payment processed successfully";
    }
}
