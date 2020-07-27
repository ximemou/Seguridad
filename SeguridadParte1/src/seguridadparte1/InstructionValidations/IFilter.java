
package seguridadparte1.InstructionValidations;

import java.util.ArrayList;
import seguridadparte1.InstructionObject;

public interface IFilter {
    
    InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception;
    
}
