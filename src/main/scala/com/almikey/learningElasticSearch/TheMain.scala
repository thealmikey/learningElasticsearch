package com.almikey.learningElasticSearch

import com.sksamuel.elastic4s.{ElasticsearchClientUri, Hit, HitReader}
import com.sksamuel.elastic4s.http.HttpClient
import com.sksamuel.elastic4s.http.ElasticDsl._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object TheMain extends App {

  val client = HttpClient(ElasticsearchClientUri("localhost", 9200))

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
    search("places" / "cities").query("africa")
  )

  resp.onComplete(
    x => x match {
      case Success(Right(something))=>print(something.result.to[City])
      case Failure(e)=>println(e.getMessage)
    }
  )

}
