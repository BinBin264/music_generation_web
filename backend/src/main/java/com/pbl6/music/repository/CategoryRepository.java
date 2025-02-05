package com.pbl6.music.repository;

import com.pbl6.music.dto.response.CategoryResponseDTO;
import com.pbl6.music.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // Tìm Category theo ID và trả về DTO
    // Truy vấn tìm CategoryEntity theo ID
    @Query("""
        select c from CategoryEntity c
        where c.categoryId = :categoryId
        """)
    CategoryEntity findByCategoryId(Long categoryId);


    // Lấy tất cả Category có phân trang
    @Query("""
        select new com.pbl6.music.dto.response.CategoryResponseDTO(
            c.categoryId, 
            c.categoryName)
        from CategoryEntity c
        """)
    Page<CategoryResponseDTO> findAllCategory(Pageable pageable);

}
