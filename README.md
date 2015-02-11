# bean-validation-scala
JSR 303 Bean Validation for Scala.

master  : [![Build Status](https://travis-ci.org/bean-validation-scala/bean-validation-scala.svg?branch=master)](https://travis-ci.org/bean-validation-scala/bean-validation-scala)

## Getting Started

```
libraryDependencies ++= Seq(
  // Incompletion.
  // Thank you for your patience.
)
```

## How to use

Step 1. Add annotations to your case class.

    import javax.validation.constraints.Size

    case class Person(
      @(Size@field)(min = 4)
      name: Option[String]
    )

Step 2. Create validator by our library.

    import com.tsukaby.bean_validation_scala.ScalaValidatorFactory

    val validator = ScalaValidatorFactory.validatorFactory.getValidator

Step 3. Validate a case class object.

      val obj = Person(Some("abc"))
      val violations = validator.validate(obj)

      if (violations.nonEmpty) {
        println("Violations found!")
      }

## License

```
Copyright 2015 - 2015 tsukaby.com
MIT License
```
