package request.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import request.HTTPRequest;
import request.base.RequestBase;
import response.HTTPResponse;

import java.util.Objects;

@Slf4j
public class HTTPPatch implements HTTPRequest<RequestBase, HTTPResponse> {
    @Override
    public HTTPResponse composeAndSendRequest(RequestBase requestBase) {
        Objects.requireNonNull(requestBase,"Request Base Object is null.");
        log.info("Received the HTTP Patch call for the end point {}", requestBase.getEndPoint());
        Response response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .headers(requestBase.getHeaders())
                .contentType(requestBase.getContentType())
                .queryParams(requestBase.getQueryParams())
                .pathParams(requestBase.getPathParams())
                .body(requestBase.getRequestBody())
                .when()
                .patch(requestBase.getEndPoint());

        log.info("Response of HTTP Patch for the end point {} is {} ", requestBase.getEndPoint() ,response.prettyPrint());
        return new HTTPResponse(response);
    }
}
