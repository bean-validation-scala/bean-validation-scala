package com.tsukaby.bean_validation_scala

import javax.validation.constraints.AssertTrue
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator

/**
 * Validates that the wrapped value passed is true
 */
class AssertTrueValidatorForOption extends ConstraintValidator[AssertTrue, Option[Boolean]] {
  private var constraintAnnotation: AssertTrue = null

  override def initialize(constraintAnnotation: AssertTrue): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[Boolean], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x) =>
        val v = new AssertTrueValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
    }
  }
}
