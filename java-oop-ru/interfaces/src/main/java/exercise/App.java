package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class App {
    public static List<String> buildApartmentsList(List<Home> list, int n) {
        return list.stream()
                .sorted(Comparator.comparingDouble(Home::getArea))
                .limit(n)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}