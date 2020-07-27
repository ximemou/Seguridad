
package seguridadparte1;


public class Subject {
    private String name;
    private int temp;
    private SecurityLevel securityLevel;

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public Subject(String name,SecurityLevel level) {
        this.name = name;
        this.temp=0;
        this.securityLevel=level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "\t" + this.getName() + " has recently read: " + this.getTemp() + "\n";
    }
    
    
}
