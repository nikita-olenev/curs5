package com.NikitaOlenev.butchershop.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import com.NikitaOlenev.butchershop.entity.Meat;
import com.NikitaOlenev.butchershop.service.MeatService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MenuController {

    private final MeatService meatService;

    @PersistenceContext
    private EntityManager entityManager;

    // Обработчик страницы с каталогом
    @GetMapping("/menu")
    public String menu(@RequestParam(value="ask_name", required = false) String ask_name,
                       @RequestParam(value="filter", required = false) String filter,
                       @RequestParam(value="card_name", required = false) String card_name,
                       Principal principal, Model model) {
        List<Meat> positions = meatService.readAll();
        if (ask_name == null) {
            model.addAttribute("positions", positions);
        }
        else {
            Meat product = findProductByName(ask_name, positions);
            if (product == null) {
                model.addAttribute("positions", positions);
            }
            else {
                model.addAttribute("positions", product);
            }
        }

        if (filter != null) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Meat> productCriteriaQuery = builder.createQuery(Meat.class);
            Root<Meat> root = productCriteriaQuery.from(Meat.class);
            productCriteriaQuery.select(root).orderBy(builder.asc(root.get("price")));
            Query<Meat> query = (Query<Meat>) entityManager.createQuery(productCriteriaQuery);
            positions = query.getResultList();
            model.addAttribute("positions", positions);
        }

        if (card_name != null) {
            Meat product = findProductByName(card_name, positions);
            meatService.addToOrder(product, principal);
        }

        return "menu";
    }

    public Meat findProductByName(String name, List<Meat> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }
}
