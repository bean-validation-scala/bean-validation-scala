package com.tsukaby.bean_validation_scala

import java.util.{Calendar, Date}
import javax.validation.constraints.Future
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._
import org.joda.time.{ReadableInstant, ReadablePartial}

/**
 * Check that the wrapped Calendar, Date and JodaTime classes passed to be validated is in the future.
 */
class FutureValidatorForOption extends ConstraintValidator[Future, Option[_]] {
  private var constraintAnnotation: Future = null

  override def initialize(constraintAnnotation: Future): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x: Calendar) =>
        val v = new FutureValidatorForCalendar
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: Date) =>
        val v = new FutureValidatorForDate
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: ReadableInstant) =>
        val v = new FutureValidatorForReadableInstant
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x: ReadablePartial) =>
        val v = new FutureValidatorForReadablePartial
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(_) | None =>
        throw new IllegalStateException("oops.")
    }
  }
}
