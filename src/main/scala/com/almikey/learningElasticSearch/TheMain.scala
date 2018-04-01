package com.almikey.learningElasticSearch

import com.sksamuel.elastic4s.ElasticsearchClientUri
import com.sksamuel.elastic4s.http.HttpClient
import com.sksamuel.elastic4s.http.ElasticDsl._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

object TheMain extends App {

  val client = HttpClient(ElasticsearchClientUri("localhost", 9200))

//  client.execute(createIndex("places"))

  val resp = client.execute(
    search("places" / "cities").query("africa")
  )

  resp.onComplete(
    x => x match {
      case Success(Right(something))=>print(something)
      case Failure(e)=>println(e.getMessage)
    }
  )
}
