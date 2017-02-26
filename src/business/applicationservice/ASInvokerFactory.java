package business.applicationservice;

/**
 * Created by salvatore on 10/10/15.
 */
public class ASInvokerFactory {

    private ASInvokerFactory() {

    }

    public static ASInvoker getASInvoker(String request) {
        return new ASInvokerCarLoan(request);
    }
}
