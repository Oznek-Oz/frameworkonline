package framework.groupe6.cd_backend.services;

import framework.groupe6.cd_backend.entites.Conversion;
import framework.groupe6.cd_backend.repository.ConversionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ConversionServices {

    //private static final Logger logger = LoggerFactory.getLogger(ConversionServices.class);
    private final ConversionRepository conversionRepository;
    private final RestTemplate restTemplate;

    // Constructeur avec injection de dépendance Repository
    public ConversionServices(ConversionRepository conversionRepository) {
        this.conversionRepository = conversionRepository; //Dépendance pour les opération CRUD avec la BD
        this.restTemplate = new RestTemplate(); //Dépendance pour appeler l'API externe
    }

    public Conversion convert(String from, String to, double montant, String userID){

        String API_URL = "https://api.frankfurter.app/latest";

        if (montant < 0) {
            throw new IllegalArgumentException("Le montant doit être positif.");
        }
        //Verifie que la devise source n'est pas la device cible
        if (from.equalsIgnoreCase(to)) {
            throw new IllegalArgumentException("Les devises source et cible doivent être différentes.");
        }

        String url = API_URL + "?from=" + from + "&to=" + to + "&amount=" + montant;

        System.out.println("URL appelée : {}" + url);

        Map response = restTemplate.getForObject(url, Map.class);

        System.out.println("URL appelée : {}" + response);

        if (response == null || !response.containsKey("rates")){
            throw new RuntimeException("Erreur d'appel à l'API taux de change");
        }

        // Extraire le sous-map 'rates'
        Map<String, Object> rates = (Map<String, Object>) response.get("rates");

        // Extraire la valeur pour la devise cible
        Object rateObj = rates.get(to);
        if (rateObj == null) {
            throw new RuntimeException("Taux non trouvé pour la devise cible");
        }

        //Conversion en nombre
        double result = ((Number) rateObj).doubleValue();
        double taux = (result / montant);

        Conversion conversion = new Conversion(
                from,
                to,
                montant,
                result,
                taux,
                LocalDateTime.now(),
                userID
        );

        saveConversion(conversion);
        return conversion;
    }

    private void saveConversion (Conversion conversion){
        conversionRepository.save(conversion);

        long count = conversionRepository.findByUserID(conversion.getUserID()).size();
        System.out.println("Conversion de :'" + conversion.getUserID() +"' : " + count);
        if (count > 15){
            List<Conversion> toDelete = conversionRepository
                    .findTop5ByOrderByDateConversionAsc()
                    .stream()
                    .filter(c -> c.getUserID().equals(conversion.getUserID()))
                    .toList();
            conversionRepository.deleteAll(toDelete);
        }
    }

    public List<Conversion> getAllConversions() {
        return conversionRepository.findAll();
    }

    public List<Conversion> getConversionsByUserId(String userID){
        return conversionRepository.findByUserID(userID);
    }

    public void creer(Conversion conversion) {
        conversionRepository.save(conversion);
    }
}
