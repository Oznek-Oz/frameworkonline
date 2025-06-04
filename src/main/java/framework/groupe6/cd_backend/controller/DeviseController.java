package framework.groupe6.cd_backend.controller;

import framework.groupe6.cd_backend.entites.Devise;
import framework.groupe6.cd_backend.services.DeviseServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name="Devise API" , description = "Obtenir la liste des devises")
@CrossOrigin(origins = "https://frontend.com")
@RestController
@RequestMapping(path = "devise")
public class DeviseController {
    private final DeviseServices deviseServices;

    public DeviseController(DeviseServices deviseServices) {
        this.deviseServices = deviseServices;
    }

    @GetMapping(path = "liste")
    public List<Devise> listeDevise(){
        System.out.println("Liste des devises");
        deviseServices.saveDevise();

        return deviseServices.getAllDevise();
    }
}
