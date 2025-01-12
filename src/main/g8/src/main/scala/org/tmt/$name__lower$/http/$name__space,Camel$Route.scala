package org.tmt.$name;format="lower"$.http

import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.{Flow, Source}
import csw.aas.http.AuthorizationPolicy.RealmRolePolicy
import csw.aas.http.SecurityDirectives
import io.bullet.borer.Json
import org.tmt.$name;format="lower"$.core.$name;format="space,Camel"$Impl
import org.tmt.$name;format="lower"$.core.models.Person

import scala.concurrent.ExecutionContext

class $name;format="space,Camel"$Route(service1: $name;format="space,Camel"$Impl, service2: J$name;format="space,Camel"$ImplWrapper, securityDirectives: SecurityDirectives) (implicit  ec: ExecutionContext) extends HttpCodecs {

  val route: Route = path("sayHello") {
    complete(service1.sayHello())
  } ~
    path("sayBye") {
      complete(service2.sayBye())
    } ~
    path("securedSayHello") {
      securityDirectives.sPost(RealmRolePolicy("Esw-user")) { token =>
        entity(as[Person]) { person => complete(service1.securedSayHello(person)) }
      }
    } ~
    path("locations") {
      securityDirectives.sGet(RealmRolePolicy("Esw-admin")) { token =>
        complete(service1.locations())
      }
    } ~
    path("greeter") {
      handleWebSocketMessages(greeter)
    } ~
    path("getFile" / Segment) { name =>
      getFromResource(s"\$name")
    }

  def greeter: Flow[Message, Message, Any] = {
    Flow[Message].flatMapConcat {
      case message: TextMessage.Strict =>
        val person = Json.decode(message.text.getBytes()).to[Person].value
        service1.sayHelloStream(person).map(s => TextMessage(Json.encode(s).toUtf8String))
      case _ =>
        Source.failed(new NotImplementedError("unhandled"))
    }
  }
}
