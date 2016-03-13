package com.tsukaby.bean_validation_scala

import javax.validation.constraints.Min
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators.bv.{MinValidatorForNumber, MinValidatorForCharSequence}

/**
 * Check that the wrapped character sequence (e.g. Option[String]) and the number being validated represents a number,
 * and has a value more than or equal to the minimum value specified.
 */
class MinValidatorForOption extends ConstraintValidator[Min, Option[_]] {
  private var constraintAnnotation: Min = null

  override def initialize(constraintAnnotation: Min): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: CharSequence) =>
        val v = new MinValidatorForCharSequence
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: Number) =>
        val v = new MinValidatorForNumber
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
