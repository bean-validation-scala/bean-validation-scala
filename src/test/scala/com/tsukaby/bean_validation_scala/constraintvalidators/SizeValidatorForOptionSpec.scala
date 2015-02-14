package com.tsukaby.bean_validation_scala.constraintvalidators

import javax.validation.constraints.Size

import com.tsukaby.bean_validation_scala.BaseSpec

import scala.annotation.meta.field


class SizeValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBeanWithOptionString(
                                                     @(Size@field)(min = 1)
                                                     name: Option[String]
                                                     )

  private[this] case class TestBeanWithOptionArray(
                                                    @(Size@field)(min = 1)
                                                    name: Option[Array[String]]
                                                    )

  private[this] case class TestBeanWithOptionSeq(
                                                  @(Size@field)(min = 1)
                                                  name: Option[Seq[String]]
                                                  )

  s"$targetClassName" should {


    val testCases = Seq(
      (TestBeanWithOptionString(Some("")), 1),
      (TestBeanWithOptionString(Some("1")), 0),
      (TestBeanWithOptionArray(Some(Array())), 1),
      (TestBeanWithOptionArray(Some(Array("1"))), 0),
      (TestBeanWithOptionSeq(Some(Seq())), 1),
      (TestBeanWithOptionSeq(Some(Seq("1"))), 0)
    )

    testValidation(testCases)
  }
}