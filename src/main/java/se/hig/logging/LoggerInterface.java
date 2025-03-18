package se.hig.logging;

import java.util.function.Supplier;

public interface LoggerInterface {
    public void info(Supplier<String> messageSupplier);
    public void error(Throwable ex);

    public static LoggerInterface get() {
        //return new BasicLogger();
        return new JavaApiLogger();
    }

}
