package Simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicCustomFeeder extends Simulation {

  //http configure

  val httpProtocol = http.baseUrl("https://www.videogamedb.uk/api").acceptHeader("application/json")

  //randomly generating an id
  var idNum = (1 to 10).iterator
  val customFeeder = Iterator.continually(Map("gameId" -> idNum.next()))

  def getSpecificGame() = {
    repeat(10) {
      feed(customFeeder).exec(http("geta specific gamefor ${gameId}").get("/videogame/${gameId}")
        .check(status.is(200)))
        .pause(1)

    }
  }


  val scn = scenario("basic custom feeder")
    .exec(getSpecificGame())

  setUp(
    scn.inject(rampUsers(5).during(5)).protocols(httpProtocol)
  )

}
