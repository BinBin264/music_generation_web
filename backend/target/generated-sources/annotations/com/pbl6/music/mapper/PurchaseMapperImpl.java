package com.pbl6.music.mapper;

import com.pbl6.music.dto.response.PurchaseResponseDTO;
import com.pbl6.music.entity.MusicEntity;
import com.pbl6.music.entity.PurchaseEntity;
import com.pbl6.music.entity.UserEntity;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-05T17:27:28+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PurchaseMapperImpl implements PurchaseMapper {

    @Override
    public PurchaseResponseDTO toResponseDTO(PurchaseEntity purchaseEntity) {
        if ( purchaseEntity == null ) {
            return null;
        }

        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();

        purchaseResponseDTO.setPurchaseIdResponse( purchaseEntity.getPurchaseId() );
        purchaseResponseDTO.setBuyerIdResponse( purchaseEntityBuyerUserId( purchaseEntity ) );
        purchaseResponseDTO.setBuyerNameResponse( purchaseEntityBuyerUserName( purchaseEntity ) );
        purchaseResponseDTO.setMusicIdResponse( purchaseEntityMusicEntityMusicId( purchaseEntity ) );
        purchaseResponseDTO.setMusicTitleResponse( purchaseEntityMusicEntityTitle( purchaseEntity ) );
        purchaseResponseDTO.setPurchaseDateResponse( purchaseEntity.getPurchaseDate() );
        purchaseResponseDTO.setAmountResponse( purchaseEntity.getAmount() );

        return purchaseResponseDTO;
    }

    @Override
    public PurchaseEntity toEntity(PurchaseResponseDTO purchaseResponseDTO) {
        if ( purchaseResponseDTO == null ) {
            return null;
        }

        PurchaseEntity purchaseEntity = new PurchaseEntity();

        return purchaseEntity;
    }

    private UUID purchaseEntityBuyerUserId(PurchaseEntity purchaseEntity) {
        if ( purchaseEntity == null ) {
            return null;
        }
        UserEntity buyer = purchaseEntity.getBuyer();
        if ( buyer == null ) {
            return null;
        }
        UUID userId = buyer.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String purchaseEntityBuyerUserName(PurchaseEntity purchaseEntity) {
        if ( purchaseEntity == null ) {
            return null;
        }
        UserEntity buyer = purchaseEntity.getBuyer();
        if ( buyer == null ) {
            return null;
        }
        String userName = buyer.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }

    private Long purchaseEntityMusicEntityMusicId(PurchaseEntity purchaseEntity) {
        if ( purchaseEntity == null ) {
            return null;
        }
        MusicEntity musicEntity = purchaseEntity.getMusicEntity();
        if ( musicEntity == null ) {
            return null;
        }
        Long musicId = musicEntity.getMusicId();
        if ( musicId == null ) {
            return null;
        }
        return musicId;
    }

    private String purchaseEntityMusicEntityTitle(PurchaseEntity purchaseEntity) {
        if ( purchaseEntity == null ) {
            return null;
        }
        MusicEntity musicEntity = purchaseEntity.getMusicEntity();
        if ( musicEntity == null ) {
            return null;
        }
        String title = musicEntity.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }
}
