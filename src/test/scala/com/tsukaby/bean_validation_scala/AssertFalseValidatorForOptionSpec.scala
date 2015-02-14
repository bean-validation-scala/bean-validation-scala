package com.tsukaby.bean_validation_scala

import javax.validation.constraints.AssertFalse

import scala.annotation.meta.field

class AssertFalseValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(AssertFalse@field)
                                     hasSomething: Option[Boolean]
                                     )


  s"$targetClassName" should {

    val testCases = Seq(
      (TestBean(Some(true)), 1),
      (TestBean(Some(false)), 0)
    )

    testValidation(testCases)

  }
}
