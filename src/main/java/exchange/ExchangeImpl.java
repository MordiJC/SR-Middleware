package exchange;

import currency.ExchangeGrpc;
import currency.ExchangeOuterClass;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ExchangeImpl extends ExchangeGrpc.ExchangeImplBase {
    private Map<ExchangeOuterClass.Currency, Double> rates = new HashMap<>() {
        {
            put(ExchangeOuterClass.Currency.PLN, 1.0);
            put(ExchangeOuterClass.Currency.EUR, 4.29151143);
            put(ExchangeOuterClass.Currency.USD, 3.83995085);
            put(ExchangeOuterClass.Currency.GBP, 5.01319023);
        }
    };

    private synchronized void changeRates() {
        final Random rand = new Random();
        for (Map.Entry<ExchangeOuterClass.Currency, Double> entry : rates.entrySet()) {
            entry.setValue(entry.getValue() * (1.0 + ((rand.nextFloat() - 0.5) * 0.1)));
        }
    }

    @Override
    public void currentRates(ExchangeOuterClass.ExchangeRateRequest request, StreamObserver<ExchangeOuterClass.ExchangeRateResponse> responseObserver) {
        List<ExchangeOuterClass.Currency> currenciesToSend = request.getCurrenciesList();
        List<ExchangeOuterClass.CurrencyRate> resultCurrencies = rates.entrySet()
                .stream()
                .filter(entry -> currenciesToSend.contains(entry.getKey()))
                .map(entry -> ExchangeOuterClass.CurrencyRate.newBuilder()
                        .setCurrency(entry.getKey())
                        .setRate(entry.getValue())
                        .build()
                )
                .collect(Collectors.toList());

        ExchangeOuterClass.ExchangeRateResponse result = ExchangeOuterClass.ExchangeRateResponse.newBuilder().addAllRates(resultCurrencies).build();

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void streamRates(ExchangeOuterClass.ExchangeRateRequest request, StreamObserver<ExchangeOuterClass.CurrencyRate> responseObserver) {
        List<ExchangeOuterClass.Currency> currenciesToSend = request.getCurrenciesList();

        while (true) {
            rates.entrySet()
                    .stream()
                    .filter(entry -> currenciesToSend.contains(entry.getKey()))
                    .map(entry -> ExchangeOuterClass.CurrencyRate.newBuilder()
                            .setCurrency(entry.getKey())
                            .setRate(entry.getValue())
                            .build()
                    )
                    .forEach(responseObserver::onNext);

            changeRates();

            try {
                Thread.sleep(new Random().nextInt(3000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
