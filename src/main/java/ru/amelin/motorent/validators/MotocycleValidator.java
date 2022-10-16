package ru.amelin.motorent.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.amelin.motorent.dao.MotocycleService;
import ru.amelin.motorent.models.Motocycle;

@Component
public class MotocycleValidator implements Validator {

    private final MotocycleService motocycleService;

    @Autowired
    public MotocycleValidator(MotocycleService motocycleService) {
        this.motocycleService = motocycleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Motocycle.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Motocycle motocycle = (Motocycle) target;
        if (motocycleService.exists(motocycle.getVin())) {
            errors.rejectValue("vin", "", "Motocycle with same vin is already exists");
        }
    }
}
