package com.tsukaby.bean_validation_scala

import java.util.{Calendar, Date, Locale}
import javax.validation.constraints.Past

import org.joda.time.DateTime

import scala.annotation.meta.field

class PastValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionCalendar(
                                                       @(Past@field)
                                                       value: Option[Calendar]
                                                       )

  private[this] case class TestBeanWithOptionDate(
                                                   @(Past@field)
                                                   value: Option[Date]
                                                   )

  private[this] case class TestBeanWithOptionDateTime(
                                                       @(Past@field)
                                                       value: Option[DateTime]
                                                       )

  s"$targetClassName" should {
    val tomorrow = DateTime.now().plusDays(1)
    val yesterday = DateTime.now().minusDays(1)
    Seq(
      (TestBeanWithOptionCalendar(Some(tomorrow.toCalendar(Locale.getDefault))), 1),
      (TestBeanWithOptionCalendar(Some(yesterday.toCalendar(Locale.getDefault))), 0),
      (TestBeanWithOptionDate(Some(tomorrow.toDate)), 1),
      (TestBeanWithOptionDate(Some(yesterday.toDate)), 0),
      (TestBeanWithOptionDateTime(Some(tomorrow)), 1),
      (TestBeanWithOptionDateTime(Some(yesterday)), 0)
    ) foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        test(bean, expected)
      }
    }
  }
}
