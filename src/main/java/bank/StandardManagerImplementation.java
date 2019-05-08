package bank;

import Bank.*;
import com.zeroc.Ice.Current;

import java.util.Map;

public class StandardManagerImplementation implements StandardManager {

    private Map<String, BankClient> clients;

    public StandardManagerImplementation(Map<String, BankClient> clients) {
        this.clients = clients;
    }

    @Override
    public Money getBalance(AuthData authData, Current current) throws FailedAuthenticationException {
        BankClient client = getClientForAuth(authData);

        return client.balance;
    }

    protected BankClient getClientForAuth(AuthData authData)  throws FailedAuthenticationException{
        if (!clients.containsKey(authData.pesel)) {
            throw new FailedAuthenticationException(authData, "No user found");
        }

        BankClient client = clients.get(authData.pesel);

        if(!client.userData.uuid.equals(authData.uuid)) {
            throw new FailedAuthenticationException(authData, "Invalid UUID");
        }

        return client;
    }
}
