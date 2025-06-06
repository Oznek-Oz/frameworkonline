package framework.groupe6.cd_backend.controller;

import framework.groupe6.cd_backend.entites.Devise;
import framework.groupe6.cd_backend.services.DeviseServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name="Devise API" , description = "Obtenir la liste des devises")
//@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.43.92:5173"})
@RestController
@RequestMapping(path = "devise")
public class DeviseController {
    private final DeviseServices deviseServices;

    public DeviseController(DeviseServices deviseServices) {
        this.deviseServices = deviseServices;
    }

    @GetMapping(path = "liste", produces = APPLICATION_JSON_VALUE)
    public List<Devise> listeDevise(){
        System.out.println("Liste des devises");

        //Renvoyer d'abord les devises locales
        List<Devise> devisesLocales = deviseServices.getAllDevise();

        //Lancer la maj en tâche de fond (non bloquante)
        new Thread(() -> {
            try{
                deviseServices.saveDevise(); //maj via l'API externe
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return devisesLocales;
    }
}
