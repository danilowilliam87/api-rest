package com.teste.attornatus.api.testcontract;

import com.google.gson.Gson;
import com.teste.attornatus.api.domain.Pessoa;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PessoaApiTestIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void initialize(){
        RestAssured.port = port;
    }

    @Test
    void testSavePessoa(){
        String json = "{\"nome\":\"Alexandre Saudate\", \"dataNascimento\":\"2000-10-10\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("nome", equalTo("Alexandre Saudate"));
    }

    @Test
    void testSavePessoaException(){
        String json = "{\"nome\":\"\", \"dataNascimento\":\"2000-10-10\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas")
                .then()
                .statusCode(400)
                .body("apiError.error", equalTo("argument invalid"));
    }


    @Test
    void testSavePessoaException2(){
        String json = "{\"nome\":\"Alexandre Saudate\", \"dataNascimento\":\"\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas")
                .then()
                .statusCode(400)
                .body("apiError.error", equalTo("argument invalid"));

    }


    @Test
    void testUpdateFullPessoa(){
        String json = "{\"nome\":\"Alexandre Saudate\", \"dataNascimento\":\"2000-10-10\"}";
        String json2 = "{\"nome\":\"Abraham Lincom\", \"dataNascimento\":\"1809-02-12\"}";;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .contentType(ContentType.JSON)
                .body(json2)
                .put("/pessoas/1")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Abraham Lincom"))
                .body("dataNascimento", equalTo("1809-02-12"));

    }

    @Test
    void testUpdateIncrementalPessoa(){
        String json = "{\"nome\":\"Alexandre Saudate\", \"dataNascimento\":\"2000-10-10\"}";
        String json2 = "{\"nome\":\"Abraham Lincom\", \"dataNascimento\":\"\"}";;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .contentType(ContentType.JSON)
                .body(json2)
                .patch("/pessoas/1")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Abraham Lincom"))
                .body("dataNascimento", equalTo("2000-10-10"));

    }


    @Test
    void testUpdateIncrementalPessoa2(){
        String json = "{\"nome\":\"William Shakspare\", \"dataNascimento\":\"2000-10-10\"}";
        String json2 = "{\"nome\":\"\", \"dataNascimento\":\"1809-02-12\"}";;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .contentType(ContentType.JSON)
                .body(json2)
                .patch("/pessoas/1")
                .then()
                .statusCode(200)
                .body("nome", equalTo("William Shakspare"))
                .body("dataNascimento", equalTo("1809-02-12"));

    }


    @Test
    void testFindPessoa(){
        String json = "{\"nome\":\"William Shakspare\", \"dataNascimento\":\"2000-10-10\"}";
        //String json2 = "{\"nome\":\"\", \"dataNascimento\":\"1809-02-12\"}";;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .get("/pessoas/1")
                .then()
                .statusCode(200)
                .body("nome", equalTo("William Shakspare"));

    }


    @Test
    void testFindAllPessoas(){
        String json = "{\"nome\":\"William Shakspare\", \"dataNascimento\":\"2000-10-10\"}";
        String json2 = "{\"nome\":\"\", \"dataNascimento\":\"1809-02-12\"}";;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");
        given()
                .contentType(ContentType.JSON)
                .body(json2)
                .post("/pessoas");

        given()
                .get("/pessoas/all")
                .then()
                .statusCode(200)
                .body("pessoas", notNullValue());

    }


}
