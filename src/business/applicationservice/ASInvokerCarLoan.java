package business.applicationservice;

import utility.Packet;
import utility.exception.CommonException;
import utility.reader.XMLReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by salvatore on 10/10/15.
 */
public class ASInvokerCarLoan implements ASInvoker {

    private final ApplicationService applicationService;
    private XMLReader xmlReader;

    public ASInvokerCarLoan(String request) {
        xmlReader = XMLReader.getReader("service");
        String applicationServiceName = (xmlReader.getServiceValue(request, 0));
        applicationService = ASFactory.getAS(applicationServiceName);
    }

    @Override
    public Object invoke(String request, Packet packet) throws CommonException {
        Object result = null;

        try {
            Class<?> applicationServiceClass = applicationService.getClass();

            Method method = applicationServiceClass.getMethod(xmlReader.getServiceValue(request, 1), Packet.class);
            result = method.invoke(applicationService, packet);


            //ArrayList<String> serviceValues = null; //xmlReader.leggiParametri(service);


            //String appServiceMethod = serviceValues.get(0);


            //String asMethodInString = ApplicationServiceSelector.getServiceMethod(serviceName);
            //Method asMethod = asClass.getMethod(asMethodInString, Parameter.class);

            //Method method = appServiceClass.getMethod(serviceValues.get(1), Packet.class);
            //result = method.invoke(applicationService, packet);

        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            Class<?> causeClass = cause.getClass();
            if (CommonException.class.isAssignableFrom(causeClass)) {
                CommonException commonCause = (CommonException) cause;
                throw commonCause;
            } else {
                /**/e.printStackTrace();
                throw new CommonException("Errore imprevisto, riavviare CarLoan!");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return result;
    }

}
