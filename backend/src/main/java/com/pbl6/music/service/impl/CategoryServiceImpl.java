package com.pbl6.music.service.impl;

import com.pbl6.music.dto.response.CategoryResponseDTO;
import com.pbl6.music.exception.ErrorCode;
import com.pbl6.music.dto.response.CategoryResponseDTO;
import com.pbl6.music.entity.CategoryEntity;
import com.pbl6.music.exception.ApiException;
import com.pbl6.music.mapper.CategoryMapper;
import com.pbl6.music.repository.CategoryRepository;
import com.pbl6.music.service.ICategoryService;
import com.pbl6.music.util.PageResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // Get all categories and map to DTOs
     public PageResponse<?> getAllCategory(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);
        Page<CategoryResponseDTO> categories = categoryRepository.findAllCategory(pageable);

        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(categories.getTotalPages())
                .items(categories.getContent())
                .build();
    }

    // Find a category by its ID
    public CategoryResponseDTO findByCategoryId(Long categoryId) {
        // Gọi query trong repository, tìm CategoryResponseDTO bằng ID
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
        CategoryResponseDTO categoryResponseDTO = categoryMapper.toCategoryResponseDTO(categoryEntity);
        // Nếu kết quả null thì ném ApiException
        if (categoryResponseDTO == null) {
            throw new ApiException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        // Trả về CategoryResponseDTO nếu tìm thấy
        return categoryResponseDTO;
    }



}

