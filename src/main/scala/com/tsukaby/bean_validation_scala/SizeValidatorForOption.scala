package com.tsukaby.bean_validation_scala

import java.util
import javax.validation.constraints.Size
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.constraintvalidators.bv.size._

import scala.collection.JavaConversions._
import scala.collection.SeqLike

/**
 * Check that the length of a wrapped value is between min and max.
 */
class SizeValidatorForOption extends ConstraintValidator[Size, Option[_]] {
  private var constraintAnnotation: Size = null

  override def initialize(constraintAnnotation: Size): Unit = {
    this.constraintAnnotation = constraintAnnotation
  }

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean = {

    value match {
      case Some(x:Array[AnyRef]) =>
        val v = new SizeValidatorForArray
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Boolean]) =>
        val v = new SizeValidatorForArraysOfBoolean
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Byte]) =>
        val v = new SizeValidatorForArraysOfByte
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Char]) =>
        val v = new SizeValidatorForArraysOfChar
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Double]) =>
        val v = new SizeValidatorForArraysOfDouble
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Float]) =>
        val v = new SizeValidatorForArraysOfFloat
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Int]) =>
        val v = new SizeValidatorForArraysOfInt
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Long]) =>
        val v = new SizeValidatorForArraysOfLong
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:Array[Short]) =>
        val v = new SizeValidatorForArraysOfShort
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:CharSequence) =>
        val v = new SizeValidatorForCharSequence
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:util.Collection[_]) =>
        val v = new SizeValidatorForCollection
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case Some(x:SeqLike[_, _]) =>
        val v = new SizeValidatorForCollection
        v.initialize(constraintAnnotation)
        v.isValid(x.toSeq, context)
      case Some(x:Map[_, _]) =>
        val v = new SizeValidatorForMap
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None =>
        true
      case Some(_) =>
        throw new IllegalStateException("oops.")
    }
  }
}
