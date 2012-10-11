


import javax.microedition.lcdui.*;

class TraceScreen
        extends List
        implements CommandListener {
    
    private final PuheluListaMidlet midlet;
    private final Command exitCommand;
    private final Command numbersCommand;    
        
    

    public TraceScreen(PuheluListaMidlet midlet, String title) {
        super(title, List.IMPLICIT);        
        this.midlet = midlet;
        exitCommand = new Command("Exit", Command.EXIT, 1);
        numbersCommand = new Command("Numbers", Command.ITEM, 1);
        addCommand(exitCommand);
        addCommand(numbersCommand);
        setCommandListener(this);
    }

    public void commandAction(Command command, Displayable displayable) {
        System.out.println("ListScreen::commandAction");
        
        if (command == exitCommand) {
            midlet.notifyDestroyed();
        }
        else if (command == numbersCommand) {
            midlet.showListScreen();
        }            
    }
    
    public void show(Display display) {
        this.deleteAll();
        String[] lines = Traces.getLines();
        for (int i=0; i<lines.length; i++) {
            this.append(lines[i], null);
        }
        display.setCurrent(this);        
    }       
    
}
