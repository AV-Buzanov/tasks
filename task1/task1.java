//Бузанов А.В 11.09.2019
//Тестовой задание №1 для Performance Lab

package com.company;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import java.io.*;
import java.text.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        List<Double> numbers = new ArrayList<>();
        double[] num;
        double[] out;


        while (reader.ready()){
            numbers.add(Double.parseDouble(reader.readLine()));
        }
        reader.close();


        num = new  double[numbers.size()];
        for (int i = 0;i<numbers.size();i++){
            num[i]=numbers.get(i);
        }


        Percentile percentile = new Percentile();

        out = new double[5];

        out[0] = percentile.withEstimationType(Percentile.EstimationType.R_7).evaluate(num,90.0);
        out[1] = percentile.withEstimationType(Percentile.EstimationType.R_7).evaluate(num,50.0);
        out[2] = Collections.max(numbers);
        out[3] = Collections.min(numbers);
        out[4] = Arrays.stream(num).average().getAsDouble();

        DecimalFormatSymbols local = new DecimalFormatSymbols(Locale.US);
        DecimalFormat f = new DecimalFormat("#0.00",local);


        for (double dbl:out)
        System.out.println(f.format(dbl));


    }
}
