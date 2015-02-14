# Development information

## Deploy

1. Run unit tests.
`sbt test`
1. Change the version in `build.sbt`.
1. Create a new tag which name is new version number.
1. Publish jar to nexus server.
`sbt publishSigned`
1. Access to the [Sonatype OSS site](https://oss.sonatype.org/index.html#welcome).
1. Staging repositories Close and Release.
