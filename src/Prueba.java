import java.util.Scanner;
public class Prueba {
    public static void main(String[] args) {

        int m = 0,n = 0;
        Scanner sc = new Scanner(System.in);
        while(n==0||m==0){
            System.out.println("Ingresa el largo del tablero (Toma en cuenta que cada casilla ocupa un espacio de 3x3)");
             m = sc.nextInt();
            System.out.println("Ingresa el alto del tablero (Toma en cuenta que cada casilla ocupa un espacio de 3x3)");
             n = sc.nextInt();
        }

        System.out.println("TABLERO INICIAL\n\n");
        Tablero nuevo = new Tablero(n,m);
        String[] cad = nuevo.to_String();
        for(int i = 0 ; i<cad.length;i++){
            System.out.println(cad[i]);
        }

        System.out.println("\nElige una coordenada Y de inicio en el tablero\nLos valores deben de cumplir que sean mayores o iguales a 0 y menores a "+n );
        int a = sc.nextInt();
        System.out.println("Elige una coordenada X de inicio en el tablero\nLos valores deben de cumplir que sean mayores o iguales a 0 y menores a "+m);        
        int b = sc.nextInt();
        System.out.println("Elige una coordenada Y de fin en el tablero\nLos valores deben de cumplir que sean mayores o iguales a 0 y menores a "+n );
        int c = sc.nextInt();
        System.out.println("Elige una coordenada X de fin en el tablero\nLos valores deben de cumplir que sean mayores o iguales a 0 y menores a "+m);        
        int d = sc.nextInt();

        System.out.println("\n\nLABERINTO CREADO\n\n");
        nuevo.CrearLabrinto();
        String[] cadena = nuevo.to_String();

        for(int i = 0 ; i<cadena.length;i++){
            System.out.println(cadena[i]);
        }

        System.out.println("\n\nLABERINTO RESUELTO\n\n");
        nuevo.resolverLaberinto(a,b,c,d);
        String[] hola = nuevo.to_String();

        for(int i = 0 ; i<hola.length;i++){
            System.out.println(hola[i]);
        }

    }
}