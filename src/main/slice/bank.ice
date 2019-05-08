#ifndef BANK_ICE
#define BANK_ICE

module Bank {
    enum Currency { PLN, EUR, USD, GBP };
    enum AccountType { STANDARD, PREMIUM };

    struct Money {
        Currency currency;
        double amount;
    };

    struct Period {
        int months;
    };

    struct AuthData {
        string pesel;
        string uuid;
    };

    struct UserData {
        string pesel;
        string uuid;
        string name;
        string surname;
        Money income;
    };

    struct BankClient {
        UserData userData;
        AccountType accountType;
        Money balance;
    };

    struct LoanRequest {
        Money amount;
        Period period;
        Currency foreignCurrency;
    };

    struct LoanResponse {
        Money baseCost;
        Money foreignCost;
    };

    exception FailedAuthenticationException {
        AuthData authData;
        string reason;
    };

    exception InvalidUuidException {
        string uuid;
    };

    exception InvalidLoanRequestException {
        LoanRequest loanRequest;
        string reason;
    };

    exception ClientAlreadyExistsException {
        UserData userData;
    };

    interface AccountRegisterer {
        BankClient registerNewClient(UserData userData, Money initBalance)
            throws ClientAlreadyExistsException;
    };

    interface StandardManager {
        Money getBalance(AuthData authData)
            throws FailedAuthenticationException;
    };

    interface PremiumManager extends StandardManager {
        LoanResponse requestLoan(AuthData authData, LoanRequest loanRequest)
            throws FailedAuthenticationException, InvalidLoanRequestException;
    };
};

#endif