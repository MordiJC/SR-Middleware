package bank;

import currency.ExchangeOuterClass;

public class Client {
    private String PESEL;
    private String name;
    private String surname;
    private String uuid;
    private boolean premium;
    private ExchangeOuterClass.CurrencyRate balance;

    public Client(String PESEL, String name, String surname, String uuid, boolean premium, ExchangeOuterClass.CurrencyRate balance) {
        this.PESEL = PESEL;
        this.name = name;
        this.surname = surname;
        this.uuid = uuid;
        this.premium = premium;
        this.balance = balance;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public ExchangeOuterClass.CurrencyRate getBalance() {
        return balance;
    }

    public void setBalance(ExchangeOuterClass.CurrencyRate balance) {
        this.balance = balance;
    }
}
