package dev.luisoliveira.esquadrias.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import dev.luisoliveira.esquadrias.dtos.AddressDto;
import dev.luisoliveira.esquadrias.enums.AddressType;
import dev.luisoliveira.esquadrias.models.AddressModel;
import dev.luisoliveira.esquadrias.models.CompanyModel;
import dev.luisoliveira.esquadrias.models.EmployeeModel;
import dev.luisoliveira.esquadrias.models.UserModel;
import dev.luisoliveira.esquadrias.services.AddressService;
import dev.luisoliveira.esquadrias.services.CompanyService;
import dev.luisoliveira.esquadrias.services.EmployeeService;
import dev.luisoliveira.esquadrias.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/users/{userId}/createUserAddress")
    public ResponseEntity<Object> registerUserAddress(@PathVariable(value = "userId") UUID userId,
                                                  @RequestBody @Validated(AddressDto.AddressView.RegistrationPost.class)
                                                  @JsonView(AddressDto.AddressView.RegistrationPost.class) AddressDto addressDto) {

        log.debug("POST registerAddress AddressDto received: ------> {}", addressDto.toString());
        Optional<UserModel> userModelOptional = userService.findById(userId);

        try {
            if (!userModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            if (addressDto.getAddressId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The addressId field must be null");
            }
            var addressModel = new AddressModel();
            BeanUtils.copyProperties(addressDto, addressModel);
            addressModel.setType(AddressType.RESIDENTIAL);
            addressModel.setUser(userModelOptional.get());
            addressService.save(addressModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressModel);

        } catch (Exception e) {
            log.error("Specific error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/employee/{employeeId}/registerEmployeeAddress")
    public ResponseEntity<Object> registerEmployeeAddress(@PathVariable(value = "employeeId") UUID employeeId,
                                                         @RequestBody @Validated(AddressDto.AddressView.RegistrationPost.class)
                                                         @JsonView(AddressDto.AddressView.RegistrationPost.class) AddressDto addressDto) {

        log.debug("POST registerEmployeeAddress AddressDto received: ------> {}", addressDto.toString());
        Optional<EmployeeModel> employeeModelOptional = employeeService.findById(employeeId);

        try {
            if (!employeeModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
            if (addressDto.getAddressId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The addressId field must be null");
            }
            var addressModel = new AddressModel();
            BeanUtils.copyProperties(addressDto, addressModel);
            addressModel.setType(AddressType.EMPLOYEE);
            addressModel.setCompany(employeeModelOptional.get().getCompany());
            addressService.save(addressModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressModel);

        } catch (Exception e) {
            log.error("Specific error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/company/{companyId}/createCompanyAddress")
    public ResponseEntity<Object> registerCompanyAddress(@PathVariable(value = "companyId") UUID companyId,
                                                      @RequestBody @Validated(AddressDto.AddressView.RegistrationPost.class)
                                                      @JsonView(AddressDto.AddressView.RegistrationPost.class) AddressDto addressDto) {

        log.debug("POST registerAddress AddressDto received: ------> {}", addressDto.toString());
        Optional<CompanyModel> companyModelOptional = companyService.findById(companyId);

        try {
            if (!companyModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
            }
            if (addressDto.getAddressId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The addressId field must be null");
            }
            var addressModel = new AddressModel();
            BeanUtils.copyProperties(addressDto, addressModel);
            addressModel.setType(AddressType.COMPANY);
            addressModel.setCompany(companyModelOptional.get());
            addressService.save(addressModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressModel);

        } catch (Exception e) {
            log.error("Specific error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }
//    @PreAuthorize("hasAnyRole('USER')")
//    @GetMapping("/{addressId}")
//    public ResponseEntity<Object> getAddress(@PathVariable UUID addressId) {
//        try {
//            Optional<AddressModel> addressModelOptional = addressService.findById(addressId);
//            if (addressModelOptional == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
//            }
//            return ResponseEntity.ok(addressModelOptional);
//        } catch (Exception e) {
//            log.error("Specific error occurred", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
//        }
//    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{userId}/updateAddress/{addressId}")
    public ResponseEntity<Object> updateAddress(@PathVariable UUID addressId,
                                                @RequestBody
                                                @Validated(AddressDto.AddressView.RegistrationPost.class)
                                                @JsonView(AddressDto.AddressView.RegistrationPost.class) AddressDto addressDto) {
        try {
            Optional<AddressModel> addressModelOptional = addressService.findById(addressId);
            if (addressModelOptional == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
            }
            var addressModel = new AddressModel();
            BeanUtils.copyProperties(addressDto, addressModel);
            addressService.save(addressModel);
            return ResponseEntity.ok(addressModel);
        } catch (Exception e) {
            log.error("Specific error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{addressId}/deactivate-address")
    public ResponseEntity<Object> deactivateAddress(@PathVariable UUID addressId) {
        try {
            Optional<AddressModel> addressModelOptional = addressService.findById(addressId);
            if (addressModelOptional == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
            }

            var addressModel = new AddressModel();
            addressModel.setActive(false);
            addressModel.setDeleted(true);
            addressService.save(addressModel);
            return ResponseEntity.ok("Address deactivated successfully");
        } catch (Exception e) {
            log.error("Specific error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }
}