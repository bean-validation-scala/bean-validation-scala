package com.tsukaby.bean_validation_scala

import javax.validation.constraints.DecimalMin

import scala.annotation.meta.field

class DecimalMinValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionString(
                                                     @(DecimalMin@field)("10.0")
                                                     value: Option[String]
                                                     )

  private[this] case class TestBeanWithOptionInt(
                                                  @(DecimalMin@field)("10.0")
                                                  value: Option[Int]
                                                  )

  private[this] case class TestBeanWithOptionDouble(
                                                     @(DecimalMin@field)("10.0")
                                                     value: Option[Double]
                                                     )

  Seq(
    (TestBeanWithOptionString(Some("9.9")), 1),
    (TestBeanWithOptionString(Some("10.0")), 0),
    (TestBeanWithOptionInt(Some(9)), 1),
    (TestBeanWithOptionInt(Some(10)), 0),
    (TestBeanWithOptionDouble(Some(9.9)), 1),
    (TestBeanWithOptionDouble(Some(10.0)), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
