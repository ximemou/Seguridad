
package seguridadparte2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CovertChannel {

    private String OUTPUT_URL = "Entrada\\output.txt";
    private int NO_BYTE = -1;
    
    public void execute(String fileUrl, boolean v){
        try{
            File f = new File(fileUrl);
            File file= new File (OUTPUT_URL);
            if(!file.exists()){
                file.createNewFile();
            }   
            FileWriter writer = new FileWriter(file);
            
            BufferedReader b = new BufferedReader(new FileReader(f));
            String currentLine = b.readLine();
            while (currentLine != null) {   
                currentLine = currentLine + "\r\n";
                processLine(currentLine, v, writer);
                currentLine = b.readLine();
            }
        }
        catch(IOException e){} 
    }

    private void processLine(String currentLine, boolean v, FileWriter writer) {
        
        ByteArrayInputStream inputStream =new ByteArrayInputStream(currentLine.getBytes());
        int oneByte = inputStream.read();
        
        while(oneByte != NO_BYTE){          
            int [] buffer = new int[8];     
            for(int i = 0; i<8; i++){

                buffer[i] = (oneByte & 0x00000080)>>7;
                oneByte = (oneByte << 1);

                boolean byteIsZero = buffer[i] == 0;
                if(byteIsZero){
                    SecureSystem.GetInstance().getReferenceMonitor().executeRun("Hal", v, writer);
                }
                SecureSystem.GetInstance().getReferenceMonitor().executeRun("Lyle", v, writer);
            }
            oneByte = inputStream.read();
        }       
    }  
}
