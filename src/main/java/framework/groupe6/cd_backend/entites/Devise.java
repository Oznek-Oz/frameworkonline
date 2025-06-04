package framework.groupe6.cd_backend.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerators;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "devise")
public class Devise {
    @Id
    String code;
    String nom;

    public Devise(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Devise() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
