import java.util.*;

public class Raton{

    int posicionXRaton;
    int posicionYRaton;

    Raton movAnterior;
    Raton movDerecha;
    Raton movIzquierda;
    Raton movArriba;
    Raton movAbajo;
    String movimiento;
    int costoMovimiento;
    double fHeuristica;
    int altura;

    public Raton(String movimiento){
	this.movimiento = movimiento;
	movAnterior = null;
	movDerecha = null;
	movIzquierda = null;
	movAbajo = null;
	movArriba = null;
	costoMovimiento = 0;
	altura = 0;
	fHeuristica = 0;
    }

    public double getFheuristica(){
	return fHeuristica;
    }

    public void setfHeuristica(double f){
	fHeuristica = f;
    }

    public void setAltura(int a){
	altura = a;
    }

    public int getAltura(){
	return altura;
    }

    public void setCostoMovimiento(int costo){
	costoMovimiento = costo;
    }

    public int getCostoMovimiento(){
	return costoMovimiento;
    }

    public void setPosicionXRaton(int x){
	posicionXRaton = x;
    }

    public void setPosicionYRaton(int y){
	posicionYRaton = y;
    }

    public int getPosicionXRaton(){
	return posicionXRaton;
    }

    public int getPosicionYRaton(){
	return posicionYRaton;
    }

    public boolean sensorIzquierdo(int [][] ambiente){ // true libre
	if(posicionXRaton > 0){
	    if(ambiente[posicionYRaton][posicionXRaton-1] == 0){
		return true;
	    }
	}
	return false;
    }

    public boolean sensorDerecho(int [][] ambiente, int tamañoAmbiente){
	if(posicionXRaton < (tamañoAmbiente-1)){
	    if(ambiente[posicionYRaton][posicionXRaton+1] == 0){
		return true;
	    }
	}
	return false;
    }

    public boolean sensorAbajo(int [][] ambiente, int tamañoAmbiente){
	if(posicionYRaton < (tamañoAmbiente-1)){
	    if(ambiente[posicionYRaton+1][posicionXRaton] == 0){
		return true;
	    }
	}
	return false;
    }

    public boolean sensorArriba(int [][] ambiente){
	if(posicionYRaton > 0){
	    if(ambiente[posicionYRaton-1][posicionXRaton] == 0){
		return true;
	    }
	}
	return false;
    }

    public boolean huele_queso(int posicionXQueso, int posicionYQueso){
	if((posicionXRaton == posicionXQueso) && (posicionYRaton == posicionYQueso)){
	    return true;
	}
	return false;
    }

    public void tomarQueso(){
	System.out.println("Encontre el queso en la posicion: " + posicionXRaton + ", "+posicionYRaton);
    }

    public void moverArriba(){
	posicionYRaton--;
    }

    public void moverAbajo(){
	posicionYRaton++;
    }

    public void moverIzquierda(){
	posicionXRaton--;
    }

    public void moverDerecha(){
	posicionXRaton++;
    }

    public String getMovimiento(){
	return movimiento;
    }
    
    public Raton getMovDerecha(){
	return movDerecha;
    }

    public Raton getMovIzquierda(){
	return movIzquierda;
    }

    public Raton getMovArriba(){
	return movArriba;
    }

    public Raton getMovAbajo(){
	return movAbajo;
    }

    public Raton getMovAnterior(){
	return movAnterior;
    }

    public void setMovAnterior(Raton nodo){
	movAnterior = nodo;
    }

    public void setMovDerecha(Raton nodo){
	movDerecha = nodo;
    }

    public void setMovIzquierda(Raton nodo){
	movIzquierda = nodo;
    }

    public void setMovAbajo(Raton nodo){
	movAbajo = nodo;
    }

    public void setMovArriba(Raton nodo){
	movArriba = nodo;
    }
}