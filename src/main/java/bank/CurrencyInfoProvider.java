package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyInfoProvider {
    private Map<Bank.Currency, Double> currencies = new HashMap<>() {
        {
            put(Bank.Currency.PLN, 1.0);
        }
    };

    private static CurrencyInfoProvider cip = new CurrencyInfoProvider();

    private CurrencyInfoProvider() {

    }

    public static CurrencyInfoProvider getInstance() {
        return cip;
    }

    public void put(Bank.Currency currency, Double value) {
        currencies.put(currency, value);
    }

    public List<Bank.Currency> getAvailableCurrencies() {
        return new ArrayList<>(currencies.keySet());
    }

    public boolean containsKey(Bank.Currency currency) {
        return currencies.containsKey(currency);
    }

    public Double get(Bank.Currency currency) {
        return currencies.get(currency);
    }
}
