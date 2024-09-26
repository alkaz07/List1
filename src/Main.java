import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //exampleWrite();
        //exampleRead();
        exampleRead2();
    }

    private static void exampleWrite() {
        List<Triangle> triangles = makeTriangles();
        printTriangles(triangles);
        writeTrianglesToTxt(triangles, "output.txt");
        writeTriangles2(triangles, "output2.txt");
    }

    private static void exampleRead() {
        List<Triangle> triangles = readTriangles("output2.txt");
        printTriangles(triangles);
        //суммарная площадь:
        double totalArea = getTotalArea(triangles);
        System.out.println(totalArea);
        //мксимальная площадь:
        double maxArea = triangles.stream()
                                    .max(Comparator.comparingDouble(Triangle::area))
                                    .get().area();
        System.out.println("максимальная площадь "+maxArea);
    }

    private static void exampleRead2() {
        List<Triangle> triangles = readTriangles2("output2.txt");
        printTriangles(triangles);
    }

    private static double getTotalArea(List<Triangle> triangles) {
        double totalArea=0;
        for (Triangle trr: triangles)
            totalArea += trr.area();
        return totalArea;
    }

    public static void printTriangles(List<Triangle> triangles){
        for (Triangle trr: triangles)
            System.out.println(trr);
    }

    public static void writeTrianglesToTxt(List<Triangle> triangles, String filename){
        try{
            FileWriter fileWriter = new FileWriter(filename); //открыть файл на запись
            //перебор элементов списка с целью записи
            for (Triangle trr: triangles)
                fileWriter.write(trr.toString()+"\n");
            //завершить запись. закрыть файл
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("что-то не так с записью в файл");
            System.out.println(e.getMessage());
        }
    }

    public static void writeTriangles2(List<Triangle> triangles, String filename){
        //в первой строке файла пусть будет количество треугольников
        //в каждой следующей строке файла пусть будут 3 числа, разделенные пробелами - строны очередного треугольбника
        try{
            FileWriter fileWriter = new FileWriter(filename);  //открыть файл на запись
            //перебор элементов списка с целью записи
            fileWriter.write(String.valueOf(triangles.size()));
            for (Triangle trr: triangles){
                fileWriter.write("\n"+trr.a+" "+ trr.b+" "+ trr.c);
            }
            //завершить запись. закрыть файл
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("что-то не так с записью в файл");
            System.out.println(e.getMessage());
        }
    }

    public static List<Triangle> readTriangles2(String fname){
        List<Triangle> triangleList = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(fname));
            int k = scanner.nextInt();
            scanner.nextLine(); //закончить с первой строкой
            for (int i = 0; i < k && scanner.hasNext(); i++) {
                String s = scanner.nextLine();
                Triangle trrr = Triangle.parseTriangle(s);
                triangleList.add(trrr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("что-то не так с чтением из файла");
            System.out.println(e.getMessage());
        }
        return triangleList;
    }

    public static List<Triangle> readTriangles(String fname){
        List<Triangle> triangleList = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(fname));
            int k = scanner.nextInt();
            for (int i = 0; i < k && scanner.hasNext(); i++) {
              //  String wordA = scanner.next();
                double a = Double.parseDouble(scanner.next());
                double b = Double.parseDouble(scanner.next());
                double c = Double.parseDouble(scanner.next());
                Triangle trrr = new Triangle(a, b, c);
                triangleList.add(trrr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("что-то не так с чтением из файла");
            System.out.println(e.getMessage());
        }
        return triangleList;
    }

    public static List<Triangle> makeTriangles(){
        List<Triangle> triangleList = new ArrayList<>();
        triangleList.add(new Triangle(3, 4, 5));
        triangleList.add(new Triangle(10, 10, 10));
        triangleList.add(0, new Triangle(11, 12, 13));
        triangleList.add(new Triangle(4, 4, 3.8));
        return triangleList;
    }
}