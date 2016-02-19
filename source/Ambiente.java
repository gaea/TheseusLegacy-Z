import java.util.*;

public class Ambiente{

    private String stringAmbiente;
    private int tamanoAmbiente;
    private int ambiente [][];

    private int posicionXRaton;
    private int posicionYRaton;

    private int posicionXQueso;
    private int posicionYQueso;

    private int posicionXGato1;
    private int posicionYGato1;

    private int posicionXGato2;
    private int posicionYGato2;

    private int posicionXSalida;
    private int posicionYSalida;

    private boolean checkGato;
    private boolean ratonTieneQueso;

    public Ambiente(String stringArchivo){
	stringAmbiente = stringArchivo;
	checkGato = true;
	ratonTieneQueso = false;
	cargarAmbiente();
    }

    public void cargarAmbiente(){
	System.out.println("cargando mapa ...");
	String linea [] = stringAmbiente.split("\n");
	tamanoAmbiente = linea.length;
	ambiente = new int [tamanoAmbiente][tamanoAmbiente];
	System.out.println("tamano "+linea.length+"x"+linea.length);

	for(int i=0 ; i<linea.length; i++){
	    String caract [] = (linea[i]).split(" ");
	    for(int j=0; j<caract.length; j++ ){
		ambiente[i][j] = Integer.parseInt(caract[j]);
		System.out.print(""+ambiente[i][j]+" ");
		if(ambiente[i][j] == 4){
		    posicionXRaton = j;
		    posicionYRaton = i;
		    ambiente[i][j] = 0;
		}
		if(ambiente[i][j] == 2){
		    posicionXQueso = j;
		    posicionYQueso = i;
		    ambiente[i][j] = 0;
		}

		if(ambiente[i][j] == 5){
		    posicionXSalida = j;
		    posicionYSalida = i;
		    ambiente[i][j] = 0;
		}
		if(ambiente[i][j] == 3){
		    if(checkGato){
			posicionXGato1 = j;
			posicionYGato1 = i;
			checkGato = false;
		    }
		    else{
			posicionXGato2 = j;
			posicionYGato2 = i;
		    }
		    ambiente[i][j] = 0;
		}
	    }
	    System.out.println(" ");
	}
	System.out.println("posicion del raton; x ="+posicionXRaton+", y="+posicionYRaton);
	System.out.println("posicion del queso; x ="+posicionXQueso+", y="+posicionYQueso);
	System.out.println("posicion del gato1; x ="+posicionXGato1+", y="+posicionYGato1);
	System.out.println("posicion del gato2; x ="+posicionXGato2+", y="+posicionYGato2);
	System.out.println("posicion de la salida; x ="+posicionXSalida+", y="+posicionYSalida);
    }

    public int getCosto(int x, int y){
	if((x == posicionXGato1 && y ==  posicionYGato1) || 
	   (x == posicionXGato2 && y ==  posicionYGato2)){
	    return 12;
	}
	else{
	    if(ratonTieneQueso){
		return 2;
	    }
	    else{
		return 1;
	    }
	}
    }
    
    public void ratonTieneQueso(boolean loTiene){
	ratonTieneQueso = loTiene;
    }

    public int [][] getAmbiente(){
	return ambiente;
    }

    public int getTamanoAmbiente(){
	return tamanoAmbiente;
    }

    public int getPosicionXRaton(){
	return posicionXRaton;
    }

    public int getPosicionYRaton(){
	return posicionYRaton;
    }

    public int getPosicionXQueso(){
	return posicionXQueso;
    }

    public int getPosicionYQueso(){
	return posicionYQueso;
    }

    public void setPosicionXQueso(int x){
	posicionXQueso = x;
    }

    public void setPosicionYQueso(int y){
	posicionYQueso = y;
    }

    public int getPosicionXSalida(){
	return posicionXSalida;
    }

    public int getPosicionYSalida(){
	return posicionYSalida;
    }

    public int getPosicionXGato1(){
	return posicionXGato1;
    }

    public int getPosicionYGato1(){
	return posicionYGato1;
    }

    public int getPosicionXGato2(){
	return posicionXGato2;
    }

    public int getPosicionYGato2(){
	return posicionYGato2;
    }

    public void setArchivo(String stringArchivo){
	stringAmbiente = stringArchivo;
    }
}
