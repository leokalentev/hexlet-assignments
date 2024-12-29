package exercise;

// BEGIN
public class App {
    public static String printSquare(Circle circle) throws NegativeRadiusException {
        try {
            var result = circle.getSquare();
            String stringResult;
            stringResult = Math.round(result) + "\nВычисление окончено";
            System.out.println(stringResult);
            return stringResult;

        } catch (NegativeRadiusException e) {
            String stringResult = e.getMessage() + "\nВычисление окончено";
            System.out.println(stringResult);
            return stringResult;
        }
    }
}
// END
