package consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "user-service", port = "8081", pactVersion = PactSpecVersion.V3)
public class UserApiTest {

    @Pact(consumer = "user-consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder
                .given("user exists")
                .uponReceiving("get user")
                // example path（给 MockServer 用）
                .path("/users/1")
                .matchPath("/users/\\d+")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody().integerType("id", 1).stringType("name", "Tom"))
                .toPact();
    }

    @Test
    public void testGetUser(MockServer mockServer) {
        given().baseUri(mockServer.getUrl())
                .when().get("/users/1")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", instanceOf(Integer.class), "name", instanceOf(String.class));
    }
}
