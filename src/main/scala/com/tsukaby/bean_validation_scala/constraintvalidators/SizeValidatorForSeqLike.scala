package com.tsukaby.bean_validation_scala.constraintvalidators

import java.util
import javax.validation.constraints.Size
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators._

import scala.collection.JavaConversions._
import scala.collection.SeqLike

/**
 * Check that the length of a wrapped value is between min and max.
 */
class SizeValidatorForSeqLike extends ConstraintValidator[Size, SeqLike[_, _]] {
  private var size: Size = null

  override def initialize(constraintAnnotation: Size): Unit = {
    size = constraintAnnotation
  }

  override def isValid(value: SeqLike[_, _], context: ConstraintValidatorContext): Boolean = {
    val v = new SizeValidatorForCollection
    v.initialize(size)
    v.isValid(value.toSeq, context)
  }
}
