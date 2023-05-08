package com.bredah.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @RequestMapping("/")
  public String home() {
    return "Spring boot is workin";
  }
}
