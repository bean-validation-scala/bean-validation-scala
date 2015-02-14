package com.tsukaby.bean_validation_scala

import org.hibernate.validator.constraints.EAN

import scala.annotation.meta.field

class EANValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(EAN@field)(`type` = EAN.Type.EAN8)
                                     value: Option[String]
                                     )

  s"$targetClassName" should {
    Seq(
      (TestBean(Some("00000001")), 1),
      (TestBean(Some("00000000")), 0)
    )foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }
}
