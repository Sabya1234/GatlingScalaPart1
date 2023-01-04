package Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class CsvFeeder extends Simulation{
  val httpProtocol=http.baseUrl("https://www.videogamedb.uk/api").acceptHeader("application/json")

 val csvFeeder=csv("Data/game.csv").circular

  def getAUser()={
    repeat(7){
      feed(csvFeeder)
      .exec(http("get a game by its name").get("/videogame/${gameID}")
        .check(jsonPath("$.name").is("${name}"))
        .check(status.in(200,304)))
        .pause(2)
    }
  }

  val scn =scenario("feed check")
    .exec(getAUser())

setUp(
  scn.inject(atOnceUsers(1)).protocols(httpProtocol)
)

}
