package bg.softuni.mobilelele_web_application.repositories;


import bg.softuni.mobilelele_web_application.domain.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
}
