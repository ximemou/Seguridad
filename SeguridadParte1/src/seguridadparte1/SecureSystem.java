
package seguridadparte1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SecureSystem {
    
    private String FILE_URL = "Entrada\\parte1.txt";
    private ArrayList<Subject> subjects;
    private static SecureSystem secureSystemInstance = null;
    private ReferenceMonitor referenceMonitor;
    
    private SecureSystem(){
        subjects = new ArrayList<Subject>();
        referenceMonitor = new ReferenceMonitor();
    }
    
    public static SecureSystem GetInstance()
    {
        if(secureSystemInstance == null){
            secureSystemInstance = new SecureSystem();
        }
        return secureSystemInstance;
    }  

    public ReferenceMonitor getReferenceMonitor() {
        return referenceMonitor;
    }
    
    public void createSubject(String name,SecurityLevel securityLevel){
        Subject newSubject = new Subject(name, securityLevel);
        subjects.add(newSubject);
    }
    
    public void PrintState(InstructionObject instruction){
        System.out.println(instruction.toString());
    }
    
    public void ReadFile(){
        
        try{
            File f = new File(FILE_URL);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String currentLine = b.readLine();
            while (currentLine != null) {   
                processLine(currentLine);
                currentLine = b.readLine();
            }
        }
        catch(IOException e){}
    }

    private void processLine(String currentLine) {
        InstructionObject instruction = new InstructionObject(currentLine);
        PrintState(instruction);
    }
    
    public Subject getSubjectByName(String subjectName){
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getName().equalsIgnoreCase(subjectName)) {
                return subjects.get(i);
            }
        }
        return null;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
}
