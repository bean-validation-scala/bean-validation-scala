package com.tsukaby.bean_validation_scala.constraintvalidators

import javax.validation.constraints.Size

import com.tsukaby.bean_validation_scala.{BaseSpec, ScalaValidatorFactory}

import scala.annotation.meta.field


class SizeValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionString(
                                                     @(Size@field)(min = 1)
                                                     name: Option[String]
                                                     )

  private[this] case class TestBeanWithOptionArray(
                                                    @(Size@field)(min = 1)
                                                    name: Option[Array[String]]
                                                    )

  private[this] case class TestBeanWithOptionSeq(
                                                    @(Size@field)(min = 1)
                                                    name: Option[Seq[String]]
                                                    )



  "SizeValidatorForOption" should {
    "Option[String] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithOptionString(Some(""))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }

    "Option[Array[String]] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithOptionArray(Some(Array()))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }

    "Option[Seq[String]] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithOptionSeq(Some(Seq()))
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }
  }
}
