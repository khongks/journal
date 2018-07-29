package com.apress.spring;

import com.apress.spring.domain.Journal;
import com.apress.spring.repository.JournalRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootJournalApplication {

    @Bean
    InitializingBean saveData(JournalRepository repo){
        return () -> {
            repo.save(new Journal("Get to know Spring Boot","Today I will learn Spring Boot","01/01/2016"));
            repo.save(new Journal("Simple Spring Boot Project","I will do my first Spring Boot Project","01/02/2016"));
            repo.save(new Journal("Spring Boot Reading","Read more about Spring Boot","02/01/2016"));
            repo.save(new Journal("Spring Boot in the Cloud","Spring Boot using Cloud Foundry","03/01/2016"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJournalApplication.class, args);
        /*
        SpringApplication app = new SpringApplication(SpringBootJournalApplication.class);
        app.setBanner(new Banner() {
          @Override
          public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                out.println(" ____    ____               _____                                         __         _                        ");
                out.println("|_   \\  /   _|             |_   _|                                       [  |       / \\                      ");
                out.println("  |   \\/   |    _   __       | |  .--.   __   _   _ .--.  _ .--.   ,--.   | |      / _ \\    _ .--.   _ .--.  ");
                out.println("  | |\\  /| |   [ \\ [  ]  _   | |/ .'`\\ \\ [ | | | [ `/'`\\][ `.-. | `'_\\ :  | |     / ___ \\  [ '/'`\\ \\[ '/'`\\ \\");
                out.println(" _| |_\\/_| |_   \\ '/ /  | |__' || \\__. | | \\_/ |, | |     | | | | // | |, | |   _/ /   \\ \\_ | \\__/ | | \\__/ |");
                out.println("|_____||_____|[\\_:  /   `.____.' '.__.'  '.__.'_/[___]   [___||__]\\'-;__/[___] |____| |____|| ;.__/  | ;.__/ ");
                out.println("               \\__.'                                                                       [__|     [__|     ");  
            }
        });
        app.run(args);
        */
    }
}