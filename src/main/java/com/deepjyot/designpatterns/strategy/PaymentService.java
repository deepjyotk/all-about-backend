package com.deepjyot.designpatterns.strategy;



interface Payment {

    /**
     * Processes the payment of a given amount.
     *
     * @param amount the amount to be paid
     * @return true if payment is successful, false otherwise
     */
    boolean pay(double amount);
}


 class CreditCardPayment implements Payment {

    private final String cardNumber;
    private final String cardHolderName;
    private final String expiryDate;
    private final String cvv;

    // In a real-world scenario, you might also have billing address, etc.
    public CreditCardPayment(String cardNumber, String cardHolderName,
                             String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {
        // Simulate credit card transaction logic
        System.out.println("Processing Credit Card payment...");
        System.out.println("Charging card number: " + cardNumber
                + " for amount: " + amount);
        // Here you might call external bank APIs, etc.

        // For demonstration purposes, assume it always succeeds
        return true;
    }
}


class PayPalPayment implements Payment {

    private final String email;
    private final String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) {
        // Simulate PayPal transaction logic
        System.out.println("Processing PayPal payment...");
        System.out.println("Paying from PayPal account: " + email
                + " for amount: " + amount);
        // Here you might call PayPal's REST API, etc.

        // For demonstration, assume it is always successful
        return true;
    }
}


enum PaymentMethodType {
    CREDIT_CARD,
    PAYPAL,
    BANK_TRANSFER
}


class PaymentFactory {

    /**
     * Creates a Payment instance based on PaymentMethodType.
     *
     * @param methodType the type of payment method
     * @param paymentRequest a helper object that contains request details
     *                       (like card number, email, etc.)
     * @return the appropriate Payment implementation
     */
    public static Payment createPayment(PaymentMethodType methodType, PaymentRequest paymentRequest) {
        switch (methodType) {
            case CREDIT_CARD:
                return new CreditCardPayment(
                        paymentRequest.getCardNumber(),
                        paymentRequest.getCardHolderName(),
                        paymentRequest.getExpiryDate(),
                        paymentRequest.getCvv()
                );
            case PAYPAL:
                return new PayPalPayment(
                        paymentRequest.getEmail(),
                        paymentRequest.getPassword()
                );
            case BANK_TRANSFER:
                return new BankTransferPayment(
                        paymentRequest.getBankAccountNumber(),
                        paymentRequest.getBankRoutingNumber()
                );
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + methodType);
        }
    }
}



 class PaymentRequest {

    // Credit Card Info
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    // PayPal Info
    private String email;
    private String password;

    // Bank Transfer Info
    private String bankAccountNumber;
    private String bankRoutingNumber;

    // Constructors, getters, and setters omitted for brevity

    public PaymentRequest() {
    }

    // Sample builder-pattern style for convenience
    public PaymentRequest setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }
    public PaymentRequest setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }
    public PaymentRequest setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }
    public PaymentRequest setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }
    public PaymentRequest setEmail(String email) {
        this.email = email;
        return this;
    }
    public PaymentRequest setPassword(String password) {
        this.password = password;
        return this;
    }
    public PaymentRequest setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }
    public PaymentRequest setBankRoutingNumber(String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
        return this;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public String getCvv() {
        return cvv;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }
}



 class BankTransferPayment implements Payment {

    private final String bankAccountNumber;
    private final String bankRoutingNumber;

    public BankTransferPayment(String bankAccountNumber, String bankRoutingNumber) {
        this.bankAccountNumber = bankAccountNumber;
        this.bankRoutingNumber = bankRoutingNumber;
    }

    @Override
    public boolean pay(double amount) {
        // Simulate a bank transfer
        System.out.println("Processing Bank Transfer payment...");
        System.out.println("Transferring from bank account: " + bankAccountNumber
                + " using routing number: " + bankRoutingNumber
                + " for amount: " + amount);
        // Here you might integrate with ACH or wire transfer APIs.

        // Assume transfer is successful for this demo
        return true;
    }
}



public class PaymentService {

    /**
     * Example method to demonstrate how to use the PaymentFactory.
     */
    public void processPayment(PaymentMethodType paymentMethodType, PaymentRequest paymentRequest, double amount) {
        // Create the appropriate payment method at runtime
        Payment paymentMethod = PaymentFactory.createPayment(paymentMethodType, paymentRequest);

        // Process payment
        boolean isSuccess = paymentMethod.pay(amount);

        // Handle the result
        if (isSuccess) {
            System.out.println("Payment succeeded for amount: " + amount);
        } else {
            System.out.println("Payment failed for amount: " + amount);
        }
    }

    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        // Example 1: Credit Card Payment
        PaymentRequest creditCardRequest = new PaymentRequest()
                .setCardNumber("1234-5678-9876-5432")
                .setCardHolderName("John Doe")
                .setExpiryDate("12/2025")
                .setCvv("123");

        service.processPayment(PaymentMethodType.CREDIT_CARD, creditCardRequest, 150.00);


        // Example 2: PayPal Payment
        PaymentRequest paypalRequest = new PaymentRequest()
                .setEmail("john.doe@example.com")
                .setPassword("securePassword123");

        service.processPayment(PaymentMethodType.PAYPAL, paypalRequest, 99.99);


        // Example 3: Bank Transfer
        PaymentRequest bankTransferRequest = new PaymentRequest()
                .setBankAccountNumber("9876543210")
                .setBankRoutingNumber("111000025");

        service.processPayment(PaymentMethodType.BANK_TRANSFER, bankTransferRequest, 500.00);
    }
}
