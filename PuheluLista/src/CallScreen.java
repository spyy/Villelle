import javax.microedition.lcdui.*;

class CallScreen
        extends Form
        implements CommandListener {
    
    private final PuheluListaMidlet midlet;
    private String number;
    

    public CallScreen(PuheluListaMidlet midlet) {
        super("Soitetut");       
        this.midlet = midlet;        
        addCommand(new Command ("Vastasi", Command.OK, 1));
        addCommand(new Command("Ei", Command.CANCEL, 1));
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable displayable) {
        if (c.getLabel().equals("Vastasi")) {
            midlet.onAnswered(this.number);
        }
        else if (c.getLabel().equals("Ei")) {
            midlet.onNotAnswered();
        }      
    }
    
    public void show(Display display, String number) {
        this.number = number;
        this.append("\n" + this.number);        
        display.setCurrent(this);
        
    }       
    
}
