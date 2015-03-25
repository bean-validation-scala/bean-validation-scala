package com.tsukaby.bean_validation_scala

import scala.annotation.meta.field

class AssertSomeValidatorSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(AssertSome@field)
                                     value: Option[Int]
                                     )


  Seq(
    (TestBean(None), 1),
    (TestBean(Some(1)), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }

}
