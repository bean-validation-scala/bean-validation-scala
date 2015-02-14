package com.tsukaby.bean_validation_scala

import javax.validation.constraints.DecimalMax

import scala.annotation.meta.field

class DecimalMaxValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionString(
                                                     @(DecimalMax@field)("10.0")
                                                     value: Option[String]
                                                     )

  private[this] case class TestBeanWithOptionInt(
                                                  @(DecimalMax@field)("10.0")
                                                  value: Option[Int]
                                                  )

  private[this] case class TestBeanWithOptionDouble(
                                                     @(DecimalMax@field)("10.0")
                                                     value: Option[Double]
                                                     )

  s"$targetClassName" should {
    Seq(
      (TestBeanWithOptionString(Some("10.1")), 1),
      (TestBeanWithOptionString(Some("10.0")), 0),
      (TestBeanWithOptionInt(Some(11)), 1),
      (TestBeanWithOptionInt(Some(10)), 0),
      (TestBeanWithOptionDouble(Some(10.1)), 1),
      (TestBeanWithOptionDouble(Some(10.0)), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }
}
