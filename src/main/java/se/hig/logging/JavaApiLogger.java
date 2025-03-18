package se.hig.logging;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaApiLogger implements LoggerInterface {

    private final Logger logger = java.util.logging.Logger.getLogger(JavaApiLogger.class.getName());

    @Override
    public void info(Supplier<String> messageSupplier) {
        logger.log(Level.INFO, messageSupplier);
    }

    @Override
    public void error(Throwable ex) {
        logger.log(Level.SEVERE, ex.getMessage(), ex);
    }

}