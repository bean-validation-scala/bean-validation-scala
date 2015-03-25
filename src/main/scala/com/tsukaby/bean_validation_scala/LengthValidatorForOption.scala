package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.internal.constraintvalidators._

/**
 * Check that the wrapped character sequence length is between min and max.
 */
class LengthValidatorForOption extends ConstraintValidator[Length, Option[_]] {
  private var constraintAnnotation: Length = null

  override def initialize(constraintAnnotation: Length): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new LengthValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
