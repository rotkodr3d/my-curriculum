package rocks.turncodr.mycurriculum.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rocks.turncodr.mycurriculum.model.AreaOfStudies;
import rocks.turncodr.mycurriculum.services.AreaOfStudiesJpaRepository;

/**
*
* Custom validation class for area of studies.
*
*/
@Component("areaOfStudiesValidator")
public class AreaOfStudiesValidator implements Validator {

    @Autowired
    private AreaOfStudiesJpaRepository areaOfStudiesJpaRepository;

    private static final int MIN_COLOR_OFFSET = 50;

    @Override
    public boolean supports(Class<?> clazz) {
        return AreaOfStudies.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        List<AreaOfStudies> areaOfStudiesList = areaOfStudiesJpaRepository.findAll();
        AreaOfStudies areaOfStudies = (AreaOfStudies) target;

        for (AreaOfStudies aos : areaOfStudiesList) {
            String existingName = aos.getName().toLowerCase();
            String inputName = areaOfStudies.getName().toLowerCase().trim();
            if (existingName.equals(inputName)) {
                errors.rejectValue("name", "areaOfStudiesCreate.NameError");
            }

            int lower = aos.getColor() - MIN_COLOR_OFFSET;
            int upper = aos.getColor() + MIN_COLOR_OFFSET;
            if (!(areaOfStudies.getColor() < lower || areaOfStudies.getColor()  > upper)) {
                errors.rejectValue("color", "areaOfStudiesCreate.ColorError");
            }
        }
    }
}
