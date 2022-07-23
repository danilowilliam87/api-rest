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
        String json = "{\"nome\":\"Isaac Newton\", \"dataNascimento\":\"2000-10-10\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("nome", equalTo("Isaac Newton"));
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
        String json = "{\"nome\":\"Ambrosio de Jesus\", \"dataNascimento\":\"2000-10-10\"}";
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
        String json = "{\"nome\":\"George Washington\", \"dataNascimento\":\"2000-10-10\"}";
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
                .body("nome", notNullValue());


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
        String json = "{\"nome\":\"William Tyndale\", \"dataNascimento\":\"2000-10-10\"}";
        //String json2 = "{\"nome\":\"\", \"dataNascimento\":\"1809-02-12\"}";;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .get("/pessoas/1")
                .then()
                .statusCode(200)
                .body("nome", notNullValue());

    }


    @Test
    void testFindAllPessoas(){
        String json = "{\"nome\":\"William Shakspare da Silva\", \"dataNascimento\":\"2000-10-10\"}";
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

    @Test
    void testSaveNoBody(){
        String json = "";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas")
                .then()
                .statusCode(400);
    }


    @Test
    void testSaveEndereco(){
        String enderecoJson = "{\"logradouro\":\"planeta dos macacos\",\"cep\":\"45623-000\", \"numero\":\"10\",\"cidade\":\"salvador\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";
        String json = "{\"nome\":\"William Shakspare junior\", \"dataNascimento\":\"2000-10-10\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson)
                .post("/pessoas/enderecos")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
    @Test
    void testSaveEnderecoException(){
        String enderecoJson = "{\"logradouro\":\"planeta dos macacos\",\"cep\":\"45623-000\", \"numero\":\"10\",\"cidade\":\"salvador\", \"principal\":\"SIM\",\"pessoaId\":\"1000\"}";
        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson)
                .post("/pessoas/enderecos")
                .then()
                .statusCode(404)
                .body("message", equalTo("resource with id : 1000 not found"));
    }
    @Test
    void testSaveEnderecoException2(){
        String enderecoJson = "";
        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson)
                .post("/pessoas/enderecos")
                .then()
                .statusCode(400)
                .body("error", equalTo("\"Required request body is missing"));
    }

    @Test
    void testSaveEnderecoException3(){
        String enderecoJson = "{}";
        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson)
                .post("/pessoas/enderecos")
                .then()
                .statusCode(400)
                .body("apiError.message", equalTo("Validation failed for argument"));
    }


    @Test
    void testfindAllEnderecoByPessoa(){
        String enderecoJson = "{\"logradouro\":\"planeta dos macacos\",\"cep\":\"45623-000\", \"numero\":\"10\",\"cidade\":\"salvador\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";
        String enderecoJson2 = "{\"logradouro\":\"AlphaVela\",\"cep\":\"45623-000\", \"numero\":\"666\",\"cidade\":\"Sao Paulo\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";

        String json = "{\"nome\":\"William Shakspare dos Santos\", \"dataNascimento\":\"2000-10-10\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson)
                .post("/pessoas/enderecos");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson2)
                .post("/pessoas/enderecos");

        given()
                .contentType(ContentType.JSON)
                .get("pessoas/1/enderecos")
                .then()
                .statusCode(200)
                .body("enderecos", notNullValue());
    }

    @Test
    void testPrincipalOption(){
        String enderecoJson = "{\"logradouro\":\"planeta dos macacos\",\"cep\":\"45623-000\", \"numero\":\"10\",\"cidade\":\"salvador\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";
        String enderecoJson2 = "{\"logradouro\":\"AlphaVela\",\"cep\":\"45623-000\", \"numero\":\"666\",\"cidade\":\"Sao Paulo\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";
        String json = "{\"nome\":\"William Shakspare neto\", \"dataNascimento\":\"2000-10-10\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson)
                .post("/pessoas/enderecos");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson2)
                .post("/pessoas/enderecos");

        given()
                .contentType(ContentType.JSON)
                .get("pessoas/1/enderecos")
                .then()
                .statusCode(200)
                .body("[0].logradouro", equalTo("planeta dos macacos"));
    }

    @Test
    void testPrincipal(){
        String enderecoJson = "{\"logradouro\":\"planeta dos macacos\",\"cep\":\"45623-000\", \"numero\":\"10\",\"cidade\":\"salvador\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";
        String enderecoJson2 = "{\"logradouro\":\"AlphaVela\",\"cep\":\"45623-000\", \"numero\":\"666\",\"cidade\":\"Sao Paulo\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";
        String enderecoJson3 = "{\"logradouro\":\"Rua jardim baiano\",\"cep\":\"45623-000\", \"numero\":\"666\",\"cidade\":\"Salvador\", \"principal\":\"SIM\",\"pessoaId\":\"1\"}";

        String json = "{\"nome\":\"Harvey Specter \", \"dataNascimento\":\"2000-10-10\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/pessoas");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson)
                .post("/pessoas/enderecos");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson2)
                .post("/pessoas/enderecos");
        given()
                .contentType(ContentType.JSON)
                .body(enderecoJson3)
                .post("/pessoas/enderecos");


        given()
                .contentType(ContentType.JSON)
                .put("pessoas/enderecos?enderecoId=3&pessoaId=1")
                .then()
                .statusCode(200);


        given()
                .contentType(ContentType.JSON)
                .get("pessoas/1/enderecos")
                .then()
                .statusCode(200)
                .body("[2].principal", notNullValue());


    }


}
