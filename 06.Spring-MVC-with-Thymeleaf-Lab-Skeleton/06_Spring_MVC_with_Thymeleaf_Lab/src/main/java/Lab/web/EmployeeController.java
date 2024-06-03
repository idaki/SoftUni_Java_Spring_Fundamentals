package Lab.web;

import Lab.Service.EmployeeService;
import Lab.model.Employee;
import jdk.jfr.Category;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
//        model.addAttribute("employee", new Employee());
        return "employee-add";
    }
}
