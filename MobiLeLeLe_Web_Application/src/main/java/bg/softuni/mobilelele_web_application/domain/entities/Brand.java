package bg.softuni.mobilelele_web_application.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity(name = "brands")
public class Brand extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private Date created;
    @Column
    private Date modified;

    public String getNam() {
        return name;
    }

    public void setNam(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
