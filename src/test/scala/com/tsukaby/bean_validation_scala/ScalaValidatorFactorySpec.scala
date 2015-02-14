package com.tsukaby.bean_validation_scala

class ScalaValidatorFactorySpec extends BaseSpec {
  "ScalaValidatorFactory#validatorFactory" should {
    "Get a instance of ValidatorFactory" in {
      val validatorFactory = ScalaValidatorFactory.validatorFactory

      validatorFactory must not beNull
    }

    "Get a instance of Validator" in {
      val validator = ScalaValidatorFactory.validator

      validator must not beNull
    }
  }
}
