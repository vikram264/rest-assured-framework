package product.api;

import bean.ProductDTO;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import request.base.RequestBase;
import request.methods.HTTPDelete;
import request.methods.HTTPGet;
import request.methods.HTTPPatch;
import request.methods.HTTPPost;
import response.HTTPResponse;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class APITestSuite {

    @Test
    public void testGetProduct() {

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put("productId","1");
        RequestBase requestBase = RequestBase.builder()
                .contentType(ContentType.JSON)

                .pathParams(pathParams)
                .endPoint("/products")
                .build();
        HTTPGet httpGet = new HTTPGet();
        HTTPResponse response = httpGet.composeAndSendRequest(requestBase);
        ProductDTO productDTO = response.as(ProductDTO.class);
        assertEquals(productDTO.getName(), "Apple iphone 14 Pro", "Product Name is not correct");
        // check http status and other validations
    }

    @Test
    public void testPostProduct() {


        String requestBody = "{\"productName\": \"Apple iphone 14 Pro\", \"price\": 19}";

        RequestBase requestBase = RequestBase.builder()
                .contentType(ContentType.JSON)
                .requestBody(requestBody)
                .endPoint("/createProduct")
                .build();
        HTTPPost post = new HTTPPost();
        HTTPResponse response = post.composeAndSendRequest(requestBase);
        ProductDTO productDTO = response.as(ProductDTO.class);
        assertEquals(productDTO.getStatus(), "Ordered", "Product Status is not ordered");
    }

    @Test
    public void testPatchProduct() {


        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put("productId","1");
        String requestBody = "{\"productName\": \"Apple iphone 14 Pro Max\"}";

        RequestBase requestBase = RequestBase.builder()
                .contentType(ContentType.JSON)
                .requestBody(requestBody)
                .pathParams(pathParams)
                .endPoint("/updateProduct")
                .build();

        HTTPPatch patch = new HTTPPatch();
        HTTPResponse response = patch.composeAndSendRequest(requestBase);
        ProductDTO productDTO = response.as(ProductDTO.class);

        assertTrue(productDTO.getName().contains("Apple iphone 14 Pro Max"), "Response doesn't contain 'Apple iphone 14 Pro Max'");
    }

    @Test
    public void testDeleteProduct() {

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put("productId","1");

        RequestBase requestBase = RequestBase.builder()
                .contentType(ContentType.JSON)
                .pathParams(pathParams)
                .endPoint("/deleteProduct")
                .build();

        HTTPDelete delete = new HTTPDelete();
        HTTPResponse response = delete.composeAndSendRequest(requestBase);

        assertTrue(response.getStatusCode()==204, "Response code is not 204");

    }
}
