package ws

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json.{DefaultJsonProtocol, _}

case class Person(id: Long, name: String, age: Int, address: String)

trait Protocols extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val personFormat = jsonFormat4(Person)
}

trait Service extends Protocols {
  private val person1: Person = Person(1, "Jane Doe", 25, "London")
  private val person2: Person = Person(2, "John Doe", 29, "Berlin")
  private val person3: Person = Person(3, "Billy Bob", 33, "Madrid")

  var persons = Seq(person1, person2, person3)

  val route =
    logRequestResult("person-service") {
      path("person" / LongNumber) { personId =>
          get {
            complete(persons.find(p => p.id == personId))
          }
      } ~
      path("person") {
        get {
          complete(persons.toJson)
        } ~
        post {
          entity(as[Person]) { person =>
            complete {
              persons = persons :+ person
              persons.toJson
            }
          }
        }
      }
    }
}

object PersonService extends App with Service {

  implicit val system = ActorSystem("PersonService")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  println("Person service has been started.")
}
