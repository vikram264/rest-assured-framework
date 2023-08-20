package request.base;




import io.restassured.http.ContentType;
import io.restassured.specification.MultiPartSpecification;
import lombok.Builder;
import lombok.Data;
import java.nio.file.Path;
import java.util.HashMap;
@Builder
@Data
public class RequestBase
{

    private HashMap<String, Object> formParameters;

    private Path requestBodyfilePath; //Path

    private String endPoint;

    private HashMap<String, Object> headers;

    private String requestBody;

    private HashMap<String, Object> queryParams;

    private HashMap<String, Object> pathParams;

    private ContentType contentType;

    private MultiPartSpecification multipart;

}
