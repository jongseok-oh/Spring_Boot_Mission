package dev.jongking.challenge.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shop_categories")
@IdClass(ShopCategoryId.class)
public class ShopCategoryEntity {

    @Id
    @ManyToOne(
            targetEntity = CategoryEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name ="category_id")
    private CategoryEntity category;

    @Id
    @ManyToOne(
            targetEntity = ShopEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    public ShopCategoryEntity() {
    }

    public ShopCategoryEntity(CategoryEntity category, ShopEntity shop) {
        this.category = category;
        this.shop = shop;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }
}
