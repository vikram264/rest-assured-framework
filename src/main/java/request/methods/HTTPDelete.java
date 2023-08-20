package request.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import request.HTTPRequest;
import request.base.RequestBase;
import response.HTTPResponse;

import java.util.Objects;

@Slf4j
public class HTTPDelete implements HTTPRequest<RequestBase, HTTPResponse> {
    @Override
    public HTTPResponse composeAndSendRequest(RequestBase requestBase)  {
        Objects.requireNonNull(requestBase,"Request Base Object is null.");
        log.info("Received the HTTP Delete call for the end point {}", requestBase.getEndPoint());
        // Add other necessary request validations

        Response response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .headers(requestBase.getHeaders())
                .pathParams(requestBase.getPathParams())
                .when()
                .delete(requestBase.getEndPoint());

        log.info("Response of HTTP Delete for the end point {} is {} ", requestBase.getEndPoint() ,response.prettyPrint());

        return new HTTPResponse(response);
    }
}
