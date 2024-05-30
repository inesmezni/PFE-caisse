package com.caisse.entity;

import java.time.Instant;
import java.util.List;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "facture")
public class Facture extends AbstractEntity{
    @Column(name = "code")
    private String code;

    @Column(name = "datevente")
    private Instant dateVente;

    @Column(name = "commentaire")
    private String commentaire;



    @Column(name = "type_paiement")
    @Enumerated(EnumType.STRING)
    private Type_paiement Type_paiement;



    @OneToMany(mappedBy = "facture")
    private List<LigneFacture> ligneFacture;
}
