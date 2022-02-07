package com.varchenko.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product {

    @Id
    @Column(name = "uniq_id")
    private String uniqId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name_title")
    private String nameTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "list_price")
    private String listPrice;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "category")
    private String category;

    @Column(name = "category_tree")
    private String categoryTree;

    @Column(name = "average_category_rating")
    private String averageProductRating;

    @Column(name = "product_url")
    private String productUrl;

    @Column(name = "product_image_urls")
    private String productImageUrls;

    @Column(name = "brand")
    private String brand;

    @Column(name = "total_number_reviews")
    private String totalNumbersReviews;

    @Column(name = "reviews")
    private String reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return uniqId != null && Objects.equals(uniqId, product.uniqId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
