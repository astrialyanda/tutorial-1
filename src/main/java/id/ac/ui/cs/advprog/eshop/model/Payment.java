package id.ac.ui.cs.advprog.eshop.model;

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

        if (this.method.equals("VOUCHER_CODE")) {
            this.status = checkVoucherCode();
        } else if (this.method.equals("BANK_TRANSFER")) {
            this.status = checkBankTransfer();
        } else if (this.method.equals("CASH_ON_DELIVERY")) {
            this.status = checkCashOnDelivery();
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String checkVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return "REJECTED";
        }

        if (voucherCode.length() != 16) {
            return "REJECTED";
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return "REJECTED";
        }

        int counter = 0;
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                counter += 1;
            }
        }
        if (counter != 8) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    public String checkBankTransfer() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty()) {
            return "REJECTED";
        } else if (referenceCode == null || referenceCode.isEmpty()) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    public String checkCashOnDelivery() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        if (address == null || address.isEmpty()) {
            return "REJECTED";
        } else if (deliveryFee == null || deliveryFee.isEmpty()) {
            return "REJECTED";
        }

        return "SUCCESS";
    }
}
