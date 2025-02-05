package com.pbl6.music.mapper;

import com.pbl6.music.dto.response.MusicResponseDTO;
import com.pbl6.music.dto.response.PurchaseResponseDTO;
import com.pbl6.music.entity.MusicEntity;
import com.pbl6.music.entity.PurchaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    // Chuyển từ PurchaseEntity sang PurchaseResponseDTO
    @Mapping(source = "purchaseId", target = "purchaseIdResponse")
    @Mapping(source = "buyer.userId", target = "buyerIdResponse")
    @Mapping(source = "buyer.userName", target = "buyerNameResponse")
    @Mapping(source = "musicEntity.musicId", target = "musicIdResponse")
    @Mapping(source = "musicEntity.title", target = "musicTitleResponse")
    @Mapping(source = "purchaseDate", target = "purchaseDateResponse")
    @Mapping(source = "amount", target = "amountResponse")
    PurchaseResponseDTO toResponseDTO(PurchaseEntity purchaseEntity);

    PurchaseEntity toEntity(PurchaseResponseDTO purchaseResponseDTO);
}
