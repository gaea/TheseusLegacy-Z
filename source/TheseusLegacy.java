import javax.swing.*;
import java.util.*;

public class TheseusLegacy{
    Raton raton;
    Ambiente ambiente;
    private Archivo archivo;
    Vector movimientos;
    private int x=0;
    private BusquedasNoInformadas busquedasNoInformadas;
    private BusquedasInformadas busquedasInformadas;
    private int busqueda;
    private Animacion animacion;

    public TheseusLegacy(){
	archivo = new Archivo();
	Ambiente ambiente = new Ambiente(archivo.getArchivo());
	Ambiente copiaAmbiente = new Ambiente(archivo.getArchivo());

	raton = new Raton("S");
	raton.setPosicionXRaton(ambiente.getPosicionXRaton());
	raton.setPosicionYRaton(ambiente.getPosicionYRaton());

	busqueda = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la busqueda que desea que el raton realice :\n \n 1) Busqueda preferente por amplitud\n 2) Busqueda Preferente por Costo Uniforme\n 3) Busqueda Preferente por Profundidad sin Ciclos\n 4) Busqueda Preferente por Profundidad con Ciclos\n 5) Busqueda Avara\n 6) Busqueda A Estrella\n\n"));

	if(busqueda == 1){
	    busquedasNoInformadas = new BusquedasNoInformadas(raton, ambiente, "busquedaPreferenteAmplitud");
	    movimientos = busquedasNoInformadas.getMovimientos();
	}
	if(busqueda == 2){
	    busquedasNoInformadas = new BusquedasNoInformadas(raton, ambiente, "busquedaPreferenteCostoUniforme");
	    movimientos = busquedasNoInformadas.getMovimientos();
	}
	if(busqueda == 3){
	    busquedasNoInformadas = new BusquedasNoInformadas(raton, ambiente, "busquedaProfundidadSinCiclos");
	    movimientos = busquedasNoInformadas.getMovimientos();
	}

	if(busqueda == 4){
	    busquedasNoInformadas = new BusquedasNoInformadas(raton, ambiente, "busquedaProfundidadConCiclos");
	    movimientos = busquedasNoInformadas.getMovimientos();
	}

	if(busqueda == 5){
	    busquedasInformadas = new BusquedasInformadas(raton, ambiente, "busquedaAvara");
	    movimientos = busquedasInformadas.getMovimientos();
	}

	if(busqueda == 6){
	    busquedasInformadas = new BusquedasInformadas(raton, ambiente, "busquedaAStar");
	    movimientos = busquedasInformadas.getMovimientos();
	}
	
	animacion = new Animacion(movimientos, copiaAmbiente);
    }

    public static void main(String args[]){
	new TheseusLegacy();
    }
}