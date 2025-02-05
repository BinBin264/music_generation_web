package com.pbl6.music.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "purchases",
    uniqueConstraints = @UniqueConstraint(columnNames = "music_id") // Đảm bảo một bản nhạc chỉ có một giao dịch
)
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private UserEntity buyer;

    @OneToOne
    @JoinColumn(name = "music_id", nullable = false)
    private MusicEntity musicEntity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}
