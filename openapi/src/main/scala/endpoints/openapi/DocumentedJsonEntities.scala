package endpoints
package openapi

/**
  * Interpreter for [[algebra.DocumentedJsonEntities]].
  */
trait DocumentedJsonEntities
  extends algebra.DocumentedJsonEntities
    with DocumentedEndpoints {

  // We don’t provide (yet!) a useful JSON schema
  case class JsonSchema[A]()
  object JsonSchema {
    implicit def universal[A]: JsonSchema[A] = JsonSchema()
  }

  type JsonRequest[A] = JsonSchema[A]

  def jsonRequest[A : JsonRequest](description: Option[String]): RequestEntity[A] =
    Some(DocumentedRequestEntity(description, Map("application/json" -> MediaType(None))))

  type JsonResponse[A] = JsonSchema[A]

  def jsonResponse[A : JsonResponse](description: String): Response[A] =
    DocumentedResponse(200, description, Map("application/json" -> MediaType(None))) :: Nil

}
