package Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class Repeat extends Simulation{

  val httpProtocol=http.baseUrl("https://gorest.co.in").acceptHeader("application/json")

  def getSpecificUser()={
    repeat(5){
      exec(http("Get specific user").get("/public/v2/users/134").check(status.in(200,304)))

    }


  }
  val scn=scenario("check repeat function")
    .exec(getSpecificUser())
    .pause(3)

  setUp(
    scn.inject(atOnceUsers(1)).protocols(httpProtocol)
  )
}

