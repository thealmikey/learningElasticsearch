package com.almikey.learningElasticSearch

import com.sksamuel.elastic4s.{ElasticsearchClientUri, Hit, HitReader}
import com.sksamuel.elastic4s.http.HttpClient
import com.sksamuel.elastic4s.http.ElasticDsl._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object TheMain extends App {

  val client = HttpClient(ElasticsearchClientUri("localhost", 9200))
  client
    .execute(createIndex("places"))
    .onComplete(_ => {
      client.execute(
        bulk(
          indexInto("places" / "cities") id "kny" fields (
            "name" -> "Nairobi",
            "country" -> "Kenya",
            "continent" -> "Africa",
            "status" -> "Safari"
          ),
          indexInto("places" / "cities") id "ug" fields (
            "name" -> "Kampala",
            "country" -> "Uganda",
            "continent" -> "Africa",
            "status" -> "Land of museveni"
          ),
          indexInto("places" / "cities") id "tz" fields (
            "name" -> "Dodoma",
            "country" -> "Tanzania",
            "continent" -> "Africa",
            "status" -> "Land of Magufuli"
          ),
          indexInto("places" / "cities") id "us" fields (
            "name" -> "Washington",
            "country" -> "USA",
            "continent" -> "America",
            "status" -> "Land of Trump"
          )
        )
      )
    })

  case class City(name: String,
                  country: String,
                  continent: String,
                  status: String)

  implicit object cityHitReader extends HitReader[City] {
    override def read(hit: Hit): Either[Throwable, City] = {
      Right(
        City(hit.sourceAsMap("name").toString,
             hit.sourceAsMap("country").toString,
             hit.sourceAsMap("continent").toString,
             hit.sourceAsMap("status").toString))
    }
  }

  val resp = client.execute(
    search("places" / "cities").query("nairobi")
  )

  resp.onComplete(
    x =>
      x match {
        case Success(Right(something)) => print(something.result.to[City])
        case Failure(e)                => println(e.getMessage)
    }
  )

}
