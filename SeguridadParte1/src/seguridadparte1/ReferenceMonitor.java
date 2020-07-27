
package seguridadparte1;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class ReferenceMonitor {
   
    private ObjectManager objectManager;
    private ArrayList<BadInstruction> badInstructions;

    public ReferenceMonitor(){
        badInstructions = new ArrayList<>();
        objectManager = new ObjectManager();
    }
    
    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }
    
    
    public void ExecuteRead(InstructionObject instruction){
        
      Object obj= objectManager.getObjectByName(instruction.getObjectName());
      Subject subject= SecureSystem.GetInstance().getSubjectByName(instruction.getSubjectName()); 
     
      boolean canRead = canRead(subject.getSecurityLevel(),obj.getSecurityLevel());
      objectManager.Read(subject, instruction.getObjectName(), canRead);
    } 
    
    public void ExecuteWrite(InstructionObject instruction){    
      Object obj = objectManager.getObjectByName(instruction.getObjectName());
      Subject subject = SecureSystem.GetInstance().getSubjectByName(instruction.getSubjectName());
      if(canWrite(subject.getSecurityLevel(),obj.getSecurityLevel())){
          objectManager.Write(instruction.getObjectName(),instruction.getValue());
      }
    }
    
    public void CreateNewObject(String name,SecurityLevel securityLevel){     
        objectManager.CreateNewObject(name, securityLevel);
    }

    private boolean canRead(SecurityLevel subjectSecurityLevel, SecurityLevel objSecurityLevel) {
        return subjectSecurityLevel.ordinal() >= objSecurityLevel.ordinal();  
    }
     private boolean canWrite(SecurityLevel subjectSecurityLevel, SecurityLevel objSecurityLevel) {
        return subjectSecurityLevel.ordinal() <= objSecurityLevel.ordinal();  
    }
     
    public void saveBadInstruction(BadInstruction badInstruction){
        badInstructions.add(badInstruction);
    }
}
