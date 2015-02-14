package com.tsukaby.bean_validation_scala.constraintvalidators

import javax.validation.constraints.AssertTrue

import com.tsukaby.bean_validation_scala.{BaseSpec, ScalaValidatorFactory}

import scala.annotation.meta.field

class AssertTrueValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(AssertTrue@field)
                                     hasSomething: Option[Boolean]
                                     )


  "AssertFalseValidatorForOption" should {
    "Option[String] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBean(Some(false))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }
  }
}
