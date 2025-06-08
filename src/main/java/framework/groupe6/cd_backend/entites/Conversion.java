package framework.groupe6.cd_backend.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name =  "conversion")
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;
    private String deviseSource;
    private String deviseCible;
    private double montantSource;
    private double montantConverti;
    private double taux;
    private LocalDateTime dateConversion;

    public Conversion() {

    }

    public Conversion(
            int id, String deviseSource, String deviseCible, double montantSource,
            double montantConverti, double taux, LocalDateTime dateConversion)
    {
        this.id = id;
        this.deviseSource = deviseSource;
        this.deviseCible = deviseCible;
        this.montantSource = montantSource;
        this.montantConverti = montantConverti;
        this.taux = taux;
        this.dateConversion = dateConversion;
    }

    public Conversion(String from, String to, double montant,
                      double result, double taux, LocalDateTime now)
    {
        this.deviseSource = from;
        this.deviseCible = to;
        this.taux = taux;
        this.montantSource = montant;
        this.montantConverti = result;
        this.dateConversion = now;
    }
    public Conversion(String from, String to, double montant,
                      double result, double taux, LocalDateTime now, String userID)
    {
        this.deviseSource = from;
        this.deviseCible = to;
        this.taux = taux;
        this.montantSource = montant;
        this.montantConverti = result;
        this.dateConversion = now;
        this.userID = userID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviseSource() {
        return deviseSource;
    }

    public void setDeviseSource(String deviseSource) {
        this.deviseSource = deviseSource;
    }

    public String getDeviseCible() {
        return deviseCible;
    }

    public void setDeviseCible(String deviseCible) {
        this.deviseCible = deviseCible;
    }

    public double getMontantSource() {
        return montantSource;
    }

    public void setMontantSource(double montantSource) {
        this.montantSource = montantSource;
    }

    public double getMontantConverti() {
        return montantConverti;
    }

    public void setMontantConverti(double montantConverti) {
        this.montantConverti = montantConverti;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public LocalDateTime getDateConversion() {
        return dateConversion;
    }

    public void setDateConversion(LocalDateTime dateConversion) {
        this.dateConversion = dateConversion;
    }
}
