package bank;

import currency.ExchangeGrpc;
import currency.ExchangeOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExchangeObserverService implements Runnable {
    private final ManagedChannel channel;
    private final ExchangeGrpc.ExchangeStub exchangeNonBlockingStub;
    private final List<ExchangeOuterClass.Currency> observedCurrencies;
    private static final Logger logger = Logger.getLogger(ExchangeObserverService.class.getName());

    public ExchangeObserverService(String address, int port, List<ExchangeOuterClass.Currency> observedCurrencies) {
        this.observedCurrencies = observedCurrencies;
        channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext()
                .build();

        exchangeNonBlockingStub = ExchangeGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        logger.info("Starting");

        ExchangeOuterClass.ExchangeRateRequest request = ExchangeOuterClass.ExchangeRateRequest.newBuilder()
                .addAllCurrencies(observedCurrencies).build();

        logger.info("Subscribing");

        exchangeNonBlockingStub.streamRates(request, new StreamObserver<>() {
            @Override
            public void onNext(ExchangeOuterClass.CurrencyRate value) {
                CurrencyInfoProvider.getInstance().put(Bank.Currency.valueOf(value.getCurrency().name()), value.getRate());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println(this.getClass().getName() + " - ERROR: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
            }
        });

        logger.info("Subscribed. Waiting.");

        while (true) {
            Thread.yield();
        }

//        try {
//            shutdown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        logger.info(this.getClass().getName() + " - Shutting down...");
    }
}
