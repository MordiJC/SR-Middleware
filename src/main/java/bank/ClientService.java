package bank;

import Bank.BankClient;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ClientService implements Runnable {

    static final String hostAddress = "localhost";
    static final int hostPort = 55332;
    static final Logger logger = Logger.getLogger(Client.class.getName());

    private Communicator communicator;
    private Map<String, BankClient> clients = new HashMap<>(); //< Key is PESEL

    @Override
    public void run() {
        try {
            logger.info("Starting");
            communicator = Util.initialize();

            logger.info("Initialized");

            ObjectAdapter adapter =
                    communicator.createObjectAdapterWithEndpoints("Adapter",
                            String.format("tcp -h %s -p %d:udp -h %s -p %d", hostAddress, hostPort, hostAddress, hostPort));

            logger.info("Adapter created");

            com.zeroc.Ice.Object accountRegistererServant = new AccountRegistererImplementation(clients);
            com.zeroc.Ice.Object standardClientServant = new StandardManagerImplementation(clients);
            com.zeroc.Ice.Object premiumClientServant = new StandardManagerImplementation(clients);

            logger.info("Servants created");

            adapter.add(accountRegistererServant, new Identity("accountRegistererServant", null));
            adapter.add(standardClientServant, new Identity("standardClientServant", null));
            adapter.add(premiumClientServant, new Identity("premiumClientServant", null));

            logger.info("Servants added");

            adapter.activate();

            logger.info("Adapter active. Waiting for termination.");

            communicator.waitForShutdown();

            logger.info("Shutting down");
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " - ERROR: " + e.getMessage());
        } finally {
            if (communicator != null) {
                try {
                    logger.info("Destroying communicator");
                    communicator.destroy();
                } catch (Exception ignored) {
                    // No need to handle this
                }
            }
        }
    }
}
