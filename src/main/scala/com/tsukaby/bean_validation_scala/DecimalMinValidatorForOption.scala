package com.tsukaby.bean_validation_scala

import javax.validation.constraints.DecimalMin
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._

/**
 * Check that the wrapped character sequence (e.g. Option[String]) and the wrapped number being validated is less than
 * or equal to the maximum value specified.
 */
class DecimalMinValidatorForOption extends ConstraintValidator[DecimalMin, Option[_]] {
  private var constraintAnnotation: DecimalMin = null

  override def initialize(constraintAnnotation: DecimalMin): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new DecimalMinValidatorForCharSequence
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: Number) =>
        val v = new DecimalMinValidatorForNumber
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
