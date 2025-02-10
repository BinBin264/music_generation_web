package com.pbl6.music.repository;

import com.pbl6.music.dto.response.MusicResponseDTO;
import com.pbl6.music.entity.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity, Long> {
    @Query("""
        select new com.pbl6.music.dto.response.MusicResponseDTO(  
            m.musicId, 
            m.title, 
            m.fullUrl, 
            m.price, 
            m.isPurchased, 
            m.composer.userId,
            m.composer.userName, 
            m.category.categoryId )
        from MusicEntity m where m.isPurchased = false
        """)
    Page<MusicResponseDTO> findAllAvailableMusic(Pageable pageable);

    @Query("""
    select new com.pbl6.music.dto.response.MusicResponseDTO(  
        m.musicId, 
        m.title, 
        m.fullUrl, 
        m.price, 
        m.isPurchased,  
        m.composer.userId,
        m.composer.userName,
        m.category.categoryId)
    from MusicEntity m 
    where m.composer.userName = :composerUserName
    """)
    Page<MusicResponseDTO> findMusicByComposer(String composerUserName, Pageable pageable);


}
