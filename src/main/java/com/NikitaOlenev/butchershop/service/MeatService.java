package com.NikitaOlenev.butchershop.service;

import lombok.AllArgsConstructor;
import com.NikitaOlenev.butchershop.dto.MeatDTO;
import com.NikitaOlenev.butchershop.entity.AppUser;
import com.NikitaOlenev.butchershop.entity.Meat;
import com.NikitaOlenev.butchershop.repository.MeatRepository;
import com.NikitaOlenev.butchershop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class MeatService {

    private final MeatRepository meatRepository;
    private final UserRepository userRepository;

    public Meat create(MeatDTO dto) {
        Meat meat = Meat.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .text(dto.getText())
                .build();
        return meatRepository.save(meat);
    }

    public List<Meat> readAll() {
        return meatRepository.findAll();
    }

    public Meat update(Meat meat) {
        return meatRepository.save(meat);
    }

    public void delete(Long id) {
        meatRepository.deleteById(id);
    }

    public Meat addToOrder(Meat meat, Principal principal) {
        meat.setUser(getUserByPrincipal(principal));
        return meatRepository.save(meat);
    }

    public AppUser getUserByPrincipal(Principal principal) {
        if (principal == null) return null;
        return userRepository.findByName(principal.getName()).get();
    }
}
