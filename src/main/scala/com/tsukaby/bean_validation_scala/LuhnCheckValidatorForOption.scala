package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.LuhnCheck
import org.hibernate.validator.internal.constraintvalidators._

/**
 * Luhn algorithm checksum validator for scala.
 *
 * http://en.wikipedia.org/wiki/Luhn_algorithm
 * http://en.wikipedia.org/wiki/Check_digit
 */
class LuhnCheckValidatorForOption extends ConstraintValidator[LuhnCheck, Option[_]] {
  private var constraintAnnotation: LuhnCheck = null

  override def initialize(constraintAnnotation: LuhnCheck): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new LuhnCheckValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
