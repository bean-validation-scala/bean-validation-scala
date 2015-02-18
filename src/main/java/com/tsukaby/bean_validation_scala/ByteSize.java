package com.tsukaby.bean_validation_scala;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated element size must be between the specified boundaries (included).
 * Supported types are String and Option[String].
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface ByteSize {

    String message() default "{com.tsukaby.bean_validation_scala.ByteSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return size the element must be higher or equal to
     */
    int min() default 0;

    /**
     * @return size the element must be lower or equal to
     */
    int max() default Integer.MAX_VALUE;

    String charsetName() default "UTF-8";

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        ByteSize[] value();
    }
}
