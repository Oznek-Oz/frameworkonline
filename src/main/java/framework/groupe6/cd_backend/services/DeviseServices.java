package framework.groupe6.cd_backend.services;

import framework.groupe6.cd_backend.entites.Devise;
import framework.groupe6.cd_backend.repository.DeviseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DeviseServices {
    @Autowired
    private DeviseRepository deviseRepository;
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ConversionServices.class);


    public void saveDevise(){
        String url = "https://api.frankfurter.app/currencies";

        try {
            Map<String , String> reponse = restTemplate.getForObject(url, Map.class);
            //logger.info("Réponse API : {}", reponse);
            for (Map.Entry<String , String> entry:reponse.entrySet()){
                Devise d = new Devise(entry.getKey() , entry.getValue());
                deviseRepository.save(d);
            }
            System.out.println("Devises enregistrées avec succès !");
        } catch (Exception e){
            System.err.println("Erreur lors de la récupération des devises: " + e.getMessage());
        }

        //String code = "EUR",nom = "CAD";
        //Devise devise = new Devise(code , nom);

        //return deviseRepository.save(devise);
    }

    public List<Devise> getAllDevise(){
        return deviseRepository.findAll();
    }

}
