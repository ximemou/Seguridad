
package seguridadparte2;

public class SeguridadParte2 {
 
    public static void main(String[] args) {
        
        SecureSystem sys = SecureSystem.GetInstance();
        
        sys.createSubject("Lyle", SecurityLevel.LOW);
        sys.createSubject("Hal", SecurityLevel.HIGH);
       
        CovertChannel covertChannel = new CovertChannel();
        
        boolean v = false;
        try{
            String fileName = args[0];
            if(args.length > 1 && args[1].equals("v")){
                v = true;
            }
            covertChannel.execute(fileName, v);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Debe ingresar por comando el archivo a enviar por el canal encubierto.");
        }   
    }
}
