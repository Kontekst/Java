package net.stawrul.services;

import net.stawrul.model.Lenses;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Komponent (serwis) biznesowy do realizacji operacji na książkach.
 */
@Service
public class LensesService extends EntityService<Lenses> {

    //Instancja klasy EntityManger zostanie dostarczona przez framework Spring
    //(wstrzykiwanie zależności przez konstruktor).
    public LensesService(EntityManager em) {

        //Lenses.class - klasa encyjna, na której będą wykonywane operacje
        //Lenses::getId - metoda klasy encyjnej do pobierania klucza głównego
        super(em, Lenses.class, Lenses::getId);
    }

    /**
     * Pobranie wszystkich książek z bazy danych.
     *
     * @return lista książek
     */
    public List<Lenses> findAll() {
        //pobranie listy wszystkich książek za pomocą zapytania nazwanego (ang. named query)
        //zapytanie jest zdefiniowane w klasie Lenses
        return em.createNamedQuery(Lenses.FIND_ALL, Lenses.class).getResultList();
    }

}
