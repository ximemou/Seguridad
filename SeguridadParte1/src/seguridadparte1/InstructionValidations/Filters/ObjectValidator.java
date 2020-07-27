
package seguridadparte1.InstructionValidations.Filters;

import java.util.ArrayList;
import seguridadparte1.InstructionObject;
import seguridadparte1.InstructionValidations.Constants;
import seguridadparte1.InstructionValidations.IFilter;
import seguridadparte1.SecureSystem;
import seguridadparte1.Subject;

public class ObjectValidator implements IFilter{

    @Override
    public InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception {
        
        String objectName = words.get(Constants.OBJECT_INDEX);
        seguridadparte1.Object object = SecureSystem.GetInstance().getReferenceMonitor().getObjectManager().getObjectByName(objectName);
        if(object != null){
            instruction.setObjectName(objectName);
        }
        else{
            throw new Exception("Error");
        }
        return instruction;
    } 
}