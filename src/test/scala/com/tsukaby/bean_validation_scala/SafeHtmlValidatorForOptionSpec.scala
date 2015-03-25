package com.tsukaby.bean_validation_scala

import org.hibernate.validator.constraints.SafeHtml

import scala.annotation.meta.field

class SafeHtmlValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(SafeHtml@field)(whitelistType = SafeHtml.WhiteListType.BASIC)
                                     value: Option[String]
                                     )


  val unsafeHtml =
    <script>alert(1);</script>.toString()

  val safeHtml =
    "<p>abc</p>"

  Seq(
    (TestBean(Some(unsafeHtml)), 1),
    (TestBean(Some("")), 0),
    (TestBean(Some(safeHtml)), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
