package com.bredah.web.requisitos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Tag;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("requisito")
public @interface RequisitoSoftware {
  String value();
}
