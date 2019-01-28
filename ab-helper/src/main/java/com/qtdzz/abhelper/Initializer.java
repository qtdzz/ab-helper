package com.qtdzz.abhelper;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import java.util.Set;

import org.slf4j.LoggerFactory;

@HandlesTypes(IABStrategy.class)
public class Initializer implements ServletContainerInitializer {
  public void onStartup(Set<Class<?>> set, ServletContext servletContext)
      throws ServletException {
    LoggerFactory.getLogger(Initializer.class).info("Hello, world!=========");
    System.out.println("================ Hello world====================");
  }
}
