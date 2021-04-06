package com.itm.legbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LegbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegbookApplication.class, args);
    }

}
