import javax.microedition.lcdui.*;

class ListScreen
        extends List
        implements CommandListener {

    private final PuheluListaMidlet midlet;
    private final Command exitCommand;
    private final Command callCommand;
    private final Command selectCommand;
        
    public static final String[] phoneNumbers = {
        "+358403675010", "+358451202979"
    };

    public ListScreen(PuheluListaMidlet midlet, String title) {
        super(title, List.IMPLICIT, phoneNumbers, null);        
        this.midlet = midlet;
        this.exitCommand = new Command("Exit", Command.EXIT, 1);
        callCommand = new Command("Call", Command.ITEM, 1);
        selectCommand = new Command("Select", Command.ITEM, 1);
        this.setSelectCommand(selectCommand);
        this.addCommand(exitCommand);
        //addCommand(callCommand);
        this.setCommandListener(this);
    }

    public void commandAction(Command command, Displayable displayable) {
        System.out.println("ListScreen::commandAction");
        
        if (command == exitCommand) {
            midlet.notifyDestroyed();
        }
        else if (command == callCommand) {            
            int index = this.getSelectedIndex();
            String number = this.getString(index);
            //this.midlet.call(number);
        }
        else if (command == selectCommand) {
            int index = this.getSelectedIndex();
            String number = this.getString(index);
            this.midlet.call(number);
        }            
    }
}