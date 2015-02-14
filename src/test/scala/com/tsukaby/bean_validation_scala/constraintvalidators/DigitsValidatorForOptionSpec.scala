package com.tsukaby.bean_validation_scala.constraintvalidators

import javax.validation.constraints.Digits

import com.tsukaby.bean_validation_scala.{BaseSpec, ScalaValidatorFactory}

import scala.annotation.meta.field

class DigitsValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionString(
                                                     @(Digits@field)(integer = 1, fraction = 1)
                                                     value: Option[String]
                                                     )

  private[this] case class TestBeanWithOptionInt(
                                                  @(Digits@field)(integer = 1, fraction = 0)
                                                  value: Option[Int]
                                                  )

  private[this] case class TestBeanWithOptionDouble(
                                                     @(Digits@field)(integer = 1, fraction = 1)
                                                     value: Option[Double]
                                                     )


  "DigitsValidatorForOption" should {
    "Option[String] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithOptionString(Some("10.1"))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }
    "Option[Int] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithOptionInt(Some(10))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }
    "Option[Double] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithOptionDouble(Some(10.1))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }
  }
}
