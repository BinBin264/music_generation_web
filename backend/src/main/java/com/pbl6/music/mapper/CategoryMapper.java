package com.pbl6.music.mapper;

import com.pbl6.music.dto.response.CategoryResponseDTO;
import com.pbl6.music.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "categoryId", target = "categoryIdResponse")
    @Mapping(source = "categoryName", target = "categoryNameResponse")
    CategoryResponseDTO toCategoryResponseDTO(CategoryEntity category);

    CategoryEntity toCategoryEntity(CategoryResponseDTO dto);
}
