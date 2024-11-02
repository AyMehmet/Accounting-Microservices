package com.memo.controler;


import com.memo.dto.CompanyDto;
import com.memo.dto.ResponseWrapper;
import com.memo.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/companies")
@PreAuthorize("hasAnyAuthority('Root User')")
public class CompanyController {

    private final CompanyService companyService;
   // private final AddressService addressService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
      //  this.addressService = addressService;
    }

    @GetMapping("/list")

    public ResponseEntity<ResponseWrapper> list(Model model) {
        List<CompanyDto> companyDtoList= companyService.getAllCompanies();
        return ResponseEntity.status(HttpStatus.OK)
                .header("version","1")
                .body(new ResponseWrapper("All Companies are retrived successfully",companyDtoList,HttpStatus.OK));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('Root User')")
    public ResponseEntity<ResponseWrapper> create(@RequestBody @Valid CompanyDto companyDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper(bindingResult.getFieldError().toString()));
        } else if (companyService.isTitleExist(companyDto.getTitle())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("This title already exists. Please try with different title."));
        } else {
                companyService.create(companyDto);
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .header("Version", "1")
                        .body(new ResponseWrapper("Company is created succesfully"));
            }
    }


    @PutMapping("/update/{companyId}")
    @PreAuthorize("hasAnyAuthority('Root User')")
    public ResponseEntity<ResponseWrapper> update( @PathVariable Long companyId, @RequestBody @Valid CompanyDto companyDto, BindingResult bindingResult) throws CloneNotSupportedException {
        boolean isThisCompanyTitle = companyDto.getTitle().equals(companyService.findCompanyById(companyId).getTitle());
        if (bindingResult.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper(bindingResult.getFieldError().toString()));

        } else if (companyService.isTitleExist(companyDto.getTitle()) && !isThisCompanyTitle) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("This title already exists. Please try with different title."));
        } else {
            companyService.update(companyId, companyDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .header("Version", "1")
                    .body(new ResponseWrapper("Company is updated succesfully"));
        }
    }

    @GetMapping("/activate/{companyId}")
    public ResponseEntity<ResponseWrapper> activate(@PathVariable("companyId") Long companyId) {
        boolean companyIdExist = companyService.isCompanyExist(companyId);
        if (companyIdExist) {
            companyService.activate(companyId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .header("Version", "1")
                    .body(new ResponseWrapper("Company is activated succesfully"));
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("There is no company for this company Id"));
    }
    @GetMapping("/deactivate/{companyId}")
    public ResponseEntity<ResponseWrapper> deactivate(@PathVariable("companyId") Long companyId) {
        boolean companyIdExist = companyService.isCompanyExist(companyId);
        if (companyIdExist) {
        companyService.deactivate(companyId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Version", "1")
                .body(new ResponseWrapper("Company is de-activated succesfully"));
    } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("There is no company for this company Id"));
    }
}
