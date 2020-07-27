
package seguridadparte1;

public class SeguridadParte1 {

    
    public static void main(String[] args) {
        
        SecureSystem sys = SecureSystem.GetInstance();
        
        sys.createSubject("lyle", SecurityLevel.LOW);
        sys.createSubject("hal", SecurityLevel.HIGH);
        
        sys.getReferenceMonitor().CreateNewObject("Lobj", SecurityLevel.LOW);
        sys.getReferenceMonitor().CreateNewObject("Hobj", SecurityLevel.HIGH);
     
        sys.ReadFile();
    }
    
}
