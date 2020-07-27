
package seguridadparte2.InstructionValidations.Filters;

import java.util.ArrayList;
import seguridadparte2.InstructionObject;
import seguridadparte2.InstructionValidations.Constants;
import seguridadparte2.InstructionValidations.IFilter;

public class ValueValidator implements IFilter{

    @Override
    public InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception {
        
        if(words.get(Constants.TYPE_INDEX).equalsIgnoreCase("WRITE")){
            String valueString = words.get(Constants.VALUE_INDEX);
            int value = Integer.parseInt(valueString);
            instruction.setValue(value);
        }
        return instruction;
    }
}
