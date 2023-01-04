package Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class Repeat extends Simulation{

  val httpProtocol=http.baseUrl("https://www.videogamedb.uk/api").acceptHeader("application/json")

  def getSpecificUser()={
    repeat(5){
      exec(http("Get specific user").get("/videogame/1").check(status.in(200,304)))

    }


  }
  val scn=scenario("check repeat function")
    .exec(getSpecificUser())
    .pause(3)

  setUp(
    scn.inject(atOnceUsers(1)).protocols(httpProtocol)
  )
}

