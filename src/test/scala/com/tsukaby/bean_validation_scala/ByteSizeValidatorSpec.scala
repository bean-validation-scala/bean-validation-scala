package com.tsukaby.bean_validation_scala

import scala.annotation.meta.field


class ByteSizeValidatorSpec extends BaseSpec {

  private[this] case class TestBeanWithString(
                                               @(ByteSize@field)(min = 1, max = 1, charsetName = "UTF-8")
                                               name: String
                                               )

  private[this] case class TestBeanWithOptionString(
                                                     @(ByteSize@field)(min = 1, max = 1, charsetName = "UTF-8")
                                                     name: Option[String]
                                                     )


  Seq(
    (TestBeanWithString("あ"), 1),
    (TestBeanWithString("a"), 0),
    (TestBeanWithOptionString(Some("あ")), 1),
    (TestBeanWithOptionString(Some("a")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }

}