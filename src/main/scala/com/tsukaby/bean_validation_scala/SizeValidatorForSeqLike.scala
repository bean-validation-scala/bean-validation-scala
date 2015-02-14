package com.tsukaby.bean_validation_scala

import javax.validation.constraints.Size
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._

import scala.collection.JavaConversions._
import scala.collection.SeqLike

/**
 * Check that the length of a wrapped value is between min and max.
 */
class SizeValidatorForSeqLike extends ConstraintValidator[Size, SeqLike[_, _]] {
  private var constraintAnnotation: Size = null

  override def initialize(constraintAnnotation: Size): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: SeqLike[_, _], context: ConstraintValidatorContext): Boolean = {
    val v = new SizeValidatorForCollection
    v.initialize(constraintAnnotation)
    v.isValid(value.toSeq, context)
  }
}
