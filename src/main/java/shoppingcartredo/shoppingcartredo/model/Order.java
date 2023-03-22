package shoppingcartredo.shoppingcartredo.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;


public class Order {
	//field
	@NotEmpty(message="Please ensure there is an item in the cart.")
	private List<Item> contents = new LinkedList<>();

	public Order() {}
	
	public Order(List<Item> contents) {
		this.contents = contents;
	}


	//Generate from getters and setters. 
	public List<Item> getContents() {return contents;}
	public void setContents(List<Item> contents) {this.contents = contents;}
	
	
	public void add(Item item) {
		Item found = null;

		for (Item i : contents) {
			if (i.getItem().equals(item.getItem())) {
				found = i;
				break;
			}
		}
		if (found == null) {
			contents.add(item);
		} else {
			found.add(item.getQuantity());
		}
	}

	@Override
	public String toString() {
		return "Order [contents=" + contents + "]";
	}

}

