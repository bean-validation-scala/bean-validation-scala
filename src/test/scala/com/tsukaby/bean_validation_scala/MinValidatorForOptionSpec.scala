package com.tsukaby.bean_validation_scala

import javax.validation.constraints.Min

import scala.annotation.meta.field

class MinValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionString(
                                                     @(Min@field)(10)
                                                     value: Option[String]
                                                     )

  private[this] case class TestBeanWithOptionInt(
                                                  @(Min@field)(10)
                                                  value: Option[Int]
                                                  )

  private[this] case class TestBeanWithOptionDouble(
                                                     @(Min@field)(10)
                                                     value: Option[Double]
                                                     )

  s"$targetClassName" should {
    Seq(
      (TestBeanWithOptionString(Some("9.9")), 1),
      (TestBeanWithOptionString(Some("10.0")), 0),
      (TestBeanWithOptionInt(Some(9)), 1),
      (TestBeanWithOptionInt(Some(10)), 0),
      (TestBeanWithOptionDouble(Some(9.9)), 1),
      (TestBeanWithOptionDouble(Some(10.0)), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }
}
