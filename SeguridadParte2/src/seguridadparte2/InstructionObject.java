
package seguridadparte2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import seguridadparte2.InstructionValidations.ValidationPipeline;

public class InstructionObject {
    
    private String typeOfInstruction;
    private String subjectName;
    private String objectName;
    private int value;
    
    public String getTypeOfInstruction() {
        return typeOfInstruction;
    }

    public void setTypeOfInstruction(String typeOfInstruction) {
        this.typeOfInstruction = typeOfInstruction;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        
        String toReturnTypeIns = "Bad Instruction \n";
        String currentState = "The current state is: \n";
        String state = getCurrentStateAsString();
        
        switch(this.getTypeOfInstruction().toUpperCase()){
            case "READ":
                toReturnTypeIns = this.getSubjectName() + " reads " + this.getObjectName() + "\n";
            break;
            
            case "WRITE":
                toReturnTypeIns = this.getSubjectName() + " writes value " + this.getValue() + " to " + this.getObjectName() + "\n";
            break;
        };
        return toReturnTypeIns + currentState + state;  
    }
    
    public InstructionObject(String line){
        
        ArrayList<String> wordsIgnoredSpaces = getWordsIgnoredSpace(line);
        try{
            ValidationPipeline pipeline= new ValidationPipeline();
            pipeline.initPipeline();
            InstructionObject builtInstruction = pipeline.PerformOperation(this, wordsIgnoredSpaces);
            executeInstruction(builtInstruction);
        }
        catch(Exception ex){
            this.typeOfInstruction = "BAD";
            BadInstruction badInstruction = new BadInstruction(line);
            SecureSystem.GetInstance().getReferenceMonitor().saveBadInstruction(badInstruction);
        }
    }

    private String getCurrentStateAsString(){
        
        String stringToReturn = "";
        for(Object obj : SecureSystem.GetInstance().getReferenceMonitor().getObjectManager().getObjects()){
            stringToReturn += obj.toString();
        }
        for(Subject sub : SecureSystem.GetInstance().getSubjects()){
            stringToReturn += sub.toString();
        }
        return stringToReturn;
    }
    
    private ArrayList<String> getWordsIgnoredSpace(String line){
        String [] words = line.split(" ");
        ArrayList<String> wordsIgnoredSpaces = new ArrayList<String>();
        for(String word : words){
            if(word.length() > 0){
                wordsIgnoredSpaces.add(word);
            }
        }
        return wordsIgnoredSpaces;
    }

    private void executeInstruction(InstructionObject builtInstruction){
        
        String typeOfInstruction = builtInstruction.getTypeOfInstruction();
        switch(typeOfInstruction.toUpperCase()){
            case "READ":
                SecureSystem.GetInstance().getReferenceMonitor().ExecuteRead(builtInstruction);      
            break;
                
            case "WRITE":
                SecureSystem.GetInstance().getReferenceMonitor().ExecuteWrite(builtInstruction);
            break;
        };
    }

    public InstructionObject(String typeOfInstruction, String subjectName, String objectName, java.lang.Object... params) {
        this.typeOfInstruction = typeOfInstruction;
        this.subjectName = subjectName;
        this.objectName = objectName;
        if(params.length > 0){
            this.value = (int) params[0];
        }
    }
    
    
}
