package com.srinath.tests.crud;

import com.srinath.base.BaseTest;
import com.srinath.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {
    @Test(groups="smoke",priority=1)
    @TmsLink("https://www.jira.srinath.com")
    @Owner("Darkdevil")
    @Description("Verify HealthCheck")
    public void testGETHealthCheck(){
        requestSpecification.basePath(APIConstants.Ping_Url);
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }

}
