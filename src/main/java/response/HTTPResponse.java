package response;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.io.InputStream;
import java.util.Optional;

public class HTTPResponse
{

    Response response;


    public HTTPResponse(Response response)
    {
        super();
        this.response = response;
    }

    public String getContentType ()
    {
        return response.getContentType();
    }

    public Headers getHeaders ()
    {
        return response.getHeaders();
    }

    public String prettyPrint ()
    {
        return response.prettyPrint();
    }

    public int getStatusCode ()
    {

        return response.getStatusCode();
    }

    public String getStatusLine ()
    {
        return response.getStatusLine();
    }

    public long getResponseTime ()
    {
        return response.time();
    }

    public ResponseBody<?> getResponseBody ()
    {
        return response.getBody();
    }

    public long size ()
    {
        return getResponseBody().path("list.size()");
    }

    public String getHeaderValue (String headerKey)
    {
        return getHeaders().getValue(headerKey);
    }

    public String asString ()
    {
        return response.prettyPrint();
    }

    public Object jsonValue (String key)
    {
        String jsonKey = Optional.ofNullable(key)
            .orElse("$.");
        return response.jsonPath()
            .get(jsonKey);
    }

    public InputStream getInputStream ()
    {
        return response.getBody().asInputStream();
    }


    public  <T> T as(Class<T> cls) {

        return getResponseBody().as(cls);
    }

}
