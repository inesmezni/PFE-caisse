package com.caisse.entity;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity {

    @Column(name = "codearticle")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxtva")
    private BigDecimal tauxTva;

    @Column(name = "prixunitairettc")
    private BigDecimal prixUnitaireTtc;

    @Lob
    @Column(name = "photo")
    private byte[] photo;



    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<LigneFacture> lignefacture;



   @OneToMany(mappedBy = "article")
    private List<LigneStockUp> ligneStockUpss;

    @OneToMany(mappedBy = "article")
    private List<MvStock> mvtstks;

}