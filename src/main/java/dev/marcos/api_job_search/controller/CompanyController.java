package dev.marcos.api_job_search.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    @DeleteMapping("/test")
    public String test() {
        return "Authenticate";
    }
}
