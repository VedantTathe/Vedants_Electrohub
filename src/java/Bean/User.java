
package Bean;

import java.io.Serializable;

public class User implements Serializable{
    
    private String Name;
    private String Sname;
    private String Email;
    private String Mnumber;
    private String Password;
    private String Address;
    private String Type;

    public User(String Name, String Sname, String Email, String Mnumber, String Password, String Address, String Type) {
        this.Name = Name;
        this.Sname = Sname;
        this.Email = Email;
        this.Mnumber = Mnumber;
        this.Password = Password;
        this.Address = Address;
        this.Type = Type;
    }

    public String getName() {
        return Name;
    }

    public String getSname() {
        return Sname;
    }

    public String getEmail() {
        return Email;
    }

    public String getMnumber() {
        return Mnumber;
    }

    public String getPassword() {
        return Password;
    }

    public String getAddress() {
        return Address;
    }
    
     public String getType() {
        return Type;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setMnumber(String Mnumber) {
        this.Mnumber = Mnumber;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
    public void setType(String Type) {
        this.Type = Type;
    }
}
