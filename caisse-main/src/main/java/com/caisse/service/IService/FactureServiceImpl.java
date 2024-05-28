package com.caisse.service.IService;

import com.caisse.dto.ArticleDto;
import com.caisse.dto.FactureDto;
import com.caisse.dto.LigneFactureDto;
import com.caisse.dto.MvStockDto;
import com.caisse.entity.*;
import com.caisse.exception.EntityNotFoundException;
import com.caisse.exception.ErrorCodes;
import com.caisse.exception.InvalidEntityException;
import com.caisse.exception.InvalidOperationException;
import com.caisse.repository.ArticleRepository;
import com.caisse.repository.FactureRepository;
import com.caisse.repository.LigneFactureRepository;
import com.caisse.service.FactureService;
import com.caisse.service.MvStockService;
import com.caisse.validator.FactureValidator;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class FactureServiceImpl implements FactureService {



private ArticleRepository articleRepository;
private FactureRepository factureRepository;
private LigneFactureRepository ligneFactureRepository;
private MvStockService mvStockService;
@Autowired
public FactureServiceImpl(ArticleRepository articleRepository, FactureRepository factureRepository, LigneFactureRepository ligneFactureRepository, MvStockService mvStockService) {
        this.articleRepository = articleRepository;
        this.factureRepository = factureRepository;
        this.ligneFactureRepository = ligneFactureRepository;
        this.mvStockService = mvStockService;
    }


public FactureDto save(FactureDto dto) {
    List<String> errors = FactureValidator.validate(dto);
    if (!errors.isEmpty()) {
        log.error("Facture n'est pas valid");
        throw new InvalidEntityException("Facture n'est pas valid", ErrorCodes.FACTURE_NOT_VALID, errors);

    }
    List<String> articleErrors = new ArrayList<>();

    dto.getLigneFacture().forEach(ligneFactureDto -> {
        Optional<Article> article = articleRepository.findById(ligneFactureDto.getArticle().getId());
        if (article.isEmpty()) {
            articleErrors.add("Aucun article avec l'ID " + ligneFactureDto.getArticle().getId() + " n'a ete trouve dans la BDD");
        }
    });
    if (!articleErrors.isEmpty()) {
        log.error("One or more articles were not found in the DB, {}", errors);
        throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.FACTURE_NOT_VALID, errors);
    }

    Facture savedFacture = factureRepository.save(FactureDto.toEntity(dto));
    dto.getLigneFacture().forEach(ligneFactureDto -> {
        LigneFacture ligneFacture = ligneFactureDto.toEntity(ligneFactureDto);
        ligneFacture.setFacture(savedFacture);
        ligneFactureRepository.save(ligneFacture);
       updateMvStock(ligneFacture);
    });
    return FactureDto.fromEntity(savedFacture);
}


    @Override
    public FactureDto findById(Integer id) {
        if (id == null) {
            log.error("Facture ID is NULL");
            return null;
        }
        return factureRepository.findById(id)
                .map(FactureDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.FACTURE_NOT_FOUND));
    }

    @Override
    public FactureDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Facture CODE is NULL");
            return null;
        }

        return factureRepository.findFactureByCode(code)
                .map(FactureDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente client n'a ete trouve avec le CODE " + code, ErrorCodes.FACTURE_NOT_VALID
                ));
    }

    @Override
    public List<FactureDto> findAll() {
        return factureRepository.findAll().stream()
                .map(FactureDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("facture ID is NULL");
            return;}
        List<LigneFacture> ligneFactures= ligneFactureRepository.findAllByFactureId(id);
        if (!ligneFactures.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer la facture",
            ErrorCodes.FACTURE_ALREADY_IN_USE);
        }
        factureRepository.deleteById(id);
        }



        public void updateMvStock(LigneFacture lig){
            MvStockDto mvStockDto=MvStockDto.builder()
                    .article(ArticleDto.fromEntity(lig.getArticle()))
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvtStk.SORTIE)
                    .sourceMvt(SourceMvtStk.FACTURE)
                    .quantite(lig.getQuantite())
                    .build();

        }
/*

        public static void generateInvoicePDF(String pdfFilename, List<LigneFacture> ligneFactures, Facture facture, List<Article> articles) throws Exception {
            OutputStream file = new FileOutputStream(new File(pdfFilename));
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();

            // Add invoice header (logo or company information)
            // You can add an image or custom text here

            addInvoiceDetails(document, facture);
            addBillToInfo(document);
            addInvoiceTable(document, ligneFactures, articles);

            document.close();
            file.close();
            System.out.println("Pdf created successfully..");
        }



    private static void addInvoiceTable(Document document, List<LigneFacture> ligneFactures, List<Article> articles) throws Exception {
        PdfPTable invoiceTable = new PdfPTable(6); // Adjust column count as needed
        invoiceTable.setWidthPercentage(100);
        invoiceTable.setWidths(new float[] { 1, 2.5f, 3.5f, 1.5f, 1, 1.5f });

        invoiceTable.addCell(addBillToInfo("Index"));
        invoiceTable.addCell(addBillToInfo("Item Code"));
        invoiceTable.addCell(addBillToInfo("Description"));
        invoiceTable.addCell(addBillToInfo("Unit Price"));
        invoiceTable.addCell(addBillToInfo("Qty"));
        invoiceTable.addCell(addBillToInfo("Amount"));

        for (int i = 0; i < ligneFactures.size(); i++) {
            LigneFacture ligneFacture = ligneFactures.get(i);
            Article article = findArticleById(articles, ligneFacture.getidArticle());

            invoiceTable.addCell(addBillToInfo(String.valueOf(i + 1)));
            invoiceTable.addCell(addBillToInfo(article.getCodeArticle()));
            invoiceTable.addCell(addBillToInfo(article.getDesignation()));
            invoiceTable.addCell(addBillToInfo(String.valueOf(article.getPrixUnitaireTtc())));
            invoiceTable.addCell(addBillToInfo(String.valueOf(ligneFacture.getQuantite())));
            invoiceTable.addCell(addBillToInfo(String.valueOf(ligneFacture.getPrixUnitaire() * ligneFacture.getQuantite())));  // Calculate total amount per line
        }
        document.add(invoiceTable);
    }

    private static void addInvoiceDetails(Document document, Facture facture) throws Exception {
            // ... (same logic as before to add invoice code, date, etc. from Facture)

        }

        private static void addBillToInfo(Document document) throws Exception {
            // ... (same logic as before to add customer information)
        }

   //     private static void addInvoiceTable(Document document, List<LigneFacture> ligneFactures, List<Article> articles) throws Exception {
            // ... (same logic as before to create and populate the invoice table)
   //     }

        // Helper method to find an Article object by its ID (replace with your implementation)
        private static Article findArticleById(List<Article> articles, int articleId) {
            for (Article article : articles) {
                if (article.getId() == articleId) {
                    return article;
                }
            }
            return null; // Handle case where article is not found (throw exception or log error)
        } */

}




