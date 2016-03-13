package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.Mod10Check
import org.hibernate.validator.internal.constraintvalidators.hv.Mod10CheckValidator

/**
 * Mod10 (Luhn algorithm implementation) Check validator for scala.
 *
 * http://en.wikipedia.org/wiki/Luhn_algorithm
 * http://en.wikipedia.org/wiki/Check_digit
 */
class Mod10CheckValidatorForOption extends ConstraintValidator[Mod10Check, Option[_]] {
  private var constraintAnnotation: Mod10Check = null

  override def initialize(constraintAnnotation: Mod10Check): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new Mod10CheckValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
