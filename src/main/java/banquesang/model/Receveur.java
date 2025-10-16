package banquesang.model;

import jakarta.persistence.*;
import banquesang.enums.Urgence;
import banquesang.enums.ReceveurStatus;

import java.util.ArrayList;

@Entity
@Table(name = "receveurs")
@PrimaryKeyJoinColumn(name = "user_id")
public class Receveur extends User {

    @Enumerated(EnumType.STRING)
    private Urgence urgence;

    @Enumerated(EnumType.STRING)
    private ReceveurStatus receveurStatus;

    public Urgence getUrgence() { return urgence; }
    public void setUrgence(Urgence urgence) { this.urgence = urgence; }

    public ReceveurStatus getReceveurStatus() { return receveurStatus; }
    public void setReceveurStatus(ReceveurStatus receveurStatus) { this.receveurStatus = receveurStatus; }

    @OneToMany(mappedBy = "receveur", cascade = CascadeType.ALL)
    private java.util.List<Donation> donations = new ArrayList<>();

    public java.util.List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(java.util.List<Donation> donations) {
        this.donations = donations;
    }


}
