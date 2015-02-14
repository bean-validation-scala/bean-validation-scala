package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.SafeHtml
import org.hibernate.validator.internal.constraintvalidators._

/**
 * Check the wrapped string.
 */
class SafeHtmlValidatorForOption extends ConstraintValidator[SafeHtml, Option[_]] {
  private var constraintAnnotation: SafeHtml = null

  override def initialize(constraintAnnotation: SafeHtml): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {
    value match {
      case Some(x:CharSequence) =>
        val v = new SafeHtmlValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(_) | None =>
        throw new IllegalStateException("oops.")
    }
  }
}
