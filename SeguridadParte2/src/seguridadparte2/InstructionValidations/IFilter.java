
package seguridadparte2.InstructionValidations;

import java.util.ArrayList;
import seguridadparte2.InstructionObject;

public interface IFilter {
    
    InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception;
    
}
