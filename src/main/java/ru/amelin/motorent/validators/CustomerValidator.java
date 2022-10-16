package ru.amelin.motorent.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.amelin.motorent.dao.CustomerService;
import ru.amelin.motorent.models.Customer;

@Component
public class CustomerValidator implements Validator {


    private final CustomerService customerService;

    @Autowired
    public CustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Customer.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        if (customerService.exists(customer.getDriverLicenseNumber())) {
            errors.rejectValue("driverLicenseNumber", "", "Customer with same driver license number is already exists");
        }
    }
}
