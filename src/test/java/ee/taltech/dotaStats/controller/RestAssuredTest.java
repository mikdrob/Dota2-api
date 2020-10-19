package ee.taltech.dotaStats.controller;

import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

    @Test
    public void RestAssuredTest1(){
        given().
                when().
                get("http://localhost:8080/stats?playerId=100616105").
                then().
                assertThat().statusCode(200).body("player_name", Matchers.equalTo("cml"));

    }
    @Test
    public void RestAssuredTest2(){
        given().
                when().
                get("http://localhost:8080/stats?playerId=100616104").
                then().
                assertThat().statusCode(400).body("message",Matchers.equalTo("Player not found or no available data."));

    }

}
