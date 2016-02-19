import javax.swing.*;
import java.util.*;
import java.io.*;

public class Archivo{

    private String nombreArchivo;
    private String archivo;
    private JFileChooser selectorArchivo;
    private File nomArchivo;
    private String propiedadesArchivo;

    public Archivo(){
	//abrirArchivo();
	//leerArchivo(nombreArchivo);
    }
    
    public void abrirArchivo(){
	selectorArchivo = new JFileChooser(System.getProperty("user.dir"));
	int resultado = selectorArchivo.showOpenDialog( null );

	if( resultado == JFileChooser.CANCEL_OPTION ){
	    JOptionPane.showMessageDialog(null, "Nombre de archivo incorrecto", "Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
	    System.exit(0);
	}

	if( resultado == JFileChooser.APPROVE_OPTION ){
	    nomArchivo = selectorArchivo.getSelectedFile() ;

	    if (nomArchivo==null || nomArchivo.getName().equals("")){
		JOptionPane.showMessageDialog(null, "Nombre de archivo incorrecto", "Nombre incorrecto o Vacio", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	    }

	    else
	    nombreArchivo= nomArchivo.getName();

	    if(nomArchivo.exists()){
		propiedadesArchivo = "Nombre del Archivo :   "+nomArchivo.getName()+"\nRuta :   "+nomArchivo.getPath()+
		"\nRuta Absoluta :   "+nomArchivo.getAbsolutePath()+"\nPermisos de Escritura :   "+nomArchivo.canRead()+"\nPermisos de Lectura :   "+nomArchivo.canWrite()+
		"\nTamaño :   "+(nomArchivo.length())+"Bytes";
	    }
	}
    }

    public void leerArchivo(String nombre){
	String renglon= "";
	archivo="";

	try{
	    RandomAccessFile file = new RandomAccessFile(nombre, "rw");
	    renglon= file.readLine( );
		
	    while( renglon != null ){
		archivo+= renglon+"\n";
		renglon= file.readLine( );
	    }

	    archivo=archivo.substring(0, archivo.length()-1);
	    file.close();
	    System.out.println( "\narchivo leido: \n"+archivo+"\n" );
	}

	catch( IOException e )
	{ System.out.println("Error al buscar en el archivo"); }
    }

    public String getArchivo(){
	return archivo;
    }

    public String getNombreArchivo(){
	return nombreArchivo;
    }

    public static void main(String args []){
	new Archivo();
    }
}