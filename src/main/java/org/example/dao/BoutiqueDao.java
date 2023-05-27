package org.example.dao;

import org.example.entities.Boutique;

import java.util.List;

public interface BoutiqueDao {
    void insert(Boutique boutique);
    void update(Boutique boutique);
    void deleteById(Integer id);
    Boutique findById(Integer id);
    List<Boutique> findAll();
    Boutique findByName(String name) ;

}
