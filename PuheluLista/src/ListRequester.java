
import cidservice.*;



public class ListRequester implements Runnable {
    private boolean threadIsRunning = false;
    private final PuheluListaMidlet midlet;
    private Service_Stub webService;

    public ListRequester(PuheluListaMidlet midlet, Service_Stub webService) {        
        this.midlet = midlet;
        this.webService = webService;
    }

    public synchronized void requestUpdate() {
        if (threadIsRunning) {
            this.midlet.onRequestError("Request pending");
        }
        else {
            threadIsRunning = true;
            new Thread(this).start();            
        }
    }

    public void run() {
        String response = "Request failed";
        
        try {
            response = this.webService.listCalls("4444,0451202979");
            this.midlet.onRequestComplete(response);
        } catch (java.rmi.RemoteException ex) {
            this.midlet.onRequestError(ex.getMessage());
        } catch (javax.xml.rpc.JAXRPCException ex) {
            this.midlet.onRequestError(ex.getMessage());
        }
        
        
        threadIsRunning = false;
    }
}
