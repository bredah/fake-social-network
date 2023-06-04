package com.bredah.web.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import com.google.common.io.Resources;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MensagemControllerIT {

  @LocalServerPort
  private int port;

  @BeforeEach
  public void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }


  @Test
  public void publicarMensagemSemImagem() {

    given()
        .log().all()
        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
        .multiPart("usuario", "John Doe")
        .multiPart("conteudo", "Exemplo de mensagem")
        .when()
        .post("/mensagem")
        .then()
        .log().all()
        .statusCode(201)
        .body("usuario", equalTo("John Doe"))
        .body("conteudo", equalTo("Exemplo de mensagem"));
  }

  @Test
  public void testCriarMensagem() {
    File imageFile = new File(Resources.getResource("assets/random_image.png").getFile());

    given()
        .log().all()
        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
        .multiPart("usuario", "John Doe")
        .multiPart("conteudo", "Exemplo de mensagem")
        .multiPart("imagem", imageFile, MediaType.IMAGE_PNG_VALUE)
        .when()
          .post("/mensagem")
        .then()
          .log().all()
          .statusCode(201)
          .body("usuario", equalTo("John Doe"))
          .body("conteudo", equalTo("Exemplo de mensagem"));
  }

}
