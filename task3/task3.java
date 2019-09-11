//Бузанов А.В 11.09.2019
//Тестовой задание №3 для Performance Lab

package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {


        File folder = new File(args[0]);

        ArrayList<BufferedReader> readers = new ArrayList<>();
        readers.add(new BufferedReader(new FileReader(folder.getAbsolutePath()+"/Cash1.txt")));
        readers.add(new BufferedReader(new FileReader(folder.getAbsolutePath()+"/Cash2.txt")));
        readers.add(new BufferedReader(new FileReader(folder.getAbsolutePath()+"/Cash3.txt")));
        readers.add(new BufferedReader(new FileReader(folder.getAbsolutePath()+"/Cash4.txt")));
        readers.add(new BufferedReader(new FileReader(folder.getAbsolutePath()+"/Cash5.txt")));

        double[][] cashData = new double[5][16]; // данные касс (5 касс * 16 интервалов)
        double[] cashSum = new double[16]; // суммы всех касс
        double max=0.0; // для поиска максимума
        int maxIndex=0; // для поиска индекса максимума




        for (int i=0; i<16; i++) {

            cashSum[i]=0; // инициализируем сумму

            // загружаем данные касс и суммируем их
            for (int j=0; j<5; j++){
                if (readers.get(j).ready()){
                    cashData[j][i]=Double.parseDouble(readers.get(j).readLine());
                    cashSum[i]+=cashData[j][i];

                }
            }

            // ищем максимальную сумму
            if (cashSum[i]>max){
                max=cashSum[i];
                maxIndex=i;
            }
        }

        //выводим ответ
        System.out.println(maxIndex+1);


    }
}
