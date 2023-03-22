package shoppingcartredo.shoppingcartredo.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
				order.getContents().stream()
					.map(v -> v.getItem())
					.toList()
				);
	}


    public Quotation getQuotations(List<String> order) throws Exception {
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

    


}
