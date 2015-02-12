package com.tsukaby.bean_validation_scala.constraintvalidators

import javax.validation.constraints.Digits
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._

/**
 * Validates that the wrapped character sequence (e.g. Option[String]) being validated consists of digits,
 * and matches the pattern defined in the constraint.
 */
class DigitsValidatorForOption extends ConstraintValidator[Digits, Option[_]] {
  private var constraintAnnotation: Digits = null

  override def initialize(constraintAnnotation: Digits): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new DigitsValidatorForCharSequence
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: Number) =>
        val v = new DigitsValidatorForNumber
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(_) | None =>
        throw new IllegalStateException("oops.")
    }
  }
}
