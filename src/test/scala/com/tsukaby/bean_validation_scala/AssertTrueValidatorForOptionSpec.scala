package com.tsukaby.bean_validation_scala

import javax.validation.constraints.AssertTrue

import scala.annotation.meta.field

class AssertTrueValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
                                     @(AssertTrue@field)
                                     hasSomething: Option[Boolean]
                                     )


  s"$targetClassName" should {

    val testCases = Seq(
      (TestBean(Some(false)), 1),
      (TestBean(Some(true)), 0)
    )

    testValidation(testCases)

  }

}
