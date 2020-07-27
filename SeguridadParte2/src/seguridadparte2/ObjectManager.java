
package seguridadparte2;


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
     
     public void Create(String objectName, SecurityLevel securityLevel){       
         Object newObject = this.getObjectByName(objectName);
         if(newObject == null){
             newObject = new Object(objectName, securityLevel);
             objects.add(newObject);
         }  
     }
     
     public void Destroy(String objectName){
         Object toDelete = this.getObjectByName(objectName);
         if(toDelete != null){
             objects.remove(toDelete);
         }
     }
}
