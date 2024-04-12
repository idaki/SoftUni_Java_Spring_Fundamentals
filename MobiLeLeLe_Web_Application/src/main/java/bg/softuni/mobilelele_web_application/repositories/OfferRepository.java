package bg.softuni.mobilelele_web_application.repositories;


import bg.softuni.mobilelele_web_application.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
}
