package org.qaddict.sqad;

import org.qaddict.sqad.data.Record;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = Record.class)
public class Sqad {

    public static void main(String[] args) {
        SpringApplication.run(Sqad.class, args);
    }

}
