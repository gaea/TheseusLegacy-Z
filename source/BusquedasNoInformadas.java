import java.util.*;

public class BusquedasNoInformadas{

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
    private int altura = 0;
    int x =0;

    public BusquedasNoInformadas(Raton movRaton, Ambiente ambiente, String busqueda){
	this.ambiente = ambiente;
	this.movRaton = movRaton;

	movimientos = new Vector();
	movimientos1 = new Vector();
	movimientos2 = new Vector();
	cola = new Vector();
	cola.add(movRaton);
	
	startTime = System.nanoTime();
	if(busqueda == "busquedaPreferenteAmplitud"){
	    busquedaPreferenteAmplitud(this.movRaton);
	}

	if(busqueda == "busquedaPreferenteCostoUniforme"){
	    busquedaCostoUniforme(this.movRaton);
	}

	if(busqueda == "busquedaProfundidadSinCiclos"){
	    busquedaProfundidadSinCiclos(this.movRaton);
	}

	if(busqueda == "busquedaProfundidadConCiclos"){
	    busquedaProfundidadConCiclos(this.movRaton);
	}
	finishTime = System.nanoTime();

	System.out.println("tiempo en nano segundos : "+ (finishTime - startTime));
	System.out.println("todos los movimientos: ");
	System.out.print(movimientos1.toString()+" "+movimientos2.toString() );
	System.out.println("\nFin de " + busqueda);
	//System.exit(0);
    }

    public void busquedaPreferenteAmplitud(Raton movRaton){
	if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso()))){
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
		    movIzq.setMovAnterior(movRaton);
		    cola.add(movIzq);
		    cantidadnodos++;
		}
		else{
		    Raton movIzq = new Raton("I");
		    movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
		    movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
		    movIzq.moverIzquierda();
		    
		    movIzq.setMovAnterior(movRaton);

		    if(!checkBucle(movIzq)){
			movIzq.setAltura( movRaton.getAltura() + 1 );
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			cola.add(movIzq);
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
		    movDer.setMovAnterior(movRaton);
		    cola.add(movDer);
		    cantidadnodos++;
		}
		else{
		    Raton movDer = new Raton("D");
		    movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
		    movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
		    movDer.moverDerecha();
		    
		    movDer.setMovAnterior(movRaton);

		    if(!checkBucle(movDer)){
			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			cola.add(movDer);
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
		    movArr.setMovAnterior(movRaton);
		    cola.add(movArr);
		    cantidadnodos++;
		}
		else{
		    Raton movArr = new Raton("A");
		    movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
		    movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
		    movArr.moverArriba();
		    
		    movArr.setMovAnterior(movRaton);

		    if(!checkBucle(movArr)){
			movArr.setAltura( movRaton.getAltura() + 1 );
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			cola.add(movArr);
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
		    movAba.setMovAnterior(movRaton);
		    cola.add(movAba);
		    cantidadnodos++;
		}
		else{
		    Raton movAba = new Raton("B");
		    movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
		    movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
		    movAba.moverAbajo();
		    
		    movAba.setMovAnterior(movRaton);
  
		    if(!checkBucle(movAba)){
			movAba.setAltura( movRaton.getAltura() + 1 );
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			cola.add(movAba);
			cantidadnodos++;
		    }
		    else{
			movAba = null;
		    }
 		}
	    }
	    //cola.remove(0);
	    if(cola.size()>0){
		busquedaPreferenteAmplitud((Raton)cola.get(0));
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
		nuevoRaton.setAltura( movRaton.getAltura());
		movRaton=null;
		cola.removeAllElements();
		cola.add(nuevoRaton);
		ambiente.setPosicionXQueso(ambiente.getPosicionXSalida());
		ambiente.setPosicionYQueso(ambiente.getPosicionYSalida());
		busquedaPreferenteAmplitud((Raton)cola.get(0));
	    }
	}
    }

    public void busquedaCostoUniforme(Raton movRaton){
	    
	    if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso()))){
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
		    busquedaCostoUniforme((Raton)cola.get(0));
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
		    nuevoRaton.setAltura( movRaton.getAltura() );
		    movRaton=null;
		    cola.removeAllElements();
		    cola.add(nuevoRaton);
		    ambiente.setPosicionXQueso(ambiente.getPosicionXSalida());
		    ambiente.setPosicionYQueso(ambiente.getPosicionYSalida());
		    busquedaCostoUniforme((Raton)cola.get(0));
		}
	    }
	}

    public void busquedaProfundidadSinCiclos(Raton movRaton){
	    
	    if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso()))){
		nodosExpandidos++;
		cola.remove(0);

		if(movRaton.sensorDerecho(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			//System.out.println("estoy en el null de derecha movimiento");
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();

			movDer.setAltura( movRaton.getAltura() + 1 );
			if(movDer.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			//System.out.println("altura d "+ movDer.getAltura());
			movDer.setMovAnterior(movRaton);

			insertarEnPila(movDer);
			cantidadnodos++;
		    }
		    else{
			//System.out.println("estoy en el else del derecha movimiento");
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			
			//System.out.println("altura d "+movDer.getAltura());
			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);

			//System.out.println("altura d"+movDer.getAltura());

			if(!checkBucle(movDer)){
			    movDer.setAltura(movRaton.getAltura() + 1);
			    if(movDer.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnPila(movDer);
			    cantidadnodos++;
			}
			else{
			    movDer = null;
			}
		    }
		}

		if(movRaton.sensorIzquierdo(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			//System.out.println("estoy en el null de izquierda movimiento");
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			
			movIzq.setAltura(movRaton.getAltura() + 1);
			if(movIzq.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			//System.out.println("altura i "+movIzq.getAltura());
			movIzq.setMovAnterior(movRaton);
			movRaton.setMovIzquierda(movIzq);
			insertarEnPila(movIzq);
			cantidadnodos++;
		    }
		    else{
			//System.out.println("estoy en el else del izquierda movimiento");
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();

			//System.out.println("altura i "+movIzq.getAltura());
			movIzq.setMovAnterior(movRaton);

			if(!checkBucle(movIzq)){
			    movIzq.setAltura(movRaton.getAltura() + 1);
			    if(movIzq.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnPila(movIzq);
			    cantidadnodos++;
			}
			else{
			    movIzq = null;
			}
		    }
		}

		if(movRaton.sensorArriba(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			
			movArr.moverArriba();
			//System.out.println("estoy en "+ movArr.getPosicionXRaton() + " , "+movArr.getPosicionYRaton());
			movArr.setAltura(movRaton.getAltura() + 1);
			if(movArr.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			//System.out.println("altura a "+movArr.getAltura());
			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);
			insertarEnPila(movArr);
			cantidadnodos++;
		    }
		    else{
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			//System.out.println("estoyn "+ movArr.getPosicionXRaton() + " , "+movArr.getPosicionYRaton());
			
			//System.out.println("altura a "+movArr.getAltura());
			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);

			if(!checkBucle(movArr)){
			    movArr.setAltura(movRaton.getAltura() + 1);
			    if(movArr.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnPila(movArr);
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
			movAba.setAltura(movRaton.getAltura() + 1);
			if(movAba.getAltura() > altura){
			    altura = movRaton.getAltura();
			}
			//System.out.println("altura b "+movAba.getAltura());
			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);
			insertarEnPila(movAba);
			cantidadnodos++;
		    }
		    else{
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			//System.out.println("estoyn "+ movAba.getPosicionXRaton() + " , "+movAba.getPosicionYRaton());
			
			//System.out.println("altura b "+movAba.getAltura());
			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);

			if(!checkBucle(movAba)){
			    movAba.setAltura(movRaton.getAltura() + 1);
			    if(movAba.getAltura() > altura){
				altura = movRaton.getAltura();
			    }
			    insertarEnPila(movAba);
			    cantidadnodos++;
			}
			else{
			    movAba = null;
			}
		    }
		}
		//cola.remove(1);
		if(cola.size()>0){
		    busquedaProfundidadSinCiclos((Raton)cola.get(0));
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
		    busquedaProfundidadSinCiclos((Raton)cola.get(0));
		}
	    }
	}

    public void busquedaProfundidadConCiclos(Raton movRaton){
	    
	    if(!(movRaton.huele_queso(ambiente.getPosicionXQueso(), ambiente.getPosicionYQueso())) && x < 30){
		x++;
		cola.remove(0);
		nodosExpandidos++;

		if(movRaton.sensorDerecho(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			//System.out.println("estoy en el null de derecha movimiento");
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();

			movDer.setAltura( movRaton.getAltura() + 1 );

			//System.out.println("altura d "+movDer.getAltura());
			movDer.setMovAnterior(movRaton);

			insertarEnPila(movDer);
			cantidadnodos++;
		    }
		    else{
			//System.out.println("estoy en el else del derecha movimiento");
			Raton movDer = new Raton("D");
			movDer.setPosicionXRaton(movRaton.getPosicionXRaton());
			movDer.setPosicionYRaton(movRaton.getPosicionYRaton());
			movDer.moverDerecha();
			movDer.setAltura(movRaton.getAltura() + 1);
			//System.out.println("altura d "+movDer.getAltura());
			movDer.setMovAnterior(movRaton);
			movRaton.setMovDerecha(movDer);

			//System.out.println("altura"+movDer.getAltura());

			//if(!checkBucle(movDer)){
			    insertarEnPila(movDer);
			    cantidadnodos++;
			/*}
			else{
			    movDer = null;
			}*/
		    }
		}

		if(movRaton.sensorIzquierdo(ambiente.getAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			//System.out.println("estoy en el null de izquierda movimiento");
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();
			
			movIzq.setAltura(movRaton.getAltura() + 1);
			//System.out.println("altura i "+movIzq.getAltura());
			movIzq.setMovAnterior(movRaton);
			movRaton.setMovIzquierda(movIzq);
			insertarEnPila(movIzq);
			cantidadnodos++;
		    }
		    else{
			//System.out.println("estoy en el else del izquierda movimiento");
			Raton movIzq = new Raton("I");
			movIzq.setPosicionXRaton(movRaton.getPosicionXRaton());
			movIzq.setPosicionYRaton(movRaton.getPosicionYRaton());
			movIzq.moverIzquierda();

			movIzq.setAltura(movRaton.getAltura() + 1);
			//System.out.println("altura i "+movIzq.getAltura());
			movIzq.setMovAnterior(movRaton);

			//if(!checkBucle(movIzq)){
			    insertarEnPila(movIzq);
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
			movArr.setAltura(movRaton.getAltura() + 1);
			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);
			insertarEnPila(movArr);
			cantidadnodos++;
		    }
		    else{
			Raton movArr = new Raton("A");
			movArr.setPosicionYRaton(movRaton.getPosicionYRaton());
			movArr.setPosicionXRaton(movRaton.getPosicionXRaton());
			movArr.moverArriba();
			movArr.setAltura(movRaton.getAltura() + 1);
			movArr.setMovAnterior(movRaton);
			movRaton.setMovArriba(movArr);

			//if(!checkBucle(movArr)){
			    insertarEnPila(movArr);
			    cantidadnodos++;
			/*}
			else{
			    movArr = null;
			}*/
		    }
		}

		if(movRaton.sensorAbajo(ambiente.getAmbiente(), ambiente.getTamanoAmbiente())){
		    if(movRaton.getMovAnterior() == null){
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura(movRaton.getAltura() + 1);
			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);
			insertarEnPila(movAba);
			cantidadnodos++;
		    }
		    else{
			Raton movAba = new Raton("B");
			movAba.setPosicionYRaton(movRaton.getPosicionYRaton());
			movAba.setPosicionXRaton(movRaton.getPosicionXRaton());
			movAba.moverAbajo();
			movAba.setAltura(movRaton.getAltura() + 1);
			movAba.setMovAnterior(movRaton);
			movRaton.setMovAbajo(movAba);

			//if(!checkBucle(movAba)){
			    insertarEnPila(movAba);
			    cantidadnodos++;
			/*}
			else{
			    movAba = null;
			}*/
		    }
		}
		//cola.remove(0);
		if(cola.size()>0){
		    busquedaProfundidadConCiclos((Raton)cola.get(0));
		}
	    }
	    else{
		recuperarCamino(movRaton);
		altura += movRaton.getAltura();
		movRaton.tomarQueso();

		if(fase == 0){
		    fase = 1;
		    Raton nuevoRaton = new Raton("S");
		    nuevoRaton.setPosicionXRaton(movRaton.getPosicionXRaton());
		    nuevoRaton.setPosicionYRaton(movRaton.getPosicionYRaton());
		    movRaton=null;
		    cola.removeAllElements();
		    cola.add(nuevoRaton);
		    ambiente.setPosicionXQueso(ambiente.getPosicionXSalida());
		    ambiente.setPosicionYQueso(ambiente.getPosicionYSalida());
		    busquedaProfundidadConCiclos((Raton)cola.get(0));
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
	    if(raton.getCostoMovimiento() < ((Raton)cola.get(i)).getCostoMovimiento()){
		cola.add(i, raton);
		return;
	    }
	}
	cola.add(raton);
    }

    public void insertarEnPila(Raton raton){
	for(int i=0; i<cola.size(); i++){
	    if(raton.getAltura() > ((Raton)cola.get(i)).getAltura()){
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
