package com.baseball.domain.generator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class AnswerGeneratorAdaptor implements AnswerGenerator {

    @Override
    public int generate() {
        List<Integer> randomNumbers = new Random()
                .ints(1, 9)
                .distinct()
                .limit(3)
                .boxed().toList();
        int sum = 0;
        for (int j = 0; j < randomNumbers.size(); j++) {
            sum += randomNumbers.get(j) * (Math.pow(10, randomNumbers.size() - j - 1));
        }
        return sum;
    }
}
