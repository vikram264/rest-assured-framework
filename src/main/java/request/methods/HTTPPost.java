package request.methods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import request.HTTPRequest;
import request.base.RequestBase;
import response.HTTPResponse;

import java.util.Objects;
@Slf4j
public class HTTPPost implements HTTPRequest<RequestBase, HTTPResponse> {
    @Override
    public HTTPResponse composeAndSendRequest(RequestBase requestBase) {
        Objects.requireNonNull(requestBase,"Request Base Object is null.");
        log.info("Received the HTTP Post call for the end point {}", requestBase.getEndPoint());
        Response response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .contentType(requestBase.getContentType())
                .headers(requestBase.getHeaders())
                .queryParams(requestBase.getQueryParams())
                .formParams(requestBase.getFormParameters())
                .body(requestBase.getRequestBody())
                .post(requestBase.getEndPoint());
        log.info("Response of HTTP Post for the end point {} is {} ", requestBase.getEndPoint() ,response.prettyPrint());
        return new HTTPResponse(response);
    }
}
