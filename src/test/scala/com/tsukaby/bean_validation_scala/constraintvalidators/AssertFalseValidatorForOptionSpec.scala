package com.tsukaby.bean_validation_scala.constraintvalidators

import javax.validation.constraints.AssertFalse

import com.tsukaby.bean_validation_scala.{BaseSpec, ScalaValidatorFactory}

import scala.annotation.meta.field

class AssertFalseValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(AssertFalse@field)
                                     hasSomething: Option[Boolean]
                                     )


  "AssertFalseValidatorForOption" should {
    "Option[Boolean] bean has violations" in {
      val validator = ScalaValidatorFactory.validatorFactory.getValidator

      val bean = TestBean(Some(true))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }
  }
}
