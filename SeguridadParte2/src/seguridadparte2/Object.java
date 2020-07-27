
package seguridadparte2;

import java.util.Objects;

public class Object {
    private String name;
    private int value;
    private SecurityLevel securityLevel;

    public Object(String name, SecurityLevel securityLevel) {
        this.name = name;
        this.value = 0;
        this.securityLevel = securityLevel;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\t" + this.getName() + " has value: " + this.getValue() + "\n";
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        Object objectCompare = (Object) obj;
        return this.name.equalsIgnoreCase(objectCompare.getName());
    }
}
