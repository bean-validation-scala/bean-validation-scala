package com.tsukaby.bean_validation_scala.constraintvalidators

import javax.validation.constraints.Size

import com.tsukaby.bean_validation_scala.{BaseSpec, ScalaValidatorFactory}

import scala.annotation.meta.field


class SizeValidatorForSeqLikeSpec extends BaseSpec {

  private[this] case class TestBeanWithSeq(
                                            @(Size@field)(min = 1)
                                            name: Seq[String]
                                            )

  private[this] case class TestBeanWithList(
                                             @(Size@field)(min = 1)
                                             name: List[String]
                                             )

  private[this] case class TestBeanWithVector(
                                             @(Size@field)(min = 1)
                                             name: Vector[String]
                                             )

  "SizeValidatorForOption" should {
    "Seq[String] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithSeq(Seq())
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }

    "List[String] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithList(List())
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }

    "Vector[String] bean has violations" in {
      val validator = ScalaValidatorFactory.validator

      val bean = TestBeanWithVector(Vector())
      val violations = validator.validate(bean)

      violations.size must be equalTo 1
    }
  }
}
