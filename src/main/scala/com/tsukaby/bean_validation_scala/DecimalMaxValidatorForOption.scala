package com.tsukaby.bean_validation_scala

import javax.validation.constraints.DecimalMax
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._

/**
 * Check that the wrapped character sequence (e.g. Option[String]) and the wrapped number being validated represents
 * a number, and has a value less than or equal to the maximum value specified.
 */
class DecimalMaxValidatorForOption extends ConstraintValidator[DecimalMax, Option[_]] {
  private var constraintAnnotation: DecimalMax = null

  override def initialize(constraintAnnotation: DecimalMax): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new DecimalMaxValidatorForCharSequence
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: Number) =>
        val v = new DecimalMaxValidatorForNumber
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(_) | None =>
        throw new IllegalStateException("oops.")
    }
  }
}
