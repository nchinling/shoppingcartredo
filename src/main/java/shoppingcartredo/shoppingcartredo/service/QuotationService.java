package shoppingcartredo.shoppingcartredo.service;

import java.io.StringReader;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import shoppingcartredo.shoppingcartredo.model.Address;
import shoppingcartredo.shoppingcartredo.model.Invoice;
import shoppingcartredo.shoppingcartredo.model.Item;
import shoppingcartredo.shoppingcartredo.model.Order;
import shoppingcartredo.shoppingcartredo.model.Quotation;

@Service
public class QuotationService {
    
    public static final String QUOTATION = "https://quotation.chuklee.com/quotation";

    //method takes in the List<Item> called contents, passes it to a stream, 
    //and for each Item object, obtains the item name,
    //before sending it to a list. The list is returned for further processing. 
    public Quotation getQuotations(Order order) throws Exception {
		return getQuotations(
                    //this is List<string> order
                    order.getContents().stream()
					.map(v -> v.getItem())
					.toList()
				);

		//without use of streams
		// List<String> items = new ArrayList<>();
		// List<OrderItem> contents = order.getContents();
		
		// for (int i = 0; i < contents.size(); i++) {
		// 	OrderItem item = contents.get(i);
		// 	String itemName = item.getItem();
		// 	items.add(itemName);
		// }
	}


    public Quotation getQuotations(List<String> order) throws Exception {
		
        //need to passed to QSys as a JSON array. 
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder(order);

		RequestEntity<String> req = RequestEntity.post(QUOTATION)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(arrBuilder.build().toString());

		ResponseEntity<String> resp;
		RestTemplate template = new RestTemplate();
		try {
			resp = template.exchange(req, String.class);
		} catch (Exception ex) {
			throw ex;
		}

		String payload = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject json = reader.readObject();

		Quotation quotation = new Quotation();
		quotation.setQuoteId(json.getString("quoteId"));

		json.getJsonArray("quotations").stream()
			.map(i -> i.asJsonObject())
			.forEach(i -> {
				quotation.addQuotation(i.getString("item"), (float)i.getJsonNumber("unitPrice").doubleValue());
			});

		return quotation;
	}

	public Invoice createInvoice(Address address, Order order, Quotation quotation) {

		float total = 0f;

		Invoice invoice = new Invoice();
		invoice.setInvoiceId(quotation.getQuoteId());

		for (Item item: order.getContents())
			total += quotation.getQuotation(item.getItem()) * item.getQuantity();
		invoice.setTotal(total);

		invoice.setAddress(address);

		return invoice;
	}

    


}
