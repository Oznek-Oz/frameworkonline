package framework.groupe6.cd_backend.controller;

import framework.groupe6.cd_backend.entites.Conversion;
import framework.groupe6.cd_backend.services.ConversionServices;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.swing.plaf.PanelUI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(
        name = "Conversion API",
        description = "Permet de convertir les devises et consulter l'historique"
)
//@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.43.92:5173"})
@RestController //Permet aux méthodes de retourner des objects JSON
@RequestMapping(path = "conversion") //racine de l'API
public class ConversionController {
    private final ConversionServices conversionServices;

    /** Injection de dépendance avec services(ConersionServices) **/
    public ConversionController(ConversionServices conversionServices) {
        this.conversionServices = conversionServices;
    }

    @Operation(summary = "Créer une conversion manuellement")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Conversion conversion) {
        this.conversionServices.creer(conversion);
    }

    @Operation(summary = "Convertir une devise via l'API externe")
    @GetMapping("convert")
    public Conversion conversionDevise(
            @Pattern(regexp = "^[A-Z]{3}$")
            @NotBlank(message = "Devise source obligatoire")
            @RequestParam String from,

            @Pattern(regexp = "^[A-Z]{3}$")
            @NotBlank(message = "Devise cible obligatoire")
            @RequestParam String to,

            @PositiveOrZero
            @Positive(message = "Le montant doit être strictement positif")
            @RequestParam double montant,

            @RequestParam String userID
    ){
        return conversionServices.convert(from,to,montant,userID);
    }

    @Operation(summary = "Obtenir l'historique des conversions")
    @GetMapping(value = "history" , produces = APPLICATION_JSON_VALUE)
    public List<Conversion> getHistory(){
        return conversionServices.getAllConversions();
    }

    @Operation(summary = "Obtenir l'historique des conversions d'un utilisateur")
    @GetMapping(path = "history/user", produces = APPLICATION_JSON_VALUE)
    public List<Conversion> getUserHistory(@RequestParam String userID) {
        return conversionServices.getConversionsByUserId(userID);
    }

        @Operation(summary="Garder l'api en vie")
        @GetMapping(path="ping", produces=APPLICATION_JSON_VALUE)
        public String ping(){
                return "pong";
        }
}

