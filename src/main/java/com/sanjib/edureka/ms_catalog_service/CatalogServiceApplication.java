package com.sanjib.edureka.ms_catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CatalogServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

}
