package com.caisse.service.IService;

import com.caisse.dto.MvStockDto;
import com.caisse.entity.TypeMvtStk;
import com.caisse.exception.ErrorCodes;
import com.caisse.exception.InvalidEntityException;
import com.caisse.repository.MvStockRepository;
import com.caisse.service.ArticleService;
import com.caisse.service.MvStockService;
import com.caisse.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MvStockServiceImpl implements MvStockService {
    private MvStockRepository repo ;
    private ArticleService articleService;

    @Autowired
    public MvStockServiceImpl(MvStockRepository repo, ArticleService articleService) {
        this.repo = repo;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if(idArticle==null){
            log.warn("id Article is null");
        return BigDecimal.valueOf(-1);

        }
        articleService.findById(idArticle);
        return repo.stockReelArticle(idArticle) ;
    }

    @Override
    public List mvStockArticle(Integer idArticle) {
        return repo.findAllByArticleId(idArticle).stream()
                .map(MvStockDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public MvStockDto entreeStock(MvStockDto dto) {
      List<String> errors= MvtStkValidator.validate(dto);
      if (!errors.isEmpty()){
          log.error("Article is not valid{}",dto);
          throw new InvalidEntityException("l'entree du stock n'est pas valid", ErrorCodes.MVT_STK_NOT_VALID,errors);
      }
dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));
      dto.setTypeMvt(TypeMvtStk.ENTREE);
      return MvStockDto.fromEntity(repo.save(MvStockDto.toEntity(dto)));
    }

    @Override
    public MvStockDto sortieStock(MvStockDto dto) {
        List<String> errors= MvtStkValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Article is not valid{}",dto);
            throw new InvalidEntityException("la sortie du stock n'est pas valid", ErrorCodes.MVT_STK_NOT_VALID,errors);
        }
        dto.setQuantite(BigDecimal.valueOf
                (Math.abs(dto.getQuantite().doubleValue())* -1
                ));

        dto.setTypeMvt(TypeMvtStk.SORTIE);
        return MvStockDto.fromEntity(repo.save(MvStockDto.toEntity(dto)));
    }

  /*  @Override
    public MvStockDto corecctionStockPOS(MvStockDto dto) {
        return null;
    }

    @Override
    public MvStockDto corecctionStockNEG(MvStockDto dto) {
        return null;
    } */
}
