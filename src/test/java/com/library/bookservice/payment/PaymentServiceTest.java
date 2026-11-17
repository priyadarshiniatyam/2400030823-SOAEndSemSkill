
package com.library.bookservice.payment;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {

    PaymentService service = new PaymentService();

    @Test
    void testValidPayment() {
        assertThat(service.processPayment("PAY101", 500))
            .isEqualTo("Payment processed successfully");
    }

    @Test
    void testInvalidAmount() {
        assertThatThrownBy(() ->
            service.processPayment("PAY102", -100))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid payment amount");
    }

    @Test
    void testMissingDetails() {
        assertThatThrownBy(() ->
            service.processPayment("", 500))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Payment details are missing");
    }
}
