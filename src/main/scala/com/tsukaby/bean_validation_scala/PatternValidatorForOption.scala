package com.tsukaby.bean_validation_scala

import javax.validation.constraints.Pattern
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._

/**
 * Check the wrapped string.
 */
class PatternValidatorForOption extends ConstraintValidator[Pattern, Option[_]] {
  private var constraintAnnotation: Pattern = null

  override def initialize(constraintAnnotation: Pattern): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {
    value match {
      case Some(x:CharSequence) =>
        val v = new PatternValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(_) | None =>
        throw new IllegalStateException("oops.")
    }
  }
}
