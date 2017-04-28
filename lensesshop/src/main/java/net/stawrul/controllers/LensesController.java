package net.stawrul.controllers;

import net.stawrul.model.Lenses;
import net.stawrul.services.LensesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;


/**
 * Kontroler zawierający akcje związane z książkami w sklepie.
 *
 * Parametr "/lenses" w adnotacji @RequestMapping określa prefix dla adresów wszystkich akcji kontrolera.
 */
@RestController
@RequestMapping("/lenses")
public class LensesController {

    //Komponent realizujący logikę biznesową operacji na książkach
    final LensesService lensesService;

    //Instancja klasy LensesService zostanie dostarczona przez framework Spring
    //(wstrzykiwanie zależności przez konstruktor).
    public LensesController(LensesService lensesService) {
        this.lensesService = lensesService;
    }

    /**
     * Pobieranie listy wszystkich książek.
     *
     * Żądanie:
     * GET /lenses
     *
     * @return lista książek
     */
    @GetMapping
    public List<Lenses> listLenses() {
        return lensesService.findAll();
    }

    /**
     * Dodawanie nowej książki.
     *
     * Żądanie:
     * POST /lenses
     *
     * @param lenses obiekt zawierający dane nowej książki, zostanie zbudowany na podstawie danych
     *             przesłanych w ciele żądania (automatyczne mapowanie z formatu JSON na obiekt
     *             klasy Lenses)
     * @param uriBuilder pomocniczy obiekt do budowania adresu wskazującego na nowo dodaną książkę,
     *                   zostanie wstrzyknięty przez framework Spring
     *
     * @return odpowiedź HTTP dla klienta
     */
    @PostMapping
    public ResponseEntity<Void> addLenses(@RequestBody Lenses lenses, UriComponentsBuilder uriBuilder) {

        if (lensesService.find(lenses.getId()) == null) {
            //Identyfikator nie istnieje w bazie danych - nowa książka zostaje zapisana
            lensesService.save(lenses);

            //Jeśli zapisywanie się powiodło zwracana jest odpowiedź 201 Created z nagłówkiem Location, który zawiera
            //adres nowo dodanej książki
            URI location = uriBuilder.path("/lenses/{id}").buildAndExpand(lenses.getId()).toUri();
            return ResponseEntity.created(location).build();

        } else {
            //Identyfikator książki już istnieje w bazie danych. Żądanie POST służy do dodawania nowych elementów,
            //więc zwracana jest odpowiedź z kodem błędu 409 Conflict
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    /**
     * Pobieranie informacji o pojedynczej książce.
     *
     * Żądanie:
     * GET /lenses/{id}
     *
     * @param id identyfikator książki
     *
     * @return odpowiedź 200 zawierająca dane książki lub odpowiedź 404, jeśli książka o podanym identyfikatorze nie
     * istnieje w bazie danych
     */
    @GetMapping("/{id}")
    public ResponseEntity<Lenses> getLenses(@PathVariable UUID id) {
        //wyszukanie książki w bazie danych
        Lenses lenses = lensesService.find(id);

        //W warstwie biznesowej brak książki o podanym id jest sygnalizowany wartością null. Jeśli książka nie została
        //znaleziona zwracana jest odpowiedź 404 Not Found. W przeciwnym razie klient otrzymuje odpowiedź 200 OK
        //zawierającą dane książki w domyślnym formacie JSON
        return lenses != null ? ResponseEntity.ok(lenses) : ResponseEntity.notFound().build();
    }

    /**
     * Aktualizacja danych książki.
     *
     * Żądanie:
     * PUT /lenses/{id}
     *
     * @param lenses
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLenses(@RequestBody Lenses lenses) {
        if (lensesService.find(lenses.getId()) != null) {
            //aktualizacja danych jest możliwa o ile książka o podanym id istnieje w bazie danych
            lensesService.save(lenses);
            return ResponseEntity.ok().build();

        } else {
            //nie odnaleziono książki o podanym id - odpowiedź 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

}
