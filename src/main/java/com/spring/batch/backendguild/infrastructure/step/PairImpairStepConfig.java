package com.spring.batch.backendguild.infrastructure.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class PairImpairStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step pairImpairStep() {

        return stepBuilderFactory
                .get("pairImpairStep")
                .<Integer,String>chunk(1)
                .reader(pairImpairStepReader())
                .processor(pairImpairStepProcessor())
                .writer(pairImpairStepWriter())
                .build();
    }

    public IteratorItemReader<Integer> pairImpairStepReader() {
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return new IteratorItemReader<Integer>(numeros.iterator());
    }

    public FunctionItemProcessor<Integer,String> pairImpairStepProcessor() {
        return new FunctionItemProcessor<Integer,String>
                (item -> item % 2 == 0 ? String.format("Item %s é par !", item) : String.format("Item %s é ímpar !", item));
    }

    public ItemWriter<String> pairImpairStepWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
