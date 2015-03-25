package com.tsukaby.bean_validation_scala

import org.hibernate.validator.constraints.NotBlank

import scala.annotation.meta.field

class NotBlankValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(NotBlank@field)
                                     value: Option[String]
                                     )

  Seq(
    (TestBean(Some("")), 1),
    (TestBean(Some(" ")), 1),
    (TestBean(Some("a")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
