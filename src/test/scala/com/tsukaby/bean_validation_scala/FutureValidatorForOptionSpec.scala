package com.tsukaby.bean_validation_scala

import java.util.{Calendar, Date, Locale}
import javax.validation.constraints.Future

import org.joda.time.DateTime

import scala.annotation.meta.field

class FutureValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionCalendar(
                                                       @(Future@field)
                                                       value: Option[Calendar]
                                                       )

  private[this] case class TestBeanWithOptionDate(
                                                   @(Future@field)
                                                   value: Option[Date]
                                                   )

  private[this] case class TestBeanWithOptionDateTime(
                                                       @(Future@field)
                                                       value: Option[DateTime]
                                                       )

  val yesterday = DateTime.now().minusDays(1)
  val tomorrow = DateTime.now().plusDays(1)
  Seq(
    (TestBeanWithOptionCalendar(Some(yesterday.toCalendar(Locale.getDefault))), 1),
    (TestBeanWithOptionCalendar(Some(tomorrow.toCalendar(Locale.getDefault))), 0),
    (TestBeanWithOptionDate(Some(yesterday.toDate)), 1),
    (TestBeanWithOptionDate(Some(tomorrow.toDate)), 0),
    (TestBeanWithOptionDateTime(Some(yesterday)), 1),
    (TestBeanWithOptionDateTime(Some(tomorrow)), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
