package com.tsukaby.bean_validation_scala

import scala.annotation.meta.field

class AssertNoneValidatorSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(AssertNone@field)
                                     value: Option[Int]
                                     )

  s"$targetClassName" should {
    Seq(
      (TestBean(Some(1)), 1),
      (TestBean(None), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }

}
