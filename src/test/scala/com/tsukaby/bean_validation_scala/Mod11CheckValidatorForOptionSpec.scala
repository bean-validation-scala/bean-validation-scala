package com.tsukaby.bean_validation_scala

import org.hibernate.validator.constraints.Mod11Check

import scala.annotation.meta.field

class Mod11CheckValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(Mod11Check@field)
                                     value: Option[String]
                                     )

  Seq(
    (TestBean(Some("00000001")), 1),
    (TestBean(Some("00000000")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
