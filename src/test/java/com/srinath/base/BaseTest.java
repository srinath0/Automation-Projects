package com.srinath.base;

import com.srinath.asserts.AssertActions;
import com.srinath.endpoints.APIConstants;
import com.srinath.modules.PayLoadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    //This is common to all testcases
    // BaseURL,content-type

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayLoadManager payLoadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp(){
        payLoadManager = new PayLoadManager();
        assertActions = new AssertActions();
        //requestSpecification=requestSpecification.given().baseUri(APIConstants.Base_Url).contentType(contentType.JSON).log().all();
    requestSpecification = new RequestSpecBuilder().setBaseUri(APIConstants.Base_Url).addHeader("Content-Type","application/json").build().log().all();
    }
    public String getToken(){
        requestSpecification = RestAssured.given().baseUri(APIConstants.Base_Url).basePath(APIConstants.AUTH_Url);

        String payLoad = payLoadManager.setAuthPayload();
        response = requestSpecification.contentType(ContentType.JSON).body(payLoad).when().post();
        String token = payLoadManager.getTokenFromJSON(response.asString());

        return token;
    }

}
