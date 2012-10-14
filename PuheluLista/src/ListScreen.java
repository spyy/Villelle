


import javax.microedition.lcdui.*;

class ListScreen
        extends List
        implements CommandListener {

    private final PuheluListaMidlet midlet;
    private final Command exitCommand;
    private final Command updateCommand;  
    private final Command traceCommand;
    private final Command selectCommand;
        
    public static final String[] testNumbers = {
        "0403675010", "0403674984"
    };

    public ListScreen(PuheluListaMidlet midlet, String title) {
        super(title, List.IMPLICIT);        
        this.midlet = midlet;
        
        exitCommand = new Command("Exit", Command.EXIT, 1);
        updateCommand = new Command("Update", Command.ITEM, 1);
        traceCommand = new Command("Traces", Command.ITEM, 1);
        selectCommand = new Command("Select", Command.ITEM, 1);
        setSelectCommand(selectCommand);
        addCommand(traceCommand);
        addCommand(exitCommand);
        addCommand(updateCommand);
        
        setCommandListener(this);
    }

    public void commandAction(Command command, Displayable displayable) {
        System.out.println("ListScreen::commandAction");
        
        if (command == exitCommand) {
            midlet.notifyDestroyed();
        }
        else if (command == updateCommand) {
            this.deleteAll();
            this.addIntoList("Request pending...");            
            midlet.update();
        }
        else if (command == traceCommand) {
            midlet.showTraceScreen();
        }
        else if (command == selectCommand) {
            int index = this.getSelectedIndex();
            String number = this.getString(index);
            this.midlet.call(number);
        }  
    }
    
    public void show(Display display) {
        this.deleteAll();
        this.addTestNumbers();
        display.setCurrent(this);
    }
    
    public void updateList(String response) {
        this.deleteAll();
        this.addTestNumbers();
        
        String[] tokens = this.splitResponse(response);
        System.out.println("num of tokens: " + Integer.toString(tokens.length));        
        for (int i=0; i<tokens.length; i++) {
            this.addIntoList(tokens[i]);
        }        
    }
    
    private void addTestNumbers() {
        for (int i=0; i<this.testNumbers.length; i++) {
            this.append(this.testNumbers[i], null);
        }
    }
    
    private void addIntoList(String token) {
        this.append(token, null);
    }        
    
    private String[] splitResponse(String response) {
        int beginIndex = 0;        
        int endIndex = 0;
        int numOfTokens = this.countTokens(response);
        String[] tokens = new String[numOfTokens];        
        
        for (int i=0; i<tokens.length; i++) {
            endIndex = response.indexOf(" ", beginIndex);
            tokens[i] = response.substring(beginIndex, endIndex);
            beginIndex = endIndex + 1;
        }
        
        return tokens;
    }
    
    private int countTokens(String response) {
        int beginIndex = 0;        
        int numOfTokens = 0;        
        int endIndex = response.indexOf(" ", beginIndex);
        
        while (endIndex>0) {            
            numOfTokens++;
            beginIndex = endIndex + 1;
            endIndex = response.indexOf(" ", beginIndex);
        }
                
        return numOfTokens;
    }
}