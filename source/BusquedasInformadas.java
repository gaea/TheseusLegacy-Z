import java.util.*;

public class BusquedasInformadas{

    private Ambiente ambiente;
    private Raton movRaton;
    private Vector movimientos;
    private Vector movimientos1;
    private Vector movimientos2;
    private Vector cola;
    private int nodosExpandidos=0;
    private int cantidadnodos=0;
    private int fase=0;
    private long startTime;
    private long finishTime;
    private int altura=0;

    int x =0;

    public BusquedasInformadas(Raton movRaton, Ambiente ambiente, String busqueda){
	this.ambiente = ambiente;
	this.movRaton = movRaton;

	movRaton.setfHeuristica(manhattan(movRaton));

	movimientos = new Vector();
	movimientos1 = new Vector();
	movimientos2 = new Vector();
	cola = new Vector();
	cola.add(movRaton);

	startTime = System.nanoTime();
	if(busqueda == "busquedaAvara"){
	    busquedaAvara(this.movRaton);
	}
	if(busqueda == "busquedaAStar"){
	    busquedaAStar(this.movRaton);
	}
	if(busqueda == "busquedaAvaraMango"){
	    busquedaAvaraMango(this.movRaton);
	}
	if(busqueda == "busquedaAStarMango"){
	    busquedaAStarMango(this.movRaton);
	}
	finishTime = System.nanoTime();

	System.out.println("tiempo en nano segundos : " + (finishTime - startTime));
	System.out.println("todos los movimientos: ");
	System.out.print(movimientos1.toString()+" "+movimientos2.toString() );
	System.out.println("\nFin de " + busqueda);
    }

    public void busquedaAvara(Raton movRaton){
	    
	    if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso())) && x < 400){
		cola.remove(0);
		nodosExpandidos++;
		x++;
		if(movRaton.sensorDerecho(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movDer.setfHeuristica(manhattan(movDer));

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);
			insertarEnCola(movDer);
			cantidadnodos++;
		    }
		    else{
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movDer.setfHeuristica(manhattan(movDer));

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);

			//if(!checkBucle(movDer)){
			    insertarEnCola(movDer);
			    cantidadnodos++;
			/*}
			else{
			    movDer = null;
			}*/
		    }
		}

		if(movRaton.sensorAbajo(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura( movRaton.getAltura() + 1 );
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movAba.setfHeuristica(manhattan(movAba));

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);
			insertarEnCola(movAba);
			cantidadnodos++;
		    }
		    else{
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura( movRaton.getAltura() + 1 );
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movAba.setfHeuristica(manhattan(movAba));

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);

			//if(!checkBucle(movAba)){
			    insertarEnCola(movAba);
			    cantidadnodos++;
			/*}
			else{
			    movAba = null;
			}*/
		    }
		}

		if(movRaton.sensorIzquierdo(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			movIzq.setAltura( movRaton.getAltura() + 1 );
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movIzq.setfHeuristica(manhattan(movIzq));

			movIzq.setMovAnterior(movRaton);
			movRaton.setMovIzquierda(movIzq);
			insertarEnCola(movIzq);
			cantidadnodos++;
		    }
		    else{
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			movIzq.setAltura( movRaton.getAltura() + 1 );
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movIzq.setfHeuristica(manhattan(movIzq));

			movIzq.setMovAnterior(movRaton);

			//if(!checkBucle(movIzq)){
			    insertarEnCola(movIzq);
			    cantidadnodos++;
			/*}
			else{
			    movIzq = null;
			}*/
		    }
		}

		if(movRaton.sensorArriba(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			movArr.setAltura( movRaton.getAltura() + 1 );
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movArr.setfHeuristica(manhattan(movArr));

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);
			insertarEnCola(movArr);
			cantidadnodos++;
		    }
		    else{
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			movArr.setAltura( movRaton.getAltura() + 1 );
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movArr.setfHeuristica(manhattan(movArr));

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);

			//if(!checkBucle(movArr)){
			    insertarEnCola(movArr);
			    cantidadnodos++;
			/*}
			else{
			    movArr = null;
			}*/
		    }
		}

		//cola.remove(0);
		if(cola.size()>0){
		    busquedaAvara((Raton)cola.get(0));
		}
	    }
	    else{
		recuperarCamino(movRaton);
		movRaton.tomarQueso();

		if(fase == 0){
		    fase = 1;
		    Raton nuevoRaton = new Raton("S");
		    nuevoRaton.setPosicionXRaton(movRaton.getPosicionXRaton());
		    nuevoRaton.setPosicionYRaton(movRaton.getPosicionYRaton());
		    nuevoRaton.setAltura(movRaton.getAltura());
		    movRaton=null;
		    cola.removeAllElements();
		    cola.add(nuevoRaton);
		    ambiente.setPosicionXQueso(ambiente.getPosicionXSalida());
		    ambiente.setPosicionYQueso(ambiente.getPosicionYSalida());
		    busquedaAvara((Raton)cola.get(0));
		}
	    }
    }

    public void busquedaAvaraMango(Raton movRaton){
	    
	    if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso())) && x < 400){
		cola.remove(0);
		nodosExpandidos++;
		x++;
		if(movRaton.sensorDerecho(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movDer.setfHeuristica(mango(movDer));

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);
			insertarEnCola(movDer);
			cantidadnodos++;
		    }
		    else{
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movDer.setfHeuristica(mango(movDer));

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);

			//if(!checkBucle(movDer)){
			    insertarEnCola(movDer);
			    cantidadnodos++;
			/*}
			else{
			    movDer = null;
			}*/
		    }
		}

		if(movRaton.sensorAbajo(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura( movRaton.getAltura() + 1 );
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movAba.setfHeuristica(mango(movAba));

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);
			insertarEnCola(movAba);
			cantidadnodos++;
		    }
		    else{
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura( movRaton.getAltura() + 1 );
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movAba.setfHeuristica(mango(movAba));

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);

			//if(!checkBucle(movAba)){
			    insertarEnCola(movAba);
			    cantidadnodos++;
			/*}
			else{
			    movAba = null;
			}*/
		    }
		}

		if(movRaton.sensorIzquierdo(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			movIzq.setAltura( movRaton.getAltura() + 1 );
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movIzq.setfHeuristica(mango(movIzq));

			movIzq.setMovAnterior(movRaton);
			movRaton.setMovIzquierda(movIzq);
			insertarEnCola(movIzq);
			cantidadnodos++;
		    }
		    else{
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			movIzq.setAltura( movRaton.getAltura() + 1 );
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movIzq.setfHeuristica(mango(movIzq));

			movIzq.setMovAnterior(movRaton);

			//if(!checkBucle(movIzq)){
			    insertarEnCola(movIzq);
			    cantidadnodos++;
			/*}
			else{
			    movIzq = null;
			}*/
		    }
		}

		if(movRaton.sensorArriba(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			movArr.setAltura( movRaton.getAltura() + 1 );
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movArr.setfHeuristica(mango(movArr));

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);
			insertarEnCola(movArr);
			cantidadnodos++;
		    }
		    else{
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			movArr.setAltura( movRaton.getAltura() + 1 );
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movArr.setfHeuristica(mango(movArr));

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);

			//if(!checkBucle(movArr)){
			    insertarEnCola(movArr);
			    cantidadnodos++;
			/*}
			else{
			    movArr = null;
			}*/
		    }
		}

		//cola.remove(0);
		if(cola.size()>0){
		    busquedaAvara((Raton)cola.get(0));
		}
	    }
	    else{
		recuperarCamino(movRaton);
		movRaton.tomarQueso();

		if(fase == 0){
		    fase = 1;
		    Raton nuevoRaton = new Raton("S");
		    nuevoRaton.setPosicionXRaton(movRaton.getPosicionXRaton());
		    nuevoRaton.setPosicionYRaton(movRaton.getPosicionYRaton());
		    nuevoRaton.setAltura(movRaton.getAltura());
		    movRaton=null;
		    cola.removeAllElements();
		    cola.add(nuevoRaton);
		    ambiente.setPosicionXQueso(ambiente.getPosicionXSalida());
		    ambiente.setPosicionYQueso(ambiente.getPosicionYSalida());
		    busquedaAvara((Raton)cola.get(0));
		}
	    }
	}

    public void busquedaAStar(Raton movRaton){
	    
	    if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso())) ){
		x++;
		cola.remove(0);
		nodosExpandidos++;
		if(movRaton.sensorIzquierdo(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			movIzq.setAltura( movRaton.getAltura() + 1 );
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movIzq.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movIzq.getPosicionXRaton(), movIzq.getPosicionYRaton()));
			//System.out.println(movIzq.getCostoMovimiento());
			movIzq.setfHeuristica(manhattan(movIzq) + movIzq.getCostoMovimiento());

			movIzq.setMovAnterior(movRaton);
			movRaton.setMovIzquierda(movIzq);
			insertarEnCola(movIzq);
			cantidadnodos++;
		    }
		    else{
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			
			movIzq.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movIzq.getPosicionXRaton(), movIzq.getPosicionYRaton()));
			movIzq.setfHeuristica(manhattan(movIzq) + movIzq.getCostoMovimiento());

			movIzq.setMovAnterior(movRaton);

			if(!checkBucle(movIzq)){
			    movIzq.setAltura( movRaton.getAltura() + 1 );
			    if(movIzq.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movIzq);
			    cantidadnodos++;
			}
			else{
			    movIzq = null;
			}
		    }
		}

		if(movRaton.sensorDerecho(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movDer.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movDer.getPosicionXRaton(), movDer.getPosicionYRaton()));
			//System.out.println(movDer.getCostoMovimiento());
			movDer.setfHeuristica(manhattan(movDer) + movDer.getCostoMovimiento());

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);
			insertarEnCola(movDer);
			cantidadnodos++;
		    }
		    else{
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			
			movDer.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movDer.getPosicionXRaton(), movDer.getPosicionYRaton()));
			//System.out.println(movDer.getCostoMovimiento());
			movDer.setfHeuristica(manhattan(movDer) + movDer.getCostoMovimiento());

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);

			if(!checkBucle(movDer)){
			    movDer.setAltura( movRaton.getAltura() + 1 );
			    if(movDer.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movDer);
			    cantidadnodos++;
			}
			else{
			    movDer = null;
			}
		    }
		}

		if(movRaton.sensorArriba(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			movArr.setAltura( movRaton.getAltura() + 1 );
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movArr.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movArr.getPosicionXRaton(), movArr.getPosicionYRaton()));
			movArr.setfHeuristica(manhattan(movArr) + movArr.getCostoMovimiento());

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);
			insertarEnCola(movArr);
			cantidadnodos++;
		    }
		    else{
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			
			movArr.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movArr.getPosicionXRaton(), movArr.getPosicionYRaton()));
			movArr.setfHeuristica(manhattan(movArr) + movArr.getCostoMovimiento());

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);

			if(!checkBucle(movArr)){
			    movArr.setAltura( movRaton.getAltura() + 1 );
			    if(movArr.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movArr);
			    cantidadnodos++;
			}
			else{
			    movArr = null;
			}
		    }
		}

		if(movRaton.sensorAbajo(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura( movRaton.getAltura() + 1 );
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movAba.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movAba.getPosicionXRaton(), movAba.getPosicionYRaton()));
			movAba.setfHeuristica(manhattan(movAba) + movAba.getCostoMovimiento());

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);
			insertarEnCola(movAba);
			cantidadnodos++;
		    }
		    else{
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			
			movAba.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movAba.getPosicionXRaton(), movAba.getPosicionYRaton()));
			movAba.setfHeuristica(manhattan(movAba) + movAba.getCostoMovimiento());

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);

			if(!checkBucle(movAba)){
			    movAba.setAltura( movRaton.getAltura() + 1 );
			    if(movAba.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movAba);
			    cantidadnodos++;
			}
			else{
			    movAba = null;
			}
		    }
		}
		//cola.remove(0);
		if(cola.size()>0){
		    busquedaAStar((Raton)cola.get(0));
		}
	    }
	    else{
		recuperarCamino(movRaton);
		movRaton.tomarQueso();
		ambiente.ratonTieneQueso(true);

		if(fase == 0){
		    fase = 1;
		    Raton nuevoRaton = new Raton("S");
		    nuevoRaton.setPosicionXRaton(movRaton.getPosicionXRaton());
		    nuevoRaton.setPosicionYRaton(movRaton.getPosicionYRaton());
		    nuevoRaton.setAltura(movRaton.getAltura());
		    movRaton=null;
		    cola.removeAllElements();
		    cola.add(nuevoRaton);
		    ambiente.setPosicionXQueso(ambiente.getPosicionXSalida());
		    ambiente.setPosicionYQueso(ambiente.getPosicionYSalida());
		    busquedaAStar((Raton)cola.get(0));
		}
	    }
    }

    public void busquedaAStarMango(Raton movRaton){
	    
	    if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso())) ){
		x++;
		cola.remove(0);
		nodosExpandidos++;
		if(movRaton.sensorIzquierdo(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			movIzq.setAltura( movRaton.getAltura() + 1 );
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movIzq.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movIzq.getPosicionXRaton(), movIzq.getPosicionYRaton()));
			//System.out.println(movIzq.getCostoMovimiento());
			movIzq.setfHeuristica(mango(movIzq) + movIzq.getCostoMovimiento());

			movIzq.setMovAnterior(movRaton);
			movRaton.setMovIzquierda(movIzq);
			insertarEnCola(movIzq);
			cantidadnodos++;
		    }
		    else{
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			
			movIzq.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movIzq.getPosicionXRaton(), movIzq.getPosicionYRaton()));
			movIzq.setfHeuristica(mango(movIzq) + movIzq.getCostoMovimiento());

			movIzq.setMovAnterior(movRaton);

			if(!checkBucle(movIzq)){
			    movIzq.setAltura( movRaton.getAltura() + 1 );
			    if(movIzq.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movIzq);
			    cantidadnodos++;
			}
			else{
			    movIzq = null;
			}
		    }
		}

		if(movRaton.sensorDerecho(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movDer.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movDer.getPosicionXRaton(), movDer.getPosicionYRaton()));
			//System.out.println(movDer.getCostoMovimiento());
			movDer.setfHeuristica(mango(movDer) + movDer.getCostoMovimiento());

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);
			insertarEnCola(movDer);
			cantidadnodos++;
		    }
		    else{
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			

			movDer.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movDer.getPosicionXRaton(), movDer.getPosicionYRaton()));
			//System.out.println(movDer.getCostoMovimiento());
			movDer.setfHeuristica(mango(movDer) + movDer.getCostoMovimiento());

			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);

			if(!checkBucle(movDer)){
			    movDer.setAltura( movRaton.getAltura() + 1 );
			    if(movDer.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movDer);
			    cantidadnodos++;
			}
			else{
			    movDer = null;
			}
		    }
		}

		if(movRaton.sensorArriba(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			movArr.setAltura( movRaton.getAltura() + 1 );
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movArr.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movArr.getPosicionXRaton(), movArr.getPosicionYRaton()));
			movArr.setfHeuristica(mango(movArr) + movArr.getCostoMovimiento());

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);
			insertarEnCola(movArr);
			cantidadnodos++;
		    }
		    else{
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			
			movArr.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movArr.getPosicionXRaton(), movArr.getPosicionYRaton()));
			movArr.setfHeuristica(mango(movArr) + movArr.getCostoMovimiento());

			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);

			if(!checkBucle(movArr)){
			    movArr.setAltura( movRaton.getAltura() + 1 );
			    if(movArr.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movArr);
			    cantidadnodos++;
			}
			else{
			    movArr = null;
			}
		    }
		}

		if(movRaton.sensorAbajo(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura( movRaton.getAltura() + 1 );
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}

			movAba.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movAba.getPosicionXRaton(), movAba.getPosicionYRaton()));
			movAba.setfHeuristica(mango(movAba) + movAba.getCostoMovimiento());

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);
			insertarEnCola(movAba);
			cantidadnodos++;
		    }
		    else{
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			
			movAba.setCostoMovimiento(movRaton.getCostoMovimiento() 
						  + ambiente.getCosto(movAba.getPosicionXRaton(), movAba.getPosicionYRaton()));
			movAba.setfHeuristica(mango(movAba) + movAba.getCostoMovimiento());

			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);

			if(!checkBucle(movAba)){
			    movAba.setAltura( movRaton.getAltura() + 1 );
			    if(movAba.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnCola(movAba);
			    cantidadnodos++;
			}
			else{
			    movAba = null;
			}
		    }
		}
		//cola.remove(0);
		if(cola.size()>0){
		    busquedaAStar((Raton)cola.get(0));
		}
	    }
	    else{
		recuperarCamino(movRaton);
		movRaton.tomarQueso();
		ambiente.ratonTieneQueso(true);

		if(fase == 0){
		    fase = 1;
		    Raton nuevoRaton = new Raton("S");
		    nuevoRaton.setPosicionXRaton(movRaton.getPosicionXRaton());
		    nuevoRaton.setPosicionYRaton(movRaton.getPosicionYRaton());
		    nuevoRaton.setAltura(movRaton.getAltura());
		    movRaton=null;
		    cola.removeAllElements();
		    cola.add(nuevoRaton);
		    ambiente.setPosicionXQueso(ambiente.getPosicionXSalida());
		    ambiente.setPosicionYQueso(ambiente.getPosicionYSalida());
		    busquedaAStar((Raton)cola.get(0));
		}
	    }
    }

    public void recuperarCamino(Raton movRaton){
	System.out.println("movimientos !!!");
	while(movRaton.getMovimiento() != "S"){
	    if(fase==0){
		movimientos1.add(0, movRaton.getMovimiento());
	    }
	    else{
		movimientos2.add(0, movRaton.getMovimiento());
	    }
	    System.out.print(movRaton.getMovimiento()+" ");
	    movRaton = movRaton.getMovAnterior();
	}
	if(fase ==1){
	    System.out.println("\ncantidad nodos creados: "+cantidadnodos+"\ncantidad nodos expandidos: "+nodosExpandidos);
	}
    }

    public void insertarEnCola(Raton raton){
	for(int i=0; i<cola.size(); i++){
	    if(raton.getFheuristica() < ((Raton)cola.get(i)).getFheuristica()){
		cola.add(i, raton);
		return;
	    }
	}
	cola.add(raton);
    }

    public boolean checkBucle(Raton raton){ // true: si hay bucle
	int x = raton.getPosicionXRaton();
	int y = raton.getPosicionYRaton();
	Raton movAnterior = raton.getMovAnterior();

	while(movAnterior != null){
	    if(x == movAnterior.getPosicionXRaton() && y == movAnterior.getPosicionYRaton()){
		return true;
	    }
	    else{
		movAnterior = movAnterior.getMovAnterior();
	    }
	}
	return false;
    }

    public double manhattan(Raton raton){
	return (Math.abs(raton.getPosicionXRaton() - ambiente.getPosicionXQueso()) + Math.abs(raton.getPosicionYRaton() - ambiente.getPosicionYQueso()));
	//return 0;
    }

    /*public double mango(Raton ratonM){
	int x0 = 0;
	int x1 = 0;
	int y0 = 0;
	int y1 = 0;
	
	if(ratonM.getPosicionXRaton() <= ambiente.getPosicionXQueso()){
	    x0 = ratonM.getPosicionXRaton();
	    x1 = ambiente.getPosicionXQueso();
	}
	else{
	    x1 = ratonM.getPosicionXRaton();
	    x0 = ambiente.getPosicionXQueso();
	}
	
	if(ratonM.getPosicionYRaton() <= ambiente.getPosicionYQueso()){
	    y0 = ratonM.getPosicionYRaton();
	    y1 = ambiente.getPosicionYQueso();
	}
	else{
	    y1 = ratonM.getPosicionYRaton();
	    y0 = ambiente.getPosicionYQueso();
	}
	
	int contador = 0;
	for(int i=x0; i<=x1; i++){
	    for(int j=y0; j<=y1; j++){
		if(ambiente.getAmbiente()[i][j] == 1){
		    contador++;
		}
	    }
	}
	System.out.println("contador : "+contador);
	contador += manhattan(ratonM);
	contador/=2;
	return contador);
	//return contador;
    }*/
    
    public double mango(Raton raton){
	//return Math.sqrt((Math.pow)+());
	return Math.sqrt(Math.pow(raton.getPosicionXRaton() - ambiente.getPosicionXQueso(), 2) + Math.pow(raton.getPosicionYRaton() - ambiente.getPosicionYQueso(), 2));
    }

    public Vector getMovimientos(){
	for(int i=0; i<movimientos1.size(); i++){
	    movimientos.add((String)movimientos1.get(i));
	}
	for(int i=0; i<movimientos2.size(); i++){
	    movimientos.add((String)movimientos2.get(i));
	}
	System.out.println("vector final de movimientos: " + movimientos.toString());
	System.out.println("altura del arbol: " + altura);
	return movimientos;
    }
}
