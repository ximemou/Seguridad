
package seguridadparte2.InstructionValidations.Filters;

import java.util.ArrayList;
import seguridadparte2.InstructionObject;
import seguridadparte2.InstructionValidations.IFilter;

public class LengthValidator implements IFilter{

    @Override
    public InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception {
        if(words.size() == 3 || words.size() == 4 ){
            return instruction;
        }
        else{
            throw new Exception("Error");
        }
    } 
}
