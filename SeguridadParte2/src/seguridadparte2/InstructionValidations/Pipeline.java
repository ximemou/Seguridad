
package seguridadparte2.InstructionValidations;

import java.util.ArrayList;
import java.util.List;
import seguridadparte2.InstructionObject;

public abstract class Pipeline {
    
    List<IFilter> filters;
    
    public  void Register(IFilter filter){
        filters.add(filter);   
    }
    
    public InstructionObject PerformOperation(InstructionObject instruction, ArrayList<String> words) throws Exception{   
        InstructionObject transformedInstruction = instruction;            
            for(int i=0; i<filters.size();i++){
                transformedInstruction = filters.get(i).executeFilter(transformedInstruction, words);
            }
        return transformedInstruction;
    } 
}
