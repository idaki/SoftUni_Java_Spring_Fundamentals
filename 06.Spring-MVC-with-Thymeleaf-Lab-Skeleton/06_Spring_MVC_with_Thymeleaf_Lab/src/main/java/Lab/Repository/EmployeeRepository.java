package Lab.Repository;

import Lab.model.Company;
import Lab.model.Employee;
import org.springframework.data.repository.Repository;
@org.springframework.stereotype.Repository
public interface EmployeeRepository extends Repository<Employee, String> {
}
