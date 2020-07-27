
package seguridadparte2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class ReferenceMonitor {
   
    private ObjectManager objectManager;
    private ArrayList<BadInstruction> badInstructions;
    private String OBJECT_NAME = "obj";
    private String LOG_URL = "Entrada\\log.txt";
    private int NUMBER_BITS = 8;
    int[] byteBuffer;
    int bitCount;
    FileWriter logWriter;

    public ReferenceMonitor(){
        badInstructions = new ArrayList<>();
        objectManager = new ObjectManager();
        byteBuffer = new int[8];
        bitCount = 0;
        try{
            File file= new File (LOG_URL);
            if(!file.exists()){
                file.createNewFile();
            }
            logWriter = new FileWriter(file);
        }
        catch(IOException e){}
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
    
    public void Create(String subjectName){ 
        Subject subject = SecureSystem.GetInstance().getSubjectByName(subjectName);
        objectManager.Create(OBJECT_NAME, subject.getSecurityLevel());
    }
    
    public void Destroy(String subjectName){     
        Object obj = objectManager.getObjectByName(OBJECT_NAME);
        Subject subject = SecureSystem.GetInstance().getSubjectByName(subjectName);
        if(canWrite(subject.getSecurityLevel(),obj.getSecurityLevel())){
            objectManager.Destroy(OBJECT_NAME);
        }
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

    public void executeRun(String subjectName, boolean v, FileWriter writer) {
        
        switch(subjectName){
            
            case "Hal":
                if(v){
                    try{
                        logWriter.write("RUN Hal\r\n");
                        logWriter.flush();
                    }
                    catch(IOException e) {}      
                }
                Create(subjectName);
                
            break;
             
            case "Lyle":
                if(v){
                    try{
                        logWriter.write("RUN Lyle\r\n");
                        logWriter.flush();
                    }
                    catch(IOException e) {}  
                }
                Create(subjectName);
                ExecuteWrite(new InstructionObject("WRITE", subjectName, OBJECT_NAME, 1));
                ExecuteRead(new InstructionObject("READ", subjectName, OBJECT_NAME));
                Destroy(subjectName);
                
                Subject subject = SecureSystem.GetInstance().getSubjectByName(subjectName);
                byteBuffer[bitCount] = subject.getTemp();
                bitCount++;
                if(bitCount == NUMBER_BITS){               
                    try{
                        char bytesToShow = convertBitsToByte(byteBuffer);
			writer.write(bytesToShow);
                        writer.flush();
                    }
                    catch(IOException e){}
                    bitCount = 0;
                }    
            break;          
        }   
    }

    private char convertBitsToByte(int[] byteBuffer) {
        int charAscii = 0;
	for (int i = 0; i < 8; i++){
            int value = (byteBuffer[i] << (7 - i));
            charAscii = charAscii | value;
	}
        return (char) charAscii;
    }
}
