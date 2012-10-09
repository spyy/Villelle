import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.io.ConnectionNotFoundException;
import cidservice.*;


public class PuheluListaMidlet extends MIDlet {
    
    private Service_Stub webService;
    
    public PuheluListaMidlet() {    
    }

    // Sets the MIDlet's current Display to a HelloScreen object.
    public void startApp() {
        System.out.println("PuheluListaMidlet::startApp");
        
        this.webService = new Service_Stub();
        
        Displayable current = Display.getDisplay(this).getCurrent();
        if (current == null) {            
            ListScreen screen = new ListScreen(this, "Soitto lista");
            Display.getDisplay(this).setCurrent(screen);
        }
    }

    public void pauseApp() {
        System.out.println("PuheluListaMidlet::pauseApp");
    }

    public void destroyApp(boolean unconditional) {
        System.out.println("PuheluListaMidlet::destroyApp");
    }
    
    public void onCapitalRequestComplete(String nation, String capital) {
        System.out.println("PuheluListaMidlet::onCapitalRequestComplete");
    }
    
    public void onCapitalRequestError(String code) {
        System.out.println("PuheluListaMidlet::onCapitalRequestError");
    }    
    
    public void call(String number) {
        System.out.println("PuheluListaMidlet::call" + number);
        String response = "Response:";
        
        try {
            response = this.webService.listCalls("4444,0451202979");
        } catch (java.rmi.RemoteException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println(response);
    }
    
    public void call2(String number) {
        System.out.println("PuheluListaMidlet::call" + number);
        
        try {
            this.platformRequest("tel:" + number);
        } catch (ConnectionNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}