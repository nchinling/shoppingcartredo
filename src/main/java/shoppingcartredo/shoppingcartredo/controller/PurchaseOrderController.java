package shoppingcartredo.shoppingcartredo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import shoppingcartredo.shoppingcartredo.model.Item;

@Controller
@RequestMapping
public class PurchaseOrderController {
    
    @GetMapping(path="/")
    public String getMain(Model m, HttpSession session){
        
   
        m.addAttribute("item", new Item());
        return "view1";
    }
}
