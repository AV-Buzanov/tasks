//Бузанов А.В 11.09.2019
//Тестовой задание №2 для Performance Lab

package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //============================================================================
        // Объявляем потоки чтения

        InputStreamReader sqr = new InputStreamReader(new FileInputStream(args[0]));
        BufferedReader dots = new BufferedReader(new FileReader(args[1]));

        // Константы для вывода сообщения, если точка:
        final String TOP = "0"; // принадлежит вершине многоугольника
        final String SIDE = "1"; // принадлежит стороне многоугольника
        final String INSIDE = "2"; // лежит внутри многоугольника
        final String OUTSIDE = "3"; // лежит за пределами многоугольника

        float[] x; //Х координаты вершин многоугольника
        float[] y; //Y координаты вершин многоугольника
        float dotX; //  Х координата точки
        float dotY; //  Y координата точки
        float det; // детерминант матрицы векторов

        int angles; // Количество углов
        int k; // номер следующей вершины
        String buffer = ""; // переменная-буфер


        //============================================================================
        // Считывем из первого файла координаты вершин многоугольника, заносим их в массивы


        while (sqr.ready()) {
            buffer = buffer.concat(String.valueOf((char) sqr.read()));

        }
        sqr.close();

        angles = buffer.split(System.getProperty("line.separator")).length;
        x = new float[angles];
        y = new float[angles];

        for (int i = 0; i < angles; i++) {
            x[i] = Float.parseFloat(buffer.split(System.getProperty("line.separator"))[i].split("\\s")[0]);
            y[i] = Float.parseFloat(buffer.split(System.getProperty("line.separator"))[i].split("\\s")[1]);
        }

        //============================================================================
        // Считывем из второго файла координаты заданных точек

        while (dots.ready())  {

            buffer=dots.readLine();

            dotX = Float.parseFloat(buffer.split("\\s")[0]);
            dotY = Float.parseFloat(buffer.split("\\s")[1]);

            for (int j = 0; j < angles; j++) {
                // задаем номер следующей вершины (если сейчас последняя, следующая - 0)
                if (j==angles-1)
                    k=0;
                else
                    k=j+1;

                // координаты точки равны координатам текущей и следующей вершин, выводим соответствующее сообщение
                // и выходим из цикла
                if ((dotX == x[j] && dotY == y[j])||
                        (dotX == x[k] && dotY == y[k])) {
                    System.out.println(TOP);
                    break;
                }


                // вычисляем детерминант матрицы векторов, напрвленных из текущей вершины в точку и из точки в следующую вершину
                // если равен 0 - вектора равны, значит точка лежит на стороне
                else if (((det=getDeterminant(dotX-x[j],dotY-y[j], x[k]-dotX, y[k]-dotY))==0.0)&&
                ((getScalarMultiple(dotX-x[j],dotY-y[j], x[k]-dotX, y[k]-dotY))>0)){
                    System.out.println(SIDE);
                    break;

                }
                // если меньше 0 - вектор повернут против часовой, значит точка лежит вне многоугольника
                else if (det<0.0){
                    System.out.println(OUTSIDE);
                    break;
                }

                // если дошли до сюда в последней вершине, значит все векторы повернуты по часовой,
                // значит точка лежит внутри многоугольника
                else if (j==angles-1)
                    System.out.println(INSIDE);
            }
        }
        dots.close();//закрываем поток
    }


    // метод нахождения детерменанта двухмерной матрицы векторов
    private static float getDeterminant(float x1, float y1, float x2, float y2){
        return ((x1*y2)-(x2*y1));
    }
    // метод нахождения скалярного произведения векторов
    private static float getScalarMultiple(float x1, float y1, float x2, float y2){
        return ((x1*x2)+(y1*y2));
    }

}