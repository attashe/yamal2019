package ru.yamal.barbos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.yamal.barbos.domain.migrations.Migration;

import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class BarbosAppliction implements CommandLineRunner {

    @Autowired
    private List<Migration> dataBaseMigrations;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private List<Converter> converterList;

    public static void main(String[] args) {
        SpringApplication.run(BarbosAppliction.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... params) throws Exception {
        converterList.forEach(modelMapper::addConverter);
        objectMapper.registerModule(new JavaTimeModule());
        dataBaseMigrations.stream().sorted(Comparator.comparing(m -> m.getClass().getName())).forEach(Migration::migrate);
    }

}
