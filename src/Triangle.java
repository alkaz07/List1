public class Triangle {
    double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                "}";
    }

    double perimeter(){return a+b+c;}
    double area(){
        double p=0.5*perimeter();
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public static Triangle parseTriangle(String s){
        String[] massiv = s.split(" ");
        double a = Double.parseDouble(massiv[0]);
        double b = Double.parseDouble(massiv[1]);
        double c = Double.parseDouble(massiv[2]);
        return new Triangle(a, b, c);
    }
}
