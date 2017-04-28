package net.stawrul.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * Klasa encyjna reprezentująca towar w sklepie (książkę).
 */
@Entity
@EqualsAndHashCode(of = "id")
@NamedQueries(value = {
        @NamedQuery(name = Lenses.FIND_ALL, query = "SELECT b FROM Lenses b")
})
public class Lenses {
    public static final String FIND_ALL = "Lenses.FIND_ALL";

    @Getter
    @Id
    UUID id = UUID.randomUUID();

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    Integer amount;
    
    @Getter
    @Setter
    Integer cost;
    
    @Getter
    @Setter
    String color;
    
    @Getter
    @Setter
    String expirationTime;
}
