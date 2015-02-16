package com.tsukaby.bean_validation_scala

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

/**
 * Validates that the wrapped value passed is none
 */
class AssertNoneValidator extends ConstraintValidator[AssertNone, Option[_]] {

  override def initialize(constraintAnnotation: AssertNone): Unit = {
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {
    value.isEmpty
  }
}
