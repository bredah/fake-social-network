package com.bredah.web.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) {
    // Obtenha o status do erro
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      int statusCode = Integer.parseInt(status.toString());

      // Trate os diferentes códigos de status de erro conforme necessário
      if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
        // Lógica para tratamento do erro 401 (Unauthorized)
        return "error-401";
      } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
        // Lógica para tratamento do erro 403 (Forbidden)
        return "error-403";
      } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
        // Lógica para tratamento do erro 404 (Not Found)
        return "error-404";
      }
    }

    // Página de erro padrão para outros erros não mapeados
    return "error";
  }

}
