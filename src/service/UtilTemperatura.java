package service;

import java.util.ArrayList;
import java.util.List;

public class UtilTemperatura {

    private static final Double X = 1.8;
    private static final Double Y = 32.0;
    private static final Double Z = 273.15;
    private static String C, F, K;
    private static List<String> listTemp = new ArrayList<>();
    private static List<String> listTempNom = new ArrayList<>();

    public static String celsiusToFahrenheit(Double C) {
        Double r = C * X + Y;
        F = String.format("%.3f", r);
        return F;
    }

    public static String fahrenheitToCelsius(Double F) {
        Double r = (F - Y) / X;
        C = String.format("%.3f", r);
        return C;
    }

    public static String kelvinToCelsius(Double K) {
        Double r = K - Z;
        C = String.format("%.3f", r);
        return C;
    }

    public static String celsiusToKelvin(Double C) {
        Double r = C + Z;
        K = String.format("%.3f", r);
        return K;
    }

    public static String fahrenheitToKelvin(Double F) {
        Double r =  (0.55555)*(F - Y) + Z;
        K = String.format("%.3f", r);
        return K;
    }

    public static String kelvinToFahrenheit(Double K) {
        Double r = X * (K - Z) + Y;
        F = String.format("%.3f", r);
        return F;
    }

    public static List<String> getListTemperatura() {
        listTemp.add("°C - Celsius");
        listTemp.add("°K - Kelvin");
        listTemp.add("°F - Fahrenheit");
        return listTemp;
    }

    public static List<String> getListTemperaturaNom() {
        listTempNom.add("°C");
        listTempNom.add("°K");
        listTempNom.add("°F");
        return listTempNom;
    }

}
