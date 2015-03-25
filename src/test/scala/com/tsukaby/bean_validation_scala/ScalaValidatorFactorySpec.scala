package com.tsukaby.bean_validation_scala

class ScalaValidatorFactorySpec extends BaseSpec {
  "ScalaValidatorFactory#validatorFactory" >> {
    "Get a instance of ValidatorFactory" >> {
      val validatorFactory = ScalaValidatorFactory.validatorFactory

      validatorFactory must_!= null
    }

    "Get a instance of Validator" >> {
      val validator = ScalaValidatorFactory.validator

      validator must_!= null
    }
  }
}
