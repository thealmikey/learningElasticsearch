package com.almikey.learningElasticSearch

import com.sksamuel.elastic4s.ElasticsearchClientUri
import com.sksamuel.elastic4s.http.HttpClient
import com.sksamuel.elastic4s.http.ElasticDsl._

object TheMain extends App {

  val client = HttpClient(ElasticsearchClientUri("localhost", 9200))

//  client.execute(createIndex("places"))

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

}
