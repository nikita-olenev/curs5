package com.NikitaOlenev.butchershop.controllers;

import lombok.AllArgsConstructor;
import com.NikitaOlenev.butchershop.entity.AppUser;
import com.NikitaOlenev.butchershop.entity.Meat;
import com.NikitaOlenev.butchershop.service.MeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {

    private final MeatService meatService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(@RequestParam(value="del_name", required = false) String del_name,
                       Model model, Principal principal) {
        AppUser user = meatService.getUserByPrincipal(principal);
        if (user != null) {
            List<Meat> orders = user.getMeat();
            if (del_name != null) {
                Meat delProduct = findProductByName(del_name, orders);
                delProduct.setUser(null);
                meatService.update(delProduct);
                orders.remove(delProduct);
                model.addAttribute("orders", orders);
                return "cart";
            }
            else {
                model.addAttribute("orders", orders);
            }
        }
        return "cart";
    }

    public Meat findProductByName(String name, List<Meat> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }
}
