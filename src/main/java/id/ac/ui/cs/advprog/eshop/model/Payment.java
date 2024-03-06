package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.order = order;
        this.paymentData = paymentData;

        if (this.method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            this.status = checkVoucherCode();
        } else if (this.method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            this.status = checkBankTransfer();
        } else if (this.method.equals(PaymentMethod.CASH_ON_DELIVERY.getValue())) {
            this.status = checkCashOnDelivery();
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String checkVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return PaymentStatus.REJECTED.getValue();
        }

        if (voucherCode.length() != 16) {
            return PaymentStatus.REJECTED.getValue();
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return PaymentStatus.REJECTED.getValue();
        }

        int counter = 0;
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                counter += 1;
            }
        }
        if (counter != 8) {
            return PaymentStatus.REJECTED.getValue();
        }

        return PaymentStatus.SUCCESS.getValue();
    }

    public String checkBankTransfer() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        } else if (referenceCode == null || referenceCode.isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        }

        return PaymentStatus.SUCCESS.getValue();
    }

    public String checkCashOnDelivery() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        if (address == null || address.isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        } else if (deliveryFee == null || deliveryFee.isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        }

        return PaymentStatus.SUCCESS.getValue();
    }
}
