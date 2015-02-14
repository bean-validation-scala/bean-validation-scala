package com.tsukaby.bean_validation_scala

import javax.validation.constraints.AssertTrue

import scala.annotation.meta.field

class AssertTrueValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(AssertTrue@field)
                                     hasSomething: Option[Boolean]
                                     )


  s"$targetClassName" should {
    Seq(
      (TestBean(Some(false)), 1),
      (TestBean(Some(true)), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }

}
