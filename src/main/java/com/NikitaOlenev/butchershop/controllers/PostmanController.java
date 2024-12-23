package com.NikitaOlenev.butchershop.controllers;

import lombok.AllArgsConstructor;
import com.NikitaOlenev.butchershop.dto.MeatDTO;
import com.NikitaOlenev.butchershop.entity.Meat;
import com.NikitaOlenev.butchershop.service.MeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostmanController {

    private final MeatService meatService;

    //Обработчики запросов для работы с API приложеня при помощи Postman
    @RequestMapping(value = "/postman", method = RequestMethod.GET)
    public ResponseEntity<List<Meat>> readAll() {
        return new ResponseEntity<>(meatService.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman", method = RequestMethod.POST)
    public ResponseEntity<Meat> create(@RequestBody MeatDTO dto) {
        return new ResponseEntity<>(meatService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman", method = RequestMethod.PUT)
    public ResponseEntity<Meat> update(@RequestBody Meat product) {
        return new ResponseEntity<>(meatService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/postman/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        meatService.delete(id);
        return HttpStatus.OK;
    }
}
