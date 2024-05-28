package com.caisse.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stockup")

public class StockUp extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant dateCommande;





   // @ManyToOne

 /*   @JoinColumn(name = "idfournisseur")
    private Fournisseur fournisseur;*/

    @OneToMany(mappedBy = "stockUp")
    private List<LigneStockUp> lignestockUp;

}
