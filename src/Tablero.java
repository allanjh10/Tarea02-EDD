import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;
/**
 * CLASE DE LABERINTO
 * Esta clase va a contener va a contener una matriz que contiene toda la información de laberinto 
 * ademas va a poder generar trayectorias entre sus espacios(nodos)
 */
public class Tablero{
    //un tablero es una matriz de nodos con alto y largo
	Nodo [][] laberinto; 
	int largo;
	int alto;

    /**Constructor por parametros
     * @param n alto del tablero
     * @param m largo del tablero
    */
	public Tablero(int n, int m){
        //Se verifica que el largo y alto sean valido
        if(n < 1 || m < 1){
            System.out.println("ERROR AL CREAR LABERINTO\n| | LARGO O ALTO INVALIDO | |");
            return;
        }
        //se crea una matriz de nodo y se llena con nodos, asignandoles su coordenada
        laberinto = new Nodo[n][m];

        for(int i = 0; i<n;i++){
            for(int j = 0; j<m;j++){
                laberinto[i][j]=new Nodo(j,i);
            }
        }

        //asigna el largo y alto al tablero
        alto = n;
        largo = m;
    }

    /**
     * Este método recibe un nodo y regresa un boolean sobre si el nodo tiene vecinos
     * @param nodo
     * @return Boolean que nos dirá si el nodo con esos indices tiene vecinos desocupados
     */
    public Boolean vecinos(Nodo nodo) throws NoSuchElementException{
        //se obtienen las coordenadas del nodo
    	int n = nodo.getCoordenadaY();
    	int m = nodo.getCoordenadaX();
        //con una bandera se inicia en falso
        boolean bandera=false;
        //Se verifica el nodo sea válido
        if((n<0||n>=alto)||(m<0||m>=largo))
        throw new NoSuchElementException("INDICE NO VÁLIDO");

        //a traves de comparaciones se busca un vecino de visitar, en caso de que exista, la bandera cambia a verdadero
        if(n==0){
            bandera = bandera || !laberinto[n+1][m].getVisitado();
        }else if(n==alto-1){
            bandera = bandera || !laberinto[n-1][m].getVisitado();
        }else{
            bandera = bandera || !laberinto[n+1][m].getVisitado();
            bandera = bandera || !laberinto[n-1][m].getVisitado();
        }

        if(m==0){
            bandera = bandera || !laberinto[n][m+1].getVisitado();
        }else if(m==largo-1){
            bandera = bandera || !laberinto[n][m-1].getVisitado();
        }else{
            bandera = bandera || !laberinto[n][m+1].getVisitado();
            bandera = bandera || !laberinto[n][m-1].getVisitado();
        }

        return bandera;
    }

    /** método que duevuelve una dirección válida para avanzar dado un nodo(solo sirve si tiene vecinos sin visitar)
     * @param nodo
     * @return Int del 0 al 3 que representa las direcciónes
     */
    public int escojerDirección(Nodo nodo){
        //obtiene las coordenadas del nodo
    	int n = nodo.getCoordenadaY();
    	int m = nodo.getCoordenadaX();
        //con un random se busca una direccion a la cual avanzar
        Random num = new Random();
        //marca avanzar como falso y mientras no se pueda avanzar hacia la direccion actual, se mantiene asi 
        boolean avanzar = false;
        int direccion = 0;
        while(avanzar == false){
            direccion = num.nextInt(4);
            switch(direccion){
                //con un switch, se divide por casos segun hacia donde quiera avanzar
                case 0:
                    //si quiere avanzar hacia arriba, se verifica no sea la primera fila
                if(n==0){
                    break;
                }else {
                    //en caso de no ser, verifica que el vecino superior no haya sido visitado, en caso positivo marca avanzar como cierto
                    if(this.laberinto[n-1][m].getVisitado()){
                        break;
                    }else{
                        avanzar = true;
                        break;
                    }
                }

                case 1:
                if(n==alto-1){
                    //si quiere avanzar hacia abajo, se verifica no sea la ultila fila
                    break;
                }else {
                    //en caso de no ser, verifica que el vecino inferior no haya sido visitado, en caso positivo marca avanzar como cierto
                    if(this.laberinto[n+1][m].getVisitado()){
                        break;
                    }else{
                        avanzar = true;
                        break;
                    }
                }

                case 2:
                if(m==largo-1){
                    //si quiere avanzar hacia la derecha, se verifica no sea la ultila columna
                    break;
                }else if(this.laberinto[n][m+1].getVisitado()){
                    //en caso de no ser, verifica que el vecino derecho no haya sido visitado, en caso positivo marca avanzar como cierto
                    break;
                }else{
                    avanzar = true;
                    break;
                }

                case 3:
                if(m==0){
                    //si quiere avanzar hacia la izquierda, se verifica no sea la primera columna
                    break;
                }else if(this.laberinto[n][m-1].getVisitado()){
                    //en caso de no ser, verifica que el vecino izquierdo no haya sido visitado, en caso positivo marca avanzar como cierto
                    break;
                }else{
                    avanzar = true;
                    break;
                }
            }
        }
        return direccion;
    }


    /**CrearLabrinto
     * Crea un laberinto en la matriz original
    */
    public void CrearLabrinto(){
        //stack de nodos
        Stack bolsa = new Stack<>();
        Random num = new Random();
        int actualn= num.nextInt(alto);
        int actualm = num.nextInt(largo);
        //inicia en un nodo aleatorio
        Nodo actual =  laberinto[actualn][actualm];
        //este int representa la direccion
        Integer direc;

        //marca el nodo actual como visitado y hace push en la pila
        actual.setVisitado(true);
        bolsa.push(actual);

        //mientras la bolsa no este vacia
        while(!(bolsa.isEmpty())){

            //selecciona el tope de la pila y obtiene sus coordenadas
            actual = (Nodo) bolsa.peek();
            actualn = actual.getCoordenadaY();
            actualm = actual.getCoordenadaX();
           if(this.vecinos(actual)){
            //si el actual tiene vecinos escoge una direccion
                direc = this.escojerDirección(actual);
                switch(direc){
                    //con un switch, segun la direccion. Marca como verdadera esa direccion en el nodo actual 
                    //se mueve hacia la direccion, marca la direccion analoga de ese nodo como cierta
                    //hace push de ese nuevo nodo y lo marca como visitado
                    case 0:
                    actual.setArriba(true);
                    actualn--;
                    this.laberinto[actualn][actualm].setVisitado(true);
                    this.laberinto[actualn][actualm].setAbajo(true);
                    bolsa.push(this.laberinto[actualn][actualm]);
                    break;

                    case 1:
                    actual.setAbajo(true);
                    actualn++;
                    this.laberinto[actualn][actualm].setVisitado(true);
                    this.laberinto[actualn][actualm].setArriba(true);
                    bolsa.push(this.laberinto[actualn][actualm]);
                    break;

                    case 2:
                    actual.setDerecha(true);
                    actualm++;
                    this.laberinto[actualn][actualm].setVisitado(true);
                    this.laberinto[actualn][actualm].setIzquierda(true);
                    bolsa.push(this.laberinto[actualn][actualm]);
                    break;

                    case 3:
                    actual.setIzquierda(true);
                    actualm--;
                    this.laberinto[actualn][actualm].setVisitado(true);
                    this.laberinto[actualn][actualm].setDerecha(true);
                    bolsa.push(this.laberinto[actualn][actualm]);
                    break;
                }
        
            }else{
                //si el nodo no tiene vecinos sin visitar, hace pop
                bolsa.pop();
            }
        }
    }

    /**resolverLaberinto
     * @param int j
     * @param int i 
     * @param int k
     * @param int l
    */
    public void resolverLaberinto(int j, int i, int k, int l){
        //se crean y asignan coordenadas iniciales
        int n = j;
        int m = i;

        //Se verifica cada coordenada sea válida
        if(j<0||j>=alto||i<0||i>=largo||k<0||k>=alto||l<0||l>=largo){
            throw new NoSuchElementException("ERROR, INDICE NO VÁLIDO");
        }
        //Se actualiza el tablero
        actualizarTablero();

        //se marca el nodo de inicio, el nodo de fin y un nodo actual
        Nodo inicio = laberinto[j][i];
        Nodo fin = laberinto[k][l];
        Nodo actual = inicio;
        Stack bolsa = new Stack<>();

        //se hace push del nodo actual, se marca como visitado y no tiene solucion de entrada
        bolsa.push(actual);
        actual.setVisitado(true);
        actual.solucion(-1);

        //mientras el nodo tope de la pila sea distinto del nodo final
        while(!actual.equals(fin)){
        	//obtiene las coordenadas del nodo actual
            n = actual.getCoordenadaY();
            m = actual.getCoordenadaX();
            //busca la direccion hacia la cual avanzar
            int direccion = vecinosVisitar(actual); 
            //si la direccion es valida entra en un if - else
            if(direccion > -1){ 
                if(direccion == 0){
                    //Segun es el caso marca la solucion del nodo actual
                    actual.solucion(0);
                    //reasigna el nodo actual
                    actual = laberinto[--n][m];
                    //asigna la solucion analoga
                    actual.solucion(1);
                } else if(direccion == 1){
                    actual.solucion(1);
                    actual = laberinto[++n][m];
                    actual.solucion(0);
                actual.setVisitado(true);
                } else if(direccion == 2){
                    actual.solucion(2);
                    actual = laberinto[n][++m];
                    actual.solucion(3);
                } else if(direccion == 3){
                    actual.solucion(3);
                    actual = laberinto[n][--m];
                    actual.solucion(2);
                }
                //mete a la pila el nodo actual y lo marca como visitado
                bolsa.push(actual);
                actual.setVisitado(true);

            } else {
                //en caso de que no haya vecinos deshace todas las soluciones del nodo del tope de la pila y lo saca de la pila
                Nodo auxiliar = (Nodo) bolsa.pop();
                int aux = auxiliar.getSolucionSalida();
                auxiliar.deshacerSolucion(aux);
                aux = auxiliar.getSolucionEntrada();
                auxiliar.deshacerSolucion(aux);
                auxiliar.deshacerSolucion();
                //deshace la solucion de salida del nodo tope
                actual = (Nodo) bolsa.peek();
                //lo marca como el nuevo nodo actual
               int au = actual.getSolucionSalida();
               actual.deshacerSolucion(au);
            }
        } 
        //marca las casillas de inicio y de fin
            laberinto[j][i].setIcono('░');
            laberinto[k][l].setIcono('░');
    }



    /**vecinosVisitar
     * @param Nodo n
     * @return int direccion
    */
    private int vecinosVisitar(Nodo nodo){
        int direccion = -1;
        //se obtienen las coordenadas del nodo
        int n = nodo.getCoordenadaY();
        int m = nodo.getCoordenadaX();

        //Se verifica sea un nodo valido
        if(n < 0 || n >= alto || m < 0 || m >= largo){
            System.out.println("| |  COORDENADAS NO VÁLIDAS :) | |   ");
        }

        //con una serie de if's se verifica la direccion hacia la que puede avanzar
            if(laberinto[n][m].getArriba() == true){
                if(laberinto[--n][m].getVisitado() == false){
                    return 0;
                }else{
                    n++;
                }
            }

            //primero se verifica que la direccion hacia la que quiere avanzar este disponible
            if(laberinto[n][m].getAbajo() == true){
                //se verifica que el nodo que se encuentra en esa posicion no haya sido visitado
                if(laberinto[++n][m].getVisitado() == false ){
                    //en caso de no haber sido visitado, retorna el indice con la direccion
                    return 1;
                }else{
                    //en caso contrario, actualiza las coordenadas
                    n--;
                }
            }

            if(laberinto[n][m].getDerecha() == true){
                if(laberinto[n][++m].getVisitado() == false){
                    return 2;
                }else{
                    m--;
                }
            }

            if(laberinto[n][m].getIzquierda() == true){
                if(laberinto[n][--m].getVisitado() == false){
                    return 3;
                }else{
                    m++;
                }
            }
            //Devuelve -1 si no existian direcciones a las cuales avanzar

        return -1;
    }

    /**actualizarTablero
     * Marca todo el tablero como no visitado
    */
    private void actualizarTablero(){
        //recorre todo el tablero y marca cada casilla como no visitada
        for(int i = 0; i < alto ; i++){
            for(int j = 0; j < largo ; j++){
                laberinto[i][j].setVisitado(false);
            }
        }
    }

    /**to_String
     * @return String[]
    */
    public String[] to_String(){

        String[] cadena = new String[alto*3];
        for(int i = 0; i<alto*3; i++){
            cadena[i]="";
        }

        int k = 0;
    
        for(int i = 0; i<alto;i++){
            for(int j = 0;j<largo;j++){
                String[] aux = this.laberinto[i][j].StringCuadrito();
                cadena[k]= cadena[k]+aux[0];
                //System.out.println(cadena[k]);
                cadena[k+1]= cadena[k+1]+aux[1];
                //System.out.println(cadena[k+1]);
                cadena[k+2]= cadena[k+2]+aux[2];
                //System.out.println(cadena[k+2]);
                
            }    
            k=k+3;
    
        }
        return cadena;
    }


}