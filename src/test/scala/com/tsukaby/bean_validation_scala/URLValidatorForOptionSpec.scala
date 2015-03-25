package com.tsukaby.bean_validation_scala

import org.hibernate.validator.constraints.URL

import scala.annotation.meta.field

class URLValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(URL@field)
                                     value: Option[String]
                                     )

  Seq(
    (TestBean(Some("htttp://example.com")), 1),
    (TestBean(Some("http://example.com")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
