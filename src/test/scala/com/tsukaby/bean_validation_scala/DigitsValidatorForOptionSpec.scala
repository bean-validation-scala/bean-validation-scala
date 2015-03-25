package com.tsukaby.bean_validation_scala

import javax.validation.constraints.Digits

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

  Seq(
    (TestBeanWithOptionString(Some("10.1")), 1),
    (TestBeanWithOptionString(Some("1.1")), 0),
    (TestBeanWithOptionInt(Some(10)), 1),
    (TestBeanWithOptionInt(Some(1)), 0),
    (TestBeanWithOptionDouble(Some(10.1)), 1),
    (TestBeanWithOptionDouble(Some(1.1)), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
