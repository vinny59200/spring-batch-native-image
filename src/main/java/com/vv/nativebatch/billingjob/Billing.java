package com.vv.nativebatch.billingjob;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Billing {


    private int year;
    private int month;
    private int accountNumber;
    @Id
    private String phoneNumber;
    private double amount;
    private int calls;
    private int messages;

    // Getters, setters, and toString (important for JPA to map the entity)
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getCalls() { return calls; }
    public void setCalls(int calls) { this.calls = calls; }

    public int getMessages() { return messages; }
    public void setMessages(int messages) { this.messages = messages; }

    @Override
    public String toString() {
        return "Billing{" +
                ", year=" + year +
                ", month=" + month +
                ", accountNumber=" + accountNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", amount=" + amount +
                ", calls=" + calls +
                ", messages=" + messages +
                '}';
    }
}
