package bank;

import currency.ExchangeOuterClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankServer {

    static final String exchangeAddress = "localhost";
    static int exchangePort = 44221;

    public static void main(String[] args) throws InterruptedException, IOException {
        List<ExchangeOuterClass.Currency> observedCurrencies = new ArrayList<>() {
            {
                add(ExchangeOuterClass.Currency.PLN);
            }
        };

        String line;
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        System.out.println("Enter currencies (EUR, USD, GBP) or `end` to continue: ");
        while(true) {
            System.out.print("> ");
            line = in.readLine();
            if (!line.equals("end")) {
                try {
                    ExchangeOuterClass.Currency currency = ExchangeOuterClass.Currency.valueOf(line.trim().toUpperCase());
                    if (!observedCurrencies.contains(currency)) {
                        observedCurrencies.add(currency);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("This currency was entered already.");
                }
            } else {
                break;
            }
        }

        Thread exchangeThread = new Thread(new ExchangeObserverService(exchangeAddress, exchangePort, observedCurrencies));
        Thread clientServerThread = new Thread(new ClientService());


        exchangeThread.start();
        clientServerThread.start();

        exchangeThread.join();
        clientServerThread.join();
    }
}
