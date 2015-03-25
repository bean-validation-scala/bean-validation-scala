package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.internal.constraintvalidators._

/**
 * Check that a wrapped character sequence's (e.g. Option[String]) trimmed length is not empty.
 */
class NotBlankValidatorForOption extends ConstraintValidator[NotBlank, Option[_]] {
  private var constraintAnnotation: NotBlank = null

  override def initialize(constraintAnnotation: NotBlank): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new NotBlankValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
