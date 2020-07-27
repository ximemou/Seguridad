
package seguridadparte1.InstructionValidations.Filters;

import java.util.ArrayList;
import seguridadparte1.InstructionObject;
import seguridadparte1.InstructionValidations.Constants;
import seguridadparte1.InstructionValidations.IFilter;
import seguridadparte1.SecureSystem;
import seguridadparte1.Subject;

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
