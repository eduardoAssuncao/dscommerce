package br.gov.ma.tce.dscommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DscommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DscommerceApplication.class, args);
    }
    //Devemos comecar a contruir um entidade que tem o muitos do outro lado do relacionamento. ex: 1 para *, * para * ...
    //Apos isso, devemos implementar a relacionamento de para 1 antes do para muitos
}
