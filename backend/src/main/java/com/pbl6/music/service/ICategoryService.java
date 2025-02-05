package com.pbl6.music.service;

import com.pbl6.music.dto.response.CategoryResponseDTO;
import com.pbl6.music.util.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ICategoryService {
    PageResponse<?> getAllCategory(int page, int pageSize);
    CategoryResponseDTO findByCategoryId(Long categoryId);
}
