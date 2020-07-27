
package seguridadparte2.InstructionValidations.Filters;

import java.util.ArrayList;
import seguridadparte2.InstructionObject;
import seguridadparte2.InstructionValidations.Constants;
import seguridadparte2.InstructionValidations.IFilter;

public class TypeValidator implements IFilter{

    @Override
    public InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception {
        boolean isReadType = words.get(Constants.TYPE_INDEX).equalsIgnoreCase("READ") && words.size() == 3;
        boolean isWriteType = words.get(Constants.TYPE_INDEX).equalsIgnoreCase("WRITE") && words.size() == 4;
        if(isReadType || isWriteType){
            instruction.setTypeOfInstruction(words.get(Constants.TYPE_INDEX));
        }
        else{
            throw new Exception("Error");
        }
        return instruction;
    }
}
