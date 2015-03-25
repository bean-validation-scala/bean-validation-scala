package com.tsukaby.bean_validation_scala

import javax.validation.constraints.Max

import scala.annotation.meta.field

class MaxValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionString(
                                                     @(Max@field)(10)
                                                     value: Option[String]
                                                     )

  private[this] case class TestBeanWithOptionInt(
                                                  @(Max@field)(10)
                                                  value: Option[Int]
                                                  )

  private[this] case class TestBeanWithOptionDouble(
                                                     @(Max@field)(10)
                                                     value: Option[Double]
                                                     )

  Seq(
    (TestBeanWithOptionString(Some("10.1")), 1),
    (TestBeanWithOptionString(Some("10.0")), 0),
    (TestBeanWithOptionInt(Some(11)), 1),
    (TestBeanWithOptionInt(Some(10)), 0),
    (TestBeanWithOptionDouble(Some(11.0)), 1),
    (TestBeanWithOptionDouble(Some(10.0)), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
