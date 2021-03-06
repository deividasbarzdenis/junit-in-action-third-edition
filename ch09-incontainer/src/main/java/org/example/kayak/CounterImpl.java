package org.example.kayak;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CounterImpl implements Counter {

    private static final String STAR = "* ";
    private static final String EMPTY = "  ";

    @Override
    public List<Frequency> countFrequency(List<Integer> numbers) {
        int maxNumber = getMax(numbers);
        List<Integer> numbersInRow = createNumberList(maxNumber);
        List<Integer> missingNumbers = getMissing(numbers, numbersInRow);

        List<Frequency> frequenciesZero = missingNumbers.stream()
                .map(number -> new Frequency(number, 0))
                .collect(Collectors.toList());

        Map<Integer, Long> frequencyMap = countByStreamGroupBy(numbers);
        List<Frequency> frequencies = frequencyMap.entrySet().stream()
                .map((frequency) -> new Frequency(frequency.getKey(), frequency.getValue().intValue()))
                .collect(Collectors.toList());

        List<Frequency> contentedFrequencies = Stream.of(frequencies, frequenciesZero)
                .flatMap(Collection :: stream)
                .collect(Collectors.toList());

        return contentedFrequencies.stream()
                .sorted(Comparator.comparingInt(Frequency::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, String> getFrequencyMap(List<Frequency> frequenciesMap) {
        Map<Integer, String> listMap = new TreeMap<>(Collections.reverseOrder());
        int count = maxFrequency(frequenciesMap);
        while (count >= 1) {
            StringBuilder line = new StringBuilder();
            for (Frequency frequency : frequenciesMap) {
                if (frequency.getFrequency() >= count) {
                    line.append(STAR);
                } else {
                    line.append(EMPTY);
                }
            }
            listMap.put(count, line.toString());
            count--;
        }
        listMap.put(0, frequenciesMap.stream()
                .map(number -> String.valueOf(number.getNumber()))
                .collect(Collectors.joining(" ")));
        return listMap;
    }

    private Integer maxFrequency(List<Frequency> frequencies) {
        return frequencies.stream()
                .mapToInt(Frequency::getFrequency)
                .max()
                .orElse(0);
    }

    private <T> Map<T, Long> countByStreamGroupBy(List<T> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(k -> k, Collectors.counting()));
    }

    private List<Integer> getMissing(List<Integer> numbers, List<Integer> inRowNumbers) {
        Set<Integer> setNumbers = new HashSet<>(numbers);
        List<Integer> differences = new ArrayList<>(inRowNumbers);
        differences.removeAll(setNumbers);
        return differences;
    }

    private int getMax(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    private List<Integer> createNumberList(int maxNumber) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= maxNumber; i++) {
            list.add(i);
        }
        return list;
    }
}
