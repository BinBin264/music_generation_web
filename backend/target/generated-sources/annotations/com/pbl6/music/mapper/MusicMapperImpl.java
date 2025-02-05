package com.pbl6.music.mapper;

import com.pbl6.music.dto.request.MusicRequestDTO;
import com.pbl6.music.dto.response.MusicResponseDTO;
import com.pbl6.music.entity.CategoryEntity;
import com.pbl6.music.entity.MusicEntity;
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
public class MusicMapperImpl implements MusicMapper {

    @Override
    public MusicResponseDTO toResponseDTO(MusicEntity musicEntity) {
        if ( musicEntity == null ) {
            return null;
        }

        MusicResponseDTO musicResponseDTO = new MusicResponseDTO();

        musicResponseDTO.setMusicIdResponse( musicEntity.getMusicId() );
        musicResponseDTO.setTitleResponse( musicEntity.getTitle() );
        musicResponseDTO.setComposerIdResponse( musicEntityComposerUserId( musicEntity ) );
        musicResponseDTO.setFullUrlResponse( musicEntity.getFullUrl() );
        musicResponseDTO.setPriceResponse( musicEntity.getPrice() );
        musicResponseDTO.setPurchasedResponse( musicEntity.isPurchased() );
        musicResponseDTO.setCategoryIdResponse( musicEntityCategoryCategoryId( musicEntity ) );

        return musicResponseDTO;
    }

    @Override
    public MusicEntity toEntity(MusicRequestDTO musicRequestDTO) {
        if ( musicRequestDTO == null ) {
            return null;
        }

        MusicEntity musicEntity = new MusicEntity();

        musicEntity.setComposer( musicRequestDTOToUserEntity( musicRequestDTO ) );
        musicEntity.setCategory( musicRequestDTOToCategoryEntity( musicRequestDTO ) );
        musicEntity.setMusicId( musicRequestDTO.getMusicId() );
        musicEntity.setTitle( musicRequestDTO.getTitle() );
        musicEntity.setFullUrl( musicRequestDTO.getFullUrl() );
        musicEntity.setPrice( musicRequestDTO.getPrice() );
        musicEntity.setPurchased( musicRequestDTO.isPurchased() );

        return musicEntity;
    }

    private UUID musicEntityComposerUserId(MusicEntity musicEntity) {
        if ( musicEntity == null ) {
            return null;
        }
        UserEntity composer = musicEntity.getComposer();
        if ( composer == null ) {
            return null;
        }
        UUID userId = composer.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Long musicEntityCategoryCategoryId(MusicEntity musicEntity) {
        if ( musicEntity == null ) {
            return null;
        }
        CategoryEntity category = musicEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        Long categoryId = category.getCategoryId();
        if ( categoryId == null ) {
            return null;
        }
        return categoryId;
    }

    protected UserEntity musicRequestDTOToUserEntity(MusicRequestDTO musicRequestDTO) {
        if ( musicRequestDTO == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userId( musicRequestDTO.getComposer_id() );

        return userEntity.build();
    }

    protected CategoryEntity musicRequestDTOToCategoryEntity(MusicRequestDTO musicRequestDTO) {
        if ( musicRequestDTO == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryId( musicRequestDTO.getCategory_id() );

        return categoryEntity;
    }
}
