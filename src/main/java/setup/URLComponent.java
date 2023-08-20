package setup;

import io.restassured.RestAssured;

public class URLComponent
{

    public URLComponent (String baseURI, String basePath)
    {
        RestAssured.baseURI = baseURI; //domain
        RestAssured.basePath = basePath; //path

    }

    public URLComponent (String baseURI)
    {
        RestAssured.baseURI = baseURI;
    }


}