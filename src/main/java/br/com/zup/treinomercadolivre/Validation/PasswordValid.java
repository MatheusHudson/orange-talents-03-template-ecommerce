package br.com.zup.treinomercadolivre.Validation;


import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public class PasswordValid implements ConstraintValidator<Password, String> {

	@Override
	public void initialize(Password arg0) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		PasswordValidator validator = new PasswordValidator(Arrays.asList(

				new LengthRule(8, 16),

				// at least one upper-case character
				new CharacterRule(EnglishCharacterData.UpperCase, 1),

				// at least one lower-case character
				new CharacterRule(EnglishCharacterData.LowerCase, 1),

				// at least one digit character

				new CharacterRule(EnglishCharacterData.Digit, 1),

				// at least one symbol (special character)

				new CharacterRule(EnglishCharacterData.Special, 1),

				// no whitespace

				new WhitespaceRule(),

				// rejects passwords that contain a sequence of >= 5 characters alphabetical
				// (e.g. abcdef)

				new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),

				// rejects passwords that contain a sequence of >= 5 characters numerical (e.g.
				// 12345)

				new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)

		));
		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		 context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate(
	         String.join("",validator.getMessages(result)))
	          .addConstraintViolation();
	        return false;
	}

}