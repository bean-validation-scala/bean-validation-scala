package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.EAN
import org.hibernate.validator.internal.constraintvalidators.hv.EANValidator

/**
 * Checks that a given wrapped character sequence (e.g. Option[String]) is a valid EAN barcode.
 */
class EANValidatorForOption extends ConstraintValidator[EAN, Option[_]] {
  private var constraintAnnotation: EAN = null

  override def initialize(constraintAnnotation: EAN): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x:CharSequence) =>
        val v = new EANValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
