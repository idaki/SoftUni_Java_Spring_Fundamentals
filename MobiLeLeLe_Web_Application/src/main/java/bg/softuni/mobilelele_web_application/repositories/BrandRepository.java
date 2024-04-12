package bg.softuni.mobilelele_web_application.repositories;


import bg.softuni.mobilelele_web_application.domain.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
}

