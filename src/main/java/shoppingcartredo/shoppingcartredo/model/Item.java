package shoppingcartredo.shoppingcartredo.model;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Item implements Serializable {
    
    @Size(min = 1, message = "We do not stock the item")
    @NotNull(message="We do not stock the item")
    private String item;

    @Min(value = 1, message = "Add at least 1 quantity")
    private int quantity;
    
    public Item() {}

    public Item(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {return item;}
    public void setItem(String item) {this.item = item;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    @Override
    public String toString() {
        return "item=" + item + ", quantity=" + quantity + "]";
    }


    
}
