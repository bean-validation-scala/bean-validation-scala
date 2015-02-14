package com.tsukaby.bean_validation_scala

import java.io.{File, FileInputStream}
import javax.validation.Validation

import org.hibernate.validator.internal.engine.ConfigurationImpl

/**
 * ValidatorFactory for scala.
 */
object ScalaValidatorFactory {

  /**
   * Provide a ValidatorFactory with scala extensions.
   */
  lazy val validatorFactory = {
    val stream = getClass.getClassLoader.getResourceAsStream("scala-constraint-mapping.xml")

    val validatorFactory = Validation.byDefaultProvider().configure().addMapping(stream).asInstanceOf[ConfigurationImpl].buildValidatorFactory()

    validatorFactory
  }

}
