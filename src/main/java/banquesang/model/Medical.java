package banquesang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medical")
public class Medical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean hepatiteB = false;
    private boolean hepatiteC = false;
    private boolean vih = false;
    private boolean diabete = false;
    private boolean grossesse = false;
    private boolean allaitement = false;

    public Medical() {}

    public boolean aDesContreIndications() {
        return hepatiteB || hepatiteC || vih || diabete || grossesse || allaitement;
    }

    public Long getId() { return id; }

    public boolean isHepatiteB() { return hepatiteB; }
    public void setHepatiteB(boolean hepatiteB) { this.hepatiteB = hepatiteB; }

    public boolean isHepatiteC() { return hepatiteC; }
    public void setHepatiteC(boolean hepatiteC) { this.hepatiteC = hepatiteC; }

    public boolean isVih() { return vih; }
    public void setVih(boolean vih) { this.vih = vih; }

    public boolean isDiabete() { return diabete; }
    public void setDiabete(boolean diabete) { this.diabete = diabete; }

    public boolean isGrossesse() { return grossesse; }
    public void setGrossesse(boolean grossesse) { this.grossesse = grossesse; }

    public boolean isAllaitement() { return allaitement; }
    public void setAllaitement(boolean allaitement) { this.allaitement = allaitement; }
}
