package com.tsukaby.bean_validation_scala

import javax.validation.constraints.Pattern

import scala.annotation.meta.field

class PatternValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(Pattern@field)(regexp = "abc")
                                     value: Option[String]
                                     )


  s"$targetClassName" should {
    Seq(
      (TestBean(Some("abcd")), 1),
      (TestBean(Some("abc")), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }
}
