import sbt._

object Libs {
  val `esw-http-template-wiring` = "com.github.tmtsoftware.esw" %% "esw-http-template-wiring" % "0.2.1"

  //testing
  val `akka-http-testkit`        = "com.typesafe.akka"                        %% "akka-http-testkit"        % "10.2.1"
  val `akka-actor-testkit-typed` = "com.typesafe.akka"                        %% "akka-actor-testkit-typed" % "2.6.10"
  val `akka-stream-testkit`      = "com.typesafe.akka"                        %% "akka-stream-testkit"      % "2.6.10"
  val `embedded-keycloak`        = "com.github.tmtsoftware.embedded-keycloak" %% "embedded-keycloak"        % "0.2.0"
  val `mockito-scala`            = "org.mockito"                              %% "mockito-scala"            % "1.16.0"
  val `scalatest`                = "org.scalatest"                            %% "scalatest"                % "3.2.2"
}
