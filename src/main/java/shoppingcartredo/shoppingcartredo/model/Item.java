package shoppingcartredo.shoppingcartredo.model;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Item implements Serializable {

    @NotBlank(message="You must select an item")
    private String item;

    @Min(value = 1, message = "You must add at least 1 item")
    private int quantity;

    public Item() {}

    public Item(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void add(int quantity){this.quantity += quantity;}
    public void add(){this.quantity++;}

    
    @Override
    public String toString() {
        return "Item{item=%s, quantity=%d}".formatted(item, quantity);
    }

    

    

}
