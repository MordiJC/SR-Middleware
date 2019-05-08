package bank;

import Bank.*;
import com.zeroc.Ice.Current;

import java.util.Map;
import java.util.UUID;

public class AccountRegistererImplementation implements Bank.AccountRegisterer {

    private Map<String, BankClient> clients;

    public AccountRegistererImplementation(Map<String, BankClient> clients) {
        this.clients = clients;
    }

    @Override
    public BankClient registerNewClient(UserData userData, Money initBalance, Current current) throws ClientAlreadyExistsException {
        if (clients.containsKey(userData.pesel)) {
            throw new ClientAlreadyExistsException(userData);
        }

        boolean premium = userData.income.amount > 5000;
        userData.uuid = UUID.randomUUID().toString();

        BankClient bankClient = new BankClient(userData, premium ? AccountType.PREMIUM : AccountType.STANDARD, initBalance);

        clients.put(userData.pesel, bankClient);

        return bankClient;
    }
}
