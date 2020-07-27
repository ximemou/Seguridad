
package seguridadparte2.InstructionValidations.Filters;

import java.util.ArrayList;
import seguridadparte2.InstructionObject;
import seguridadparte2.InstructionValidations.Constants;
import seguridadparte2.InstructionValidations.IFilter;
import seguridadparte2.SecureSystem;
import seguridadparte2.Subject;

public class SubjectValidator implements IFilter {

    @Override
    public InstructionObject executeFilter(InstructionObject instruction, ArrayList<String> words) throws Exception {
        
        String subjectName = words.get(Constants.SUBJECT_INDEX);
        Subject subject = SecureSystem.GetInstance().getSubjectByName(subjectName);
        if(subject != null){
            instruction.setSubjectName(subjectName);
        }
        else{
            throw new Exception("Error");
        }
        return instruction;
    }  
}
