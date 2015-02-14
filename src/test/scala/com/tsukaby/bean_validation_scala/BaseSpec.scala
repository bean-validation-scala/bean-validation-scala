package com.tsukaby.bean_validation_scala

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

/**
 * Base of all specs.
 */
trait BaseSpec extends Specification with Mockito {
  def targetClassName = {
    this.getClass.getSimpleName.replaceAll("Spec$", "")
  }

  def testValidation(testCases: Seq[(Any, Int)]) = {
    testCases foreach { case (bean, expected) =>
      s"Check violations count. bean = $bean, count = $expected" in {
        val validator = ScalaValidatorFactory.validator
        val violations = validator.validate(bean)
        violations.size must be equalTo expected
      }
    }
  }

  def test(bean:Any, expected:Int) = {
    val validator = ScalaValidatorFactory.validator
    val violations = validator.validate(bean)
    violations.size must be equalTo expected

  }

}
