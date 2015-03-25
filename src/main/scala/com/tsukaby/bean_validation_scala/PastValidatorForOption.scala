package com.tsukaby.bean_validation_scala

import java.util.{Calendar, Date}
import javax.validation.constraints.Past
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._
import org.joda.time.{ReadableInstant, ReadablePartial}

/**
 * Check that the wrapped Calendar, Date and JodaTime classes passed to be validated is in the past.
 */
class PastValidatorForOption extends ConstraintValidator[Past, Option[_]] {
  private var constraintAnnotation: Past = null

  override def initialize(constraintAnnotation: Past): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: Calendar) =>
        val v = new PastValidatorForCalendar
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: Date) =>
        val v = new PastValidatorForDate
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: ReadableInstant) =>
        val v = new PastValidatorForReadableInstant
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: ReadablePartial) =>
        val v = new PastValidatorForReadablePartial
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
