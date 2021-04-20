package gestionStages.validator;

import gestionStages.entity.Etudiant;
import gestionStages.entity.Utilisateur;
import gestionStages.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Etudiant.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Etudiant etudiant = (Etudiant) o;


        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (etudiant.getPassword().length() < 8 || etudiant.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!etudiant.getPasswordConfirm().equals(etudiant.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
