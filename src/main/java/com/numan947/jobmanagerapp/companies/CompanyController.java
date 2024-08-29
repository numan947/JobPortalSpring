package com.numan947.jobmanagerapp.companies;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @GetMapping(path = {"/{id}", "/{id}/"})
    public ResponseEntity<CompanyModel> getCompanyById(@PathVariable Long id) {
        if (companyService.getCompanyById(id).isPresent()) {
            return ResponseEntity.ok(companyService.getCompanyById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<String> createCompany(@RequestBody CompanyModel company) {
        companyService.createCompany(company);
        return ResponseEntity.created(URI.create("/companies/" + company.getId())).body("Company created successfully");
    }

    @PutMapping(path = {"/{id}", "/{id}/"})
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody CompanyModel company) {
        if (companyService.updateCompany(id, company)) {
            return ResponseEntity.ok("Company updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = {"/{id}", "/{id}/"})
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        if (companyService.deleteCompany(id)) {
            return ResponseEntity.ok("Company deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
