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
    val url = Thread.currentThread().getContextClassLoader.getResource("scala-constraint-mapping.xml")
    val file = new File(url.getPath)
    val fis = new FileInputStream(file)

    val validatorFactory = Validation.byDefaultProvider().configure().addMapping(fis).asInstanceOf[ConfigurationImpl].buildValidatorFactory()

    validatorFactory
  }

}
