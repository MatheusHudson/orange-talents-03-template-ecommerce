package br.com.zup.treinomercadolivre.Validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = {IdIsPresentValidator.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface IdIsPresent {
	
	String message() default "Necessário informar um  id valido";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	Class<?> domainClass();
}
