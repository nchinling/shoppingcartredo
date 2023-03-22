package shoppingcartredo.shoppingcartredo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Address {
    
    @NotBlank(message="Please provide a name")
    @Size(min=2, message="Need to be at least 2 characters long")
    private String name;

    @NotBlank(message="Please provide an address")
    private String address;

    public Address() {}

    public Address(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    @Override
    public String toString() {
        return "name=" + name + ", address=" + address + "]";
    }

    

    

    

    

}
