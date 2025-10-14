package banquesang.model;

import jakarta.persistence.*;
import banquesang.enums.Urgence;
import banquesang.enums.ReceveurStatus;

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
}
