package shoppingcartredo.shoppingcartredo.model;

public class Invoice {
	private String invoiceId;
	private Address address;
	private float total;

	public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }
	public String getInvoiceId() { return this.invoiceId; }

	public void setTotal(float total) { this.total = total; }
	public float getTotal() { return this.total; }
	
	public Address getAddress() {return address;}
	public void setAddress(Address address) {this.address = address;}
}
