package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private List<Order> orders;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("39a06a92-87ae-4cf4-8c10-35b956699245");
        product1.setProductName("Sapu Ajaib Abdul");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("baa3ee56-9ce7-4608-9f83-a2672e8521b3");
        product2.setProductName("Kapas Putih Samara");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        Order order1 = new Order("66a35b43-431c-4868-b078-00b927557a65", this.products, 1708560000L,"Siti");
        Order order2 = new Order("21d7e0b9-9470-466e-acfe-c0527a745b6a", this.products, 1708570000L, "Cipto");

        this.orders.add(order1);
        this.orders.add(order2);
    }

    @Test
    void testPaymentByVoucherSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("68ea553b-a533-4395-99c2-f15a83e9d739", "VOUCHER_CODE",
                orders.get(1), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testPaymentByVoucherRejectedNot16Characters() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234");

        Payment payment = new Payment("68ea553b-a533-4395-99c2-f15a83e9d739", "VOUCHER_CODE",
                orders.get(1), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentByVoucherRejectedNotStartedByEshop() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "1234ABC5678");

        Payment payment = new Payment("68ea553b-a533-4395-99c2-f15a83e9d739", "VOUCHER_CODE",
                orders.get(1), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentByVoucherRejectedNot8NumericalChar() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCDEFG");

        Payment payment = new Payment("68ea553b-a533-4395-99c2-f15a83e9d739", "VOUCHER_CODE",
                orders.get(1), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentByCashOnDeliverySuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Kelapa 2");
        paymentData.put("deliveryFee", "22000");

        Payment payment = new Payment("4bb81b30-af6a-48f8-810f-0779dc04dd54", "CASH_ON_DELIVERY",
                orders.get(1), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testPaymentByCashOnDeliveryRejectedAddressEmpty() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "22000");

        Payment payment = new Payment("4bb81b30-af6a-48f8-810f-0779dc04dd54", "CASH_ON_DELIVERY",
                orders.get(1), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testPaymentByCashOnDeliveryRejectedDeliveryFeeEmpty() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Kelapa 2");
        paymentData.put("deliveryFee", "");

        Payment payment = new Payment("4bb81b30-af6a-48f8-810f-0779dc04dd54", "CASH_ON_DELIVERY",
                orders.get(1), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentByBankTransferSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BNI");
        paymentData.put("referenceCode", "12345678");

        Payment payment = new Payment("4bb81b30-af6a-48f8-810f-0779dc04dd54", "BANK_TRANSFER",
                orders.get(1), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testPaymentByBankTransferRejectedBankNameEmpty() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "12345678");

        Payment payment = new Payment("4bb81b30-af6a-48f8-810f-0779dc04dd54", "BANK_TRANSFER",
                orders.get(1), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentByBankTransferRejectedReferenceCodeEmpty() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BNI");
        paymentData.put("referenceCode", "");

        Payment payment = new Payment("4bb81b30-af6a-48f8-810f-0779dc04dd54", "BANK_TRANSFER",
                orders.get(1), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}
