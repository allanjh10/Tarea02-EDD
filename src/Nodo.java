/**Clase Nodo
*/
public class Nodo{
    //Coordenadas de un nodo
	private int coordenadaY;
	private int coordenadaX;
    //Booleano para saber si el nodo ha sido visitado
	private boolean visitado;
    //booleanos que indican si el nodo puede avanzar hacia cierta dirección
	private boolean arriba;
	private boolean abajo;
	private boolean derecha;
	private boolean izquierda;
    //Enteros que indican hacia que dirección avanza al resolverse
	private Integer solucionEntrada;
	private Integer solucionSalida;
    //Matriz del nodo para su String
	private char[][] cuadrito = new char[3][3] ;

    /**Constructor por parámetros
     * @param int x, coordenada que ocupa en la matriz del tablero
     * @param int y, coordenada que ocupa en la matriz del tablero
    */
	public Nodo(int x,int y){
        //Un nodo inicialmente no ha sido visitado
		visitado = false;

        coordenadaX = x;
        coordenadaY = y;
        //Un nodo por defecto no puede avanzar y tiene todas sus paredes bloqueadas
        arriba = false;
        abajo = false;
        derecha = false;
        izquierda = false;

        cuadrito[0][0]='█';
        cuadrito[0][1]='█';
        cuadrito[0][2]='█';
        cuadrito[1][0]='█';
        cuadrito[1][1]=' ';
        cuadrito[1][2]='█';
        cuadrito[2][0]='█';
        cuadrito[2][1]='█';
        cuadrito[2][2]='█';
	}

    /**getCoordenadaY
     * @return coordenadaY
    */
	public int getCoordenadaY(){
        return coordenadaY;
    }

    /**getCoordenadaX
     * @return coordenadaX
    */
    public int getCoordenadaX(){
        return coordenadaX;
    }

    /**getArriba
     * @return booleano sobre si se puede o no pasar arriba
    */
    public boolean getArriba(){
    	return arriba;
    }

    /**getAbajo
     * @return booleano sobre si se puede o no pasar abajo
    */
    public boolean getAbajo(){
    	return abajo;
    }

    /**getIzquierda
     * @return booleano sobre si se puede o no pasar a la izquierda
    */
    public boolean getIzquierda(){
    	return izquierda;
    }

    /**getDerecha
     * @return booleano sobre si se puede o no pasar a la derecha
    */
    public boolean getDerecha(){
    	return derecha;
    }

    /**getVisitado
     * @return booleano sobre si el nodo ha sido visitado
    */
    public  boolean getVisitado(){
        return visitado;
    }

    /**getSolucionEntrada
     * @return entero con la dirección de entrada en la solucion del laberinto
    */
    public int getSolucionEntrada(){
        if(solucionEntrada!= null){
            return solucionEntrada;
        }
        return -1;
    } 

    /**getSolucionSalida
     * @return entero con la dirección de salida en la solucion del laberinto
    */
    public int getSolucionSalida(){
        if(solucionSalida!= null){
            return solucionSalida;
        }
        return -1;
    }

    /**setIcono
     * @param nuevo centro del nodo
    */
    public void setIcono(char c){
        cuadrito[1][1]= c;
    }

    /**setVisitado
     * @param boolean visitado
    */
    public  void setVisitado(boolean visitado){
        this.visitado = visitado;
    }

    /**setCoordenadaX
     * @param coordenada x
    */
    public void setCoordenadaX(int x){
        coordenadaX = x;
    }

    /**setCoordenadaY
     * @param coordenada y
    */
    public void setCoordenadaY(int y){
        coordenadaY = y;
    }

    /**setArriba
     * @param booleano arriba
    */
    public void setArriba(boolean arriba){
        this.arriba = arriba;
        if(arriba){
        	cuadrito[0][1]=' ';
        }
    }

    /**setAbajo
     * @param booleano abajo
    */
    public void setAbajo(boolean abajo){
        this.abajo = abajo;
        if(abajo){
        	cuadrito[2][1]=' ';
        }
    }

    /**setDerecha
     * @param booleano derecha
    */
    public void setDerecha(boolean derecha){
        this.derecha = derecha;
        if(derecha){
        	cuadrito[1][2]=' ';
        }
    }


    /**setIzquierda
     * @param booleano izquierda
    */
    public void setIzquierda(boolean izquierda){
        this.izquierda = izquierda;
        if(izquierda){
        	cuadrito[1][0]=' ';
        }
    }


    /**solucion
     * @param int direccion 
     * direccion hacia la que el nodo se mueve en la matriz general
    */
    public void solucion(int direccion){
        //marca el centro del nodo como parte de la solución
        cuadrito[1][1]='X';
        
        //verifica si la solucion es de entrada o de salida
        if(solucionEntrada == null){
            solucionEntrada = direccion;
        } else {
            solucionSalida = direccion;
        }

        //dependiendo de la direccion, marca una casilla de la matriz del nodo como parte del recorrido de la solución
        if(direccion == 0)
        cuadrito[0][1]='X';

        if(direccion == 1)
        cuadrito[2][1]='X';

        if(direccion == 2)
        cuadrito[1][2]='X';

        if(direccion == 3)
        cuadrito[1][0]='X';

    }


    /**deshacerSolucion
     * @param int con la direccion
    */
    public void deshacerSolucion(int direccion){
        //Segun la direccion que se ingrese, en el nodo se vuelve a marcar el tunel 
        if(direccion == 0)
        cuadrito[0][1]=' ';

        if(direccion == 1)
        cuadrito[2][1]=' ';

        if(direccion == 2)
        cuadrito[1][2]=' ';

        if(direccion == 3)
        cuadrito[1][0]=' ';
    }

    /**deshacerSolucion
     * Marca el centro del nodo como libre
    */
    public void deshacerSolucion(){
        cuadrito[1][1]=' ';
    }

    /**equals
     * @param Nodo n 
     * @return booleano sobre si son iguales o no
    */
    public boolean equals(Nodo n){
        //dos nodos son iguales si están en la misma coordenada de la matriz general
    	return this.coordenadaY == n.coordenadaY && this.coordenadaX==n.coordenadaX;
    }

    /**StringCuadrito
     * @return [] String
     * Para el toString del nodo se tiene que iterar sobre su matriz
    */
    public String[] StringCuadrito(){
        String[] cadena = new String[3];
        for(int i = 0;i<3;i++){
            cadena[i] = ""; 
        }
   
        cadena[0]= cadena[0]+this.cuadrito[0][0]+this.cuadrito[0][1]+this.cuadrito[0][2];
        cadena[1]=cadena[1]+this.cuadrito[1][0]+this.cuadrito[1][1]+this.cuadrito[1][2];
        cadena[2]=cadena[2]+this.cuadrito[2][0]+this.cuadrito[2][1]+this.cuadrito[2][2];

        return cadena;  
    }

}