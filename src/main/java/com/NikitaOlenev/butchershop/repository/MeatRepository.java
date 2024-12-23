package com.NikitaOlenev.butchershop.repository;

import com.NikitaOlenev.butchershop.entity.Meat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeatRepository extends JpaRepository<Meat, Long> {

}
