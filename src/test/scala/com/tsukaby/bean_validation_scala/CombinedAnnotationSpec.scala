package com.tsukaby.bean_validation_scala

import org.hibernate.validator.constraints.{CreditCardNumber, NotEmpty, Range => RangeAnnotation}

import scala.annotation.meta.field

class CombinedAnnotationSpec extends BaseSpec {

  private[this] case class TestBeanForCreditCardNumber(
                                                        @(CreditCardNumber@field)
                                                        value: Option[String]
                                                        )

  s"CreditCardNumber" should {
    Seq(
      (TestBeanForCreditCardNumber(Some("00000001")), 1),
      (TestBeanForCreditCardNumber(Some("00000000")), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }

  private[this] case class TestBeanWithOptionStringForNotEmpty(
                                                                @(NotEmpty@field)
                                                                value: Option[String]
                                                                )

  private[this] case class TestBeanWithOptionArrayForNotEmpty(
                                                               @(NotEmpty@field)
                                                               value: Option[Array[String]]
                                                               )

  s"NotEmpty" should {
    Seq(
      (TestBeanWithOptionStringForNotEmpty(Some("")), 1),
      (TestBeanWithOptionStringForNotEmpty(Some(" ")), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }

    Seq(
      (TestBeanWithOptionArrayForNotEmpty(Some(Array())), 1),
      (TestBeanWithOptionArrayForNotEmpty(Some(Array(""))), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }

  private[this] case class TestBeanForRange(
                                             @(RangeAnnotation@field)(min = 1, max = 1)
                                             value: Option[Int]
                                             )

  s"Range" should {
    Seq(
      (TestBeanForRange(Some(2)), 1),
      (TestBeanForRange(Some(1)), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }

}
