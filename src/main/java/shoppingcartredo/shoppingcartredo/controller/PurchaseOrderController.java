package shoppingcartredo.shoppingcartredo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import shoppingcartredo.shoppingcartredo.model.Order;
import shoppingcartredo.shoppingcartredo.model.Quotation;
import shoppingcartredo.shoppingcartredo.service.QuotationService;
import shoppingcartredo.shoppingcartredo.model.Address;
import shoppingcartredo.shoppingcartredo.model.Item;

@Controller
@RequestMapping
public class PurchaseOrderController {
    
    @Autowired
	private QuotationService quoteSvc;

    @GetMapping(path={"/","/index.html"})
    public String getMain(Model m, HttpSession session){
        
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
            session.setAttribute("order", order);
        }

        m.addAttribute("order", order);
        m.addAttribute("item", new Item());
        return "view1";
    }

    @PostMapping(path="/")
    public String updateCart(@ModelAttribute @Valid Item item, BindingResult binding, Model m, HttpSession session){
        
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
            session.setAttribute("order", order);
        }

        //order must be added as webpage contains ${order}
        m.addAttribute("order", order);

        if(binding.hasErrors()){
            return "view1";
        }

        //if no errors, add item to order. 
        order.add(item);
     
        m.addAttribute("item", new Item());
        // m.addAttribute("item" , item);
        return "view1";
    }

    
    @GetMapping(path="/shippingaddress")
    public String getAddress(Model m, HttpSession session){
        
        if (!hasOrder(session)) {
			return "redirect:/";
		}

        //order must be added as webpage contains ${order}
        m.addAttribute("address", new Address());

        // m.addAttribute("item" , item);
        return "view2";
    }


    @PostMapping(path="/quotation")
	public String postCheckout(Model model, HttpSession session
			, @ModelAttribute @Valid Address address, BindingResult binding) {


		if (!hasOrder(session)) {
			return "redirect:/";
		}

		if (binding.hasErrors()) 
			return "view2";

        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
            session.setAttribute("order", order);
        }

		
		try {
			Quotation quotation = quoteSvc.getQuotations(order);
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("error", ex.getMessage());
			return "view2";
		}

		// Invoice invoice = poSvc.createInvoice(shippingAddress, order, quotation);
		// model.addAttribute(ATTR_INVOICE, invoice);

		// sess.invalidate();

		return "view3";
	}


    private boolean hasOrder(HttpSession sess) {
		return null != sess.getAttribute("order");
	}



}
