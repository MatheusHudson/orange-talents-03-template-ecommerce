package br.com.zup.treinomercadolivre.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Constraint(validatedBy = {CompraStatusIsValidValitador.class})
@Target({ FIELD,ElementType.PARAMETER})
@Retention(RUNTIME)
public @interface CompraStatusIsValid {
	
	String message() default "Necessário informar um  id valido";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
