package shoppingcartredo.shoppingcartredo.model;

import java.util.HashMap;
import java.util.Map;

public class Quotation {
    
    private String quoteId;
    private Map<String, Float> quotations = new HashMap<>();
    
    public String getQuoteId() {return quoteId;}
    public void setQuoteId(String quoteId) {this.quoteId = quoteId;}
    
    public Map<String, Float> getQuotations() {return quotations;}
    public void setQuotations(Map<String, Float> quotations) {this.quotations = quotations;}

    
}
