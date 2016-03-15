package com.tsukaby.bean_validation_scala

import javax.validation.constraints.AssertFalse
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators.bv.AssertFalseValidator

/**
 * Validates that the wrapped value passed is false
 */
class AssertFalseValidatorForOption extends ConstraintValidator[AssertFalse, Option[Boolean]] {
  private var constraintAnnotation: AssertFalse = null

  override def initialize(constraintAnnotation: AssertFalse): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[Boolean], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x) =>
        val v = new AssertFalseValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
    }
  }
}
