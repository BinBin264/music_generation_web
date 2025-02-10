package com.pbl6.music.mapper;

import com.pbl6.music.dto.request.*;
import com.pbl6.music.dto.response.*;
import com.pbl6.music.entity.MusicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MusicMapper {

     // Chuyển từ MusicEntity sang MusicResponseDTO
    @Mapping(source = "musicId", target = "musicIdResponse")
    @Mapping(source = "title", target = "titleResponse")
    @Mapping(source = "composer.userId", target = "composerIdResponse")
    @Mapping(source = "composer.userName", target = "composerNameResponse")// Tên người soạn nhạc
    @Mapping(source = "fullUrl", target = "fullUrlResponse")
    @Mapping(source = "price", target = "priceResponse")
    @Mapping(source = "purchased", target = "purchasedResponse")
    @Mapping(source = "category.categoryId", target = "categoryIdResponse")
    MusicResponseDTO toResponseDTO(MusicEntity musicEntity);

    @Mapping(source = "musicId", target = "musicId")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "composer_id", target = "composer.userId")// Tên người soạn nhạc
    @Mapping(source = "fullUrl", target = "fullUrl")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "purchased", target = "purchased")
    @Mapping(source = "category_id", target = "category.categoryId")
    MusicEntity toEntity(MusicRequestDTO musicRequestDTO);
}
