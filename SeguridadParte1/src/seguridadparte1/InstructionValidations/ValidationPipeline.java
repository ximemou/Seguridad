
package seguridadparte1.InstructionValidations;

import java.util.ArrayList;
import seguridadparte1.InstructionValidations.Filters.LengthValidator;
import seguridadparte1.InstructionValidations.Filters.ObjectValidator;
import seguridadparte1.InstructionValidations.Filters.SubjectValidator;
import seguridadparte1.InstructionValidations.Filters.TypeValidator;
import seguridadparte1.InstructionValidations.Filters.ValueValidator;

public class ValidationPipeline extends Pipeline{
    
     public ValidationPipeline(){
        filters = new ArrayList<IFilter>();
    }
    
    public void initPipeline(){
        Register(new LengthValidator());
        Register(new TypeValidator());
        Register(new SubjectValidator());
        Register(new ObjectValidator());
        Register(new ValueValidator());      
    }  
}
