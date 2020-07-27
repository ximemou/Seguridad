
package seguridadparte1;


import java.util.ArrayList;
import java.util.List;


public class ObjectManager {
   
    private ArrayList<Object> objects;

    public ObjectManager() {
       objects=new ArrayList<Object>();
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }
 
    public void Read(Subject subject,String objectName, boolean canRead){
        Object obj=this.getObjectByName(objectName);
        if(canRead){
            subject.setTemp(obj.getValue());
        }
        else{
            subject.setTemp(0);
        }     
    }

    public void Write(String objectName,int value){
         Object obj=this.getObjectByName(objectName);
         obj.setValue(value); 
    }
    
     public Object getObjectByName(String name){
        for(int i=0;i<objects.size();i++){
            if(objects.get(i).getName().equalsIgnoreCase(name)){
                return objects.get(i);
            }
        }
        return null;
    }
     
     public void CreateNewObject(String name,SecurityLevel securityLevel){       
         Object newObject = this.getObjectByName(name);
         if(newObject == null){
             newObject = new Object(name, securityLevel);
             objects.add(newObject);
         }  
     }
}
