package banquesang.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receveur_id", nullable = false)
    private Receveur receveur;

    @OneToOne
    @JoinColumn(name = "donneur_id", nullable = false, unique = true)
    private Donneur donneur;

    private LocalDate dateDonation;

    @Column(nullable = false)
    private int quantite;


    public Donation() {
        this.dateDonation = LocalDate.now();
        this.quantite = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Receveur getReceveur() {
        return receveur;
    }

    public void setReceveur(Receveur receveur) {
        this.receveur = receveur;
    }

    public Donneur getDonneur() {
        return donneur;
    }

    public void setDonneur(Donneur donneur) {
        this.donneur = donneur;
    }

    public LocalDate getDateDonation() {
        return dateDonation;
    }

    public void setDateDonation(LocalDate dateDonation) {
        this.dateDonation = dateDonation;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
