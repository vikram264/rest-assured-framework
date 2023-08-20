package request;

public interface HTTPRequest<T,V>
{
    //Composes the HTTP Request and sends it to the end point mentioned in the URI
     V composeAndSendRequest(T t) throws Exception;

}