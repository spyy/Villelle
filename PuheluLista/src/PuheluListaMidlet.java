import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.io.ConnectionNotFoundException;
import cidservice.*;
import java.util.*;



public class PuheluListaMidlet extends MIDlet {
    
    private Service_Stub webService;
    private ListScreen listScreen;
    private ListRequester listRequester;
    private TraceScreen traceScreen;
    private CallScreen callScreen;
    
    public PuheluListaMidlet() {    
        this.listScreen = new ListScreen(this, "Soitto lista");
        this.traceScreen = new TraceScreen(this, "Traces");
        this.callScreen = new CallScreen(this);
        this.webService = new Service_Stub();
        this.listRequester = new ListRequester(this, webService);
    }
    
    public void startApp() {        
        Traces.addLine("Midlet::startApp");
                        
        Displayable current = Display.getDisplay(this).getCurrent();
        if (current == null) {            
            this.showListScreen();
        }
    }
        
    public void pauseApp() {        
        Traces.addLine("Midlet::pauseApp");
    }

    public void destroyApp(boolean unconditional) {        
        Traces.addLine("Midlet::destroyApp");
    }
    
    public void showListScreen() {
        Display display = Display.getDisplay(this);
        this.listScreen.show(display);
    }
    
    public void showTraceScreen() {
        Display display = Display.getDisplay(this);
        this.traceScreen.show(display);
    }
    
    public void showCallScreen(String number) {
        Display display = Display.getDisplay(this);
        this.callScreen.show(display, number);
    }
    
    public void onRequestComplete(String response) {
        Traces.addLine("Midlet::onRequestComplete");
        this.listScreen.updateList(response);
    }
    
    public void onRequestError(String code) {
        Traces.addLine("Midlet::onRequestError");
        this.showListScreen();
    }
    
    public void onAnswered(String number) {
        Calendar calendar = Calendar.getInstance();        
        Traces.addLine("onAnswered: " + calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.SECOND));       
        this.showTraceScreen();
    }
    
    public void onNotAnswered() {
        Calendar calendar = Calendar.getInstance();        
        Traces.addLine("onNotAnswered: " + calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.SECOND));
        this.showTraceScreen();
    }
    
    public void call(String number) {
        Calendar calendar = Calendar.getInstance();        
        Traces.addLine("call: " + calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.SECOND));
        
        this.showCallScreen(number);
        
        try {
            this.platformRequest("tel:" + number);
        } catch (ConnectionNotFoundException ex) {
            Traces.addLine(ex.getMessage());            
        }                        
    }
    
    public void update() {        
        this.listRequester.requestUpdate();       
    }
        
}