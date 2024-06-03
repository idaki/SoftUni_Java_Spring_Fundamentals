package Lab.Repository;

import Lab.model.Company;
import org.springframework.data.repository.Repository;
@org.springframework.stereotype.Repository
public interface CompanyRepository extends Repository<Company, String> {
}
