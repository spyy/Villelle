import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.io.ConnectionNotFoundException;
import cidservice.*;


public class PuheluListaMidlet extends MIDlet {
    
    private Service_Stub webService;
    private ListScreen listScreen;
    private ListRequester listRequester;
    private TraceScreen traceScreen;
    
    public PuheluListaMidlet() {    
        this.listScreen = new ListScreen(this, "Soitto lista");
        this.traceScreen = new TraceScreen(this, "Traces");
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
    
    public void onRequestComplete(String response) {
        Traces.addLine("Midlet::onRequestComplete");
        this.listScreen.updateList(response);
    }
    
    public void onRequestError(String code) {
        Traces.addLine("Midlet::onRequestError");
    }    
    
    public void call(String number) {
        Traces.addLine("Midlet::call" + number);
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