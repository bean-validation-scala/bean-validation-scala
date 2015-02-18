package com.tsukaby.bean_validation_scala

import java.io.UnsupportedEncodingException
import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.hibernate.validator.internal.util.logging.{Log, LoggerFactory}

/**
 * Check that the length of a wrapped value is between min and max.
 */
class ByteSizeValidator extends ConstraintValidator[ByteSize, Any] {
  private val log: Log = LoggerFactory.make
  private var min: Int = 0
  private var max: Int = 0
  private var charsetName: String = null

  override def initialize(constraintAnnotation: ByteSize): Unit = {
    min = constraintAnnotation.min()
    max = constraintAnnotation.max()
    charsetName = constraintAnnotation.charsetName()
    validateParameters()
  }

  override def isValid(value: Any, context: ConstraintValidatorContext): Boolean = {
    value match {
      case null | None =>
        true
      case Some(x: String) =>
        val length = x.getBytes(charsetName).size
        length >= min && length <= max
      case x: String =>
        val length = x.getBytes(charsetName).size
        length >= min && length <= max
      case _ =>
        throw new IllegalStateException("oops.")
    }
  }

  private def validateParameters() {
    if (min < 0) {
      throw log.getMinCannotBeNegativeException
    }
    if (max < 0) {
      throw log.getMaxCannotBeNegativeException
    }
    if (max < min) {
      throw log.getLengthCannotBeNegativeException
    }

    try {
      "a".getBytes(charsetName)
      ()
    } catch {
      case e: UnsupportedEncodingException =>
        throw new IllegalArgumentException("Invalid charset name", e)
    }
  }
}
