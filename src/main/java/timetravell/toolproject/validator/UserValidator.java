package timetravell.toolproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import timetravell.toolproject.domain.User;

import javax.validation.ConstraintViolation;
import org.springframework.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;

        if(user.getPassword().length() <6){
            errors.rejectValue("password","Length", "Password must be at least 6 characters");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Match", "Passwords must match");

        }

        //confirmPassword



    }

}
