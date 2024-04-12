package bg.softuni.mobilelele_web_application.domain.entities;

import bg.softuni.mobilelele_web_application.domain.enums.Category;
import jakarta.persistence.*;

@Entity(name = "models")
public class Model extends BaseEntity {
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
   @Column
   private String imageUrl;
    @Column
    private String startYear;
   @Column
   private String endYear;
   @Column
   private String created;
   @Column
   private String modified;

   @ManyToOne
   private Brand brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
