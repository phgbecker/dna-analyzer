package br.com.meli.dna.analyzer.model;

import br.com.meli.dna.analyzer.business.objects.DnaSpecies;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "dna")
public class DnaModel {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Lob
    @Column(name = "dna", nullable = false)
    private String dna;

    @Enumerated(STRING)
    @Column(name = "species", nullable = false)
    private DnaSpecies species;

    @Temporal(TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    public DnaModel() {
    }

    public DnaModel(String id, String dna, DnaSpecies species, Date createdAt) {
        this.id = id;
        this.dna = dna;
        this.species = species;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public DnaSpecies getSpecies() {
        return species;
    }

    public void setSpecies(DnaSpecies species) {
        this.species = species;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
