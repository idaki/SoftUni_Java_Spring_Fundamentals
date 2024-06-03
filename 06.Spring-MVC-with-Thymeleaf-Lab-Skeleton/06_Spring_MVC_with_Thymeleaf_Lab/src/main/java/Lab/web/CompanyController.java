package Lab.web;

import Lab.Service.CompanyService;
import Lab.model.Company;
import Lab.model.DTO.importDTO.AddCompanyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/add")
    public String showAddCompanyForm(Model model) {

        if(!model.containsAttribute("addCompanyDTO")) {
            model.addAttribute("addCompanyDTO",AddCompanyDTO.empty());
        }
        model.addAttribute("company", new Company()); // Adding an empty Company object to the model
        return "company-add";
    }

    @PostMapping("/add")
    public String creatCompany (AddCompanyDTO addCompanyDTO){

        return "company-add";
    }


}
