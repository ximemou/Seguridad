
package seguridadparte2.InstructionValidations.Filters;

import java.util.ArrayList;
import seguridadparte2.InstructionObject;
import seguridadparte2.InstructionValidations.Constants;
import seguridadparte2.InstructionValidations.IFilter;
import seguridadparte2.SecureSystem;
import seguridadparte2.Subject;

public class ObjectValidator implements IFilter{

    @Override
    public InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception {
        
        String objectName = words.get(Constants.OBJECT_INDEX);
        seguridadparte2.Object object = SecureSystem.GetInstance().getReferenceMonitor().getObjectManager().getObjectByName(objectName);
        if(object != null){
            instruction.setObjectName(objectName);
        }
        else{
            throw new Exception("Error");
        }
        return instruction;
    } 
}