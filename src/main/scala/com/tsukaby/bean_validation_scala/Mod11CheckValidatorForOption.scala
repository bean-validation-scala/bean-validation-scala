package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.Mod11Check
import org.hibernate.validator.internal.constraintvalidators._

/**
 * Mod11 Check Digit validator for scala.
 *
 * http://en.wikipedia.org/wiki/Check_digit
 */
class Mod11CheckValidatorForOption extends ConstraintValidator[Mod11Check, Option[_]] {
  private var constraintAnnotation: Mod11Check = null

  override def initialize(constraintAnnotation: Mod11Check): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new Mod11CheckValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
