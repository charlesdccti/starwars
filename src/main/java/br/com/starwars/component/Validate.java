package br.com.starwars.component;

import br.com.starwars.exception.FieldRequiredException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class Validate {

    public <T> void validator(T arg){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(arg);

        if(!violations.isEmpty()){
            StringBuilder sb = new StringBuilder();
            violations.forEach(v -> sb.append(v.getMessage()).append(", "));
            throw new FieldRequiredException(sb.toString());
        }
    }
}
