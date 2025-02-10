package com.pbl6.music.mapper;

import com.pbl6.music.dto.response.CategoryResponseDTO;
import com.pbl6.music.entity.CategoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-09T19:55:01+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponseDTO toCategoryResponseDTO(CategoryEntity category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setCategoryIdResponse( category.getCategoryId() );
        categoryResponseDTO.setCategoryNameResponse( category.getCategoryName() );

        return categoryResponseDTO;
    }

    @Override
    public CategoryEntity toCategoryEntity(CategoryResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        return categoryEntity;
    }
}
