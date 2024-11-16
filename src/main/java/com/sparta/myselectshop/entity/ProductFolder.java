package com.sparta.myselectshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product_folder")
public class ProductFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ManyToOne(N:1)은 fetch = FetchType.EAGER -> 즉시 로딩
    // 폴더를 조회할 때 항상 회원의 정보를 가져올 필요가 없기 때문에
    // 효율성을 높이기 위해서 필요할 때마다 FetchType.LAZY 조회하도록 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "product")
    private List<ProductFolder> productFolderList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;

    public ProductFolder(Product product, Folder folder) {
        this.product = product;
        this.folder = folder;
    }
}