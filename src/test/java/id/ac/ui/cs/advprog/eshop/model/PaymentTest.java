package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Order order;

    @BeforeEach
    void setup() {
        Product product1 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        order = new Order("123456789-skibidi-order-id", products, 128560000L, "Sigma");
    }

    @Test
    void testCreatePaymentWithVoucherSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("6789-skibidi12345", "VOUCHER", order, paymentData);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("6789-skibidi12345", "MOGGING", order, paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherWithInvalidPaymentData() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("notVoucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("6789-skibidi12345", "VOUCHER", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());

    }

    @Test
    void testCreatePaymentWith13CharacterVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678");
        Payment payment = new Payment("6789-skibidi12345", "VOUCHER", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());

    }

    @Test
    void testCreatePaymentWithVoucherWithoutEshop() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "1234ABC56789ESHOP");

        Payment payment = new Payment("6789-skibidi12345", "VOUCHER", order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());

    }
    @Test
    void testCreatePaymentWithNumberLessThanEightOnVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("6789-skibidi12345", "VOUCHER", order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testCreatePaymentWithCodSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Silly");
        paymentData.put("deliveryFee", "69420");
        Payment payment = new Payment("6789-skibidi12345", "COD", order, paymentData);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testCreatePaymentCodWithoutAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("deliveryFee", "69420");
        Payment payment = new Payment("6789-skibidi12345", "COD", order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testCreatePaymentCodWithoutDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Silly");
        Payment payment = new Payment("6789-skibidi12345", "COD", order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("6789-skibidi12345", "VOUCHER", order, paymentData);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }
    @Test
    void testSetStatusToValidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "MEWING");
        Payment payment = new Payment("6789-skibidi12345", "VOUCHER", order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }
}