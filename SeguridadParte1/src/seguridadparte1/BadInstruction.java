
package seguridadparte1;

public class BadInstruction {
    
    private String badLine;
    
    public BadInstruction(){
        
    }
    
    public BadInstruction(String line){
        badLine = line;
    }

    public String getBadLine() {
        return badLine;
    }

    public void setBadLine(String badLine) {
        this.badLine = badLine;
    }
    
}
