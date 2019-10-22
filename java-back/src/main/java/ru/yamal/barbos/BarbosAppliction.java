package ru.yamal.barbos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.yamal.barbos.domain.migrations.Migration;

import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class BarbosAppliction implements CommandLineRunner {

    @Autowired
    private List<Migration> dataBaseMigrations;

    public static void main(String[] args) {
        SpringApplication.run(BarbosAppliction.class, args);
    }

    @Override
    public void run(String... params) throws Exception {
        dataBaseMigrations.stream().sorted(Comparator.comparing(m -> m.getClass().getName())).forEach(Migration::migrate);
    }

}
