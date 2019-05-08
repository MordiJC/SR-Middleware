package bank;

import Bank.*;
import com.zeroc.Ice.Current;

import java.util.Map;

public class PremiumManagerImplementation extends StandardManagerImplementation implements PremiumManager {

    public PremiumManagerImplementation(Map<String, BankClient> clients) {
        super(clients);
    }

    @Override
    public LoanResponse requestLoan(AuthData authData, LoanRequest loanRequest, Current current)
            throws FailedAuthenticationException, InvalidLoanRequestException {
        BankClient client = getClientForAuth(authData);

        if (client.accountType != AccountType.PREMIUM) {
            throw new InvalidLoanRequestException(loanRequest, "Only premium user are permitted to take a loan.");
        }

        if (loanRequest.period.months < 3) {
            throw new InvalidLoanRequestException(loanRequest, "Loans are given for at least 3 months.");
        }

        if (loanRequest.amount.amount < 1000) {
            throw new InvalidLoanRequestException(loanRequest, "Lowest loan is 1000 of any currency.");
        }

        if (!CurrencyInfoProvider.getInstance().containsKey(loanRequest.foreignCurrency)) {
            throw new InvalidLoanRequestException(loanRequest, "Requested foreign currency is not available");
        }

        Money baseCost = new Money(loanRequest.amount.currency, loanRequest.amount.amount * 1.03);

        double newCost = CurrencyInfoProvider.getInstance().get(loanRequest.amount.currency) * baseCost.amount;
        Money foreignCost = new Money(loanRequest.foreignCurrency, newCost);

        return new LoanResponse(baseCost, foreignCost);
    }
}
