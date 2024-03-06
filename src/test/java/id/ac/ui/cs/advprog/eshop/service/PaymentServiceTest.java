package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("39a06a92-87ae-4cf4-8c10-35b956699245");
        product1.setProductName("Sapu Ajaib Abdul");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("66a35b43-431c-4868-b078-00b927557a65", products, 1708560000L,"Siti");
        orders.add(order1);

        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("3cadb8b2-89de-4467-b167-e952cb07806c", PaymentMethod.VOUCHER_CODE.getValue(),
                orders.get(1), paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC0098");
        Payment payment2 = new Payment("7eb97d10-6a2a-4195-a22e-a776a245bba9", PaymentMethod.CASH_ON_DELIVERY.getValue(),
                orders.get(1), paymentData2);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        UUID uuid = UUID.randomUUID();
        String paymentId = uuid.toString();
        Payment payment = new Payment(paymentId, PaymentMethod.VOUCHER_CODE.getValue(), orders.get(1), payments.get(1).getPaymentData());

        doReturn(null).when(paymentRepository).findById(paymentId);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(paymentId, orders.get(1), PaymentMethod.VOUCHER_CODE.getValue(), payments.get(1).getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfAlreadyExist() {
        UUID uuid = UUID.randomUUID();
        String paymentId = uuid.toString();
        Payment payment = new Payment(paymentId, PaymentMethod.VOUCHER_CODE.getValue(), orders.get(1), payments.get(1).getPaymentData());

        doReturn(payment).when(paymentRepository).findById(paymentId);
        Payment result = paymentService.addPayment(paymentId, orders.get(1), PaymentMethod.VOUCHER_CODE.getValue(), payments.get(1).getPaymentData());
        verify(paymentRepository, times(1)).findById(paymentId);
        verify(paymentRepository, times(0)).save(any(Payment.class));
        assertNull(result);
    }

    @Test
    void testSetValidStatus() {
        Payment payment = payments.get(1);

        Payment payment1 = new Payment(payment.getId(), payment.getMethod(), payment.getOrder(), payment.getPaymentData());
        Payment result1 = paymentService.setStatus(payment1, "SUCCESS");
        assertEquals(payment1.getId(), result1.getId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result1.getOrder().getStatus());

        Payment payment2 = new Payment(payment.getId(), payment.getMethod(), payment.getOrder(), payment.getPaymentData());
        Payment result2 = paymentService.setStatus(payment1, "REJECTED");
        assertEquals(payment2.getId(), result2.getId());
        assertEquals(OrderStatus.FAILED.getValue(), result1.getOrder().getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("0000");
        assertNull(paymentService.getPayment("0000"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayment();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments, results);
    }
}
