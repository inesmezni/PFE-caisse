package com.caisse.service.IService;

import com.caisse.dto.ArticleDto;
import com.caisse.exception.EntityNotFoundException;
import com.caisse.exception.ErrorCodes;
import com.caisse.exception.InvalidEntityException;
import com.caisse.repository.ArticleRepository;
import com.caisse.repository.LigneFactureRepository;
import com.caisse.service.ArticleService;
import com.caisse.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.caisse.dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

import com.caisse.entity.Article;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private LigneFactureRepository factureRepository;

    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository,
            LigneFactureRepository factureRepository) {
        this.articleRepository = articleRepository;
        this.factureRepository = factureRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        return ArticleDto.fromEntity(
                articleRepository.save(
                        ArticleDto.toEntity(dto)
                )
        );
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return null;
        }

        return articleRepository.findById(id).map(ArticleDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec l'ID = " + id + " n' a été trouvé dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)) {
            log.error("Article CODE is null");
            return null;
        }

        return articleRepository.findArticleByCodeArticle(codeArticle)
                .map(ArticleDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Aucun article avec le CODE = " + codeArticle + " n' a été trouvé dans la BDD",
                                ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto updateArticle(Integer id, ArticleDto dto) {
        if (id == null || dto == null) {
            log.error("Article ID or DTO is null");
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, List.of("ID or DTO is null"));
        }

        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun article avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND));

        existingArticle.setCodeArticle(dto.getCodeArticle());
        existingArticle.setDesignation(dto.getDesignation());
        existingArticle.setPrixUnitaireHt(dto.getPrixUnitaireHt());
        existingArticle.setTauxTva(dto.getTauxTva());
        existingArticle.setPrixUnitaireTtc(dto.getPrixUnitaireTtc());
        existingArticle.setCategory(CategoryDto.toEntity(dto.getCategory()));

        return ArticleDto.fromEntity(articleRepository.save(existingArticle));
    }

    @Override
    public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
        return articleRepository.findAllByCategoryId(idCategory).stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
