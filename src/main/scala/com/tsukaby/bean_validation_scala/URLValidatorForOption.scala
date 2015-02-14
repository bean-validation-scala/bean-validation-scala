package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.constraints.URL
import org.hibernate.validator.internal.constraintvalidators._

/**
 * Validate that the wrapped character sequence (e.g. Option[String]) is a valid URL.
 */
class URLValidatorForOption extends ConstraintValidator[URL, Option[_]] {
  private var constraintAnnotation: URL = null

  override def initialize(constraintAnnotation: URL): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x:CharSequence) =>
        val v = new URLValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(_) | None =>
        throw new IllegalStateException("oops.")
    }
  }
}
