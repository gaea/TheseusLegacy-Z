import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.geometry.*;

import java.io.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Behavior.*;
import javax.media.j3d.WakeupOnAWTEvent;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.*;
import java.util.Enumeration;
import javax.media.j3d.BoundingBox;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Scene extends BranchGroup
{
    private int mapa [][];
    private TransformGroup vpTrans;
    private BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
    private KeyNavigatorBehavior keyNavSphere;
    private KeyNavigatorBehavior keyNavCamara;
    private Ambiente ambiente;
    private Appearance apparYellow;
    private Appearance apparBlue;
    private Appearance apparBlock;
    private Appearance apparFloor;
    private Appearance apparDragonSphere;
    private Appearance apparGoku;
    private Appearance apparExit;
    private Appearance apparCell;
    private Appearance apparBlack;
    private Appearance apparGreen;
    private Sphere cheese;
    Transform3D t3dSpCheese;
    TransformGroup vpTransSpCheese;
    javax.swing.Timer timer;
    private Sound sonido;
    Vector movimientos;
    private float x0;
    private float y0;
    int i = 0;
    int tiempo = 0;
    int tmp = 0;

    public Scene(SimpleUniverse simpleU, Vector movimientos, Ambiente ambiente, Canvas3D canvas)
    {
	sonido = new Sound("../sounds/pick.wav");
	this.ambiente = ambiente;
	mapa = ambiente.getAmbiente();
	this.movimientos = movimientos;
	vpTrans = simpleU.getViewingPlatform().getViewPlatformTransform();
	loadAppearances();
	addCamera();
	addLight();
	addBackground();
	addMaze();
	addCheese();
	addCat1();
	addCat2();
	addExit();
	addMouse();

	canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                
            }
        });
    }

    public void addBackground()
    {
	Background background = new Background();
	background.setColor( 0.0f, 0.0f, 0.0f );
	background.setApplicationBounds(new BoundingSphere());
	addChild(background);
    }

    public void addLight()
    {
	Color3f ambientColour = new Color3f(0.2f, 0.2f, 0.2f);
	AmbientLight ambientLightNode = new AmbientLight(ambientColour);
	ambientLightNode.setInfluencingBounds(bounds);

	Color3f lightColour = new Color3f(1.0f, 1.0f, 1.0f);
	Vector3f lightDir = new Vector3f(-1.0f, -1.0f, -1.0f);
	DirectionalLight light = new DirectionalLight(lightColour, lightDir);
	light.setInfluencingBounds(bounds);

	addChild(ambientLightNode);
	addChild(light);
    }

      public void addCamera(){
	  Transform3D T3D;
	  Vector3f translate = new Vector3f();
	  
	  T3D = new Transform3D();
	  translate.set(4.5f, -3.5f, 20.0f);
	  
	  Transform3D rotViewScene = new Transform3D();
	  rotViewScene.rotX(Math.PI/8.0);
	  
	  T3D.setTranslation(translate);
	  T3D.mul(rotViewScene);
	  vpTrans.setTransform(T3D);
	
	  //T3D.setTranslation(translate);
	  //vpTrans.setTransform(T3D);

	  keyNavCamara = new KeyNavigatorBehavior(vpTrans);
	  keyNavCamara.setSchedulingBounds(bounds);
	  addChild(keyNavCamara);

	  MouseRotate myMouseRotate = new MouseRotate(MouseBehavior.INVERT_INPUT);
	  myMouseRotate.setTransformGroup(vpTrans);
	  myMouseRotate.setSchedulingBounds(bounds);
	  addChild(myMouseRotate);

	  MouseTranslate myMouseTranslate = new MouseTranslate(
	  MouseBehavior.INVERT_INPUT);
	  myMouseTranslate.setTransformGroup(vpTrans);
	  myMouseTranslate.setSchedulingBounds(bounds);
	  addChild(myMouseTranslate);

	  MouseZoom myMouseZoom = new MouseZoom(MouseBehavior.INVERT_INPUT);
	  myMouseZoom.setTransformGroup(vpTrans);
	  myMouseZoom.setSchedulingBounds(bounds);
	  addChild(myMouseZoom);
	  
      }

      public void loadAppearances(){
	  Color yellow = new Color(1.0f, 1.0f, 0.0f);
	  Color blue = new Color(0.0f, 0.0f, 1.0f);
	  Color red = new Color(1.0f, 0.0f, 0.0f);
	  Color white = new Color(1.0f, 1.0f, 1.0f);
	  Color black = new Color(0.0f, 0.0f, 0.0f);
	  Color green = new Color(0.0f, 1.0f, 0.0f);

	  float shininess = 20.0f;

	  apparYellow = new Appearance();
	  Color3f emissiveColourYellow = new Color3f(0.0f, 0.0f, 0.0f);
	  Color3f specularColourYellow = new Color3f(0.5f, 0.5f, 0.5f);
	  Color3f ambientColourYellow = new Color3f(yellow);
	  Color3f diffuseColourYellow = new Color3f(yellow);

	  ColoringAttributes colorAtriYellow = new ColoringAttributes();
	  colorAtriYellow.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
	  apparYellow.setColoringAttributes(colorAtriYellow);
	  apparYellow.setMaterial(new Material(ambientColourYellow, emissiveColourYellow, diffuseColourYellow, specularColourYellow, shininess));

	  apparBlue = new Appearance();
	  Color3f emissiveColourBlue = new Color3f(0.0f, 0.0f, 0.0f);
	  Color3f specularColourBlue = new Color3f(1.0f, 1.0f, 1.0f);      
	  Color3f ambientColourBlue = new Color3f(blue);
	  Color3f diffuseColourBlue = new Color3f(blue);

	  ColoringAttributes colorAtriBlue = new ColoringAttributes();
	  colorAtriBlue.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
	  apparBlue.setColoringAttributes(colorAtriBlue);
	  apparBlue.setMaterial(new Material(ambientColourBlue, emissiveColourBlue, diffuseColourBlue, specularColourBlue, shininess));
	  
	  Texture texBlock = null;
	  try{
	      texBlock = new TextureLoader(new java.net.URL("file:../images/block3.jpg"), null).getTexture();
	  }
	  catch(Exception e){}
	  
	  TextureAttributes texAttr = new TextureAttributes();
	  texAttr.setTextureMode(TextureAttributes.MODULATE);

	  apparBlock = new Appearance();
	  apparBlock.setTexture(texBlock);
	  apparBlock.setTextureAttributes(texAttr);
	  
	  Texture texFloor = null;
	  try{
	      texFloor = new TextureLoader(new java.net.URL("file:../images/floor1.jpg"), null).getTexture();
	  }
	  catch(Exception e){}

	  apparFloor = new Appearance();
	  apparFloor.setTexture(texFloor);
	  apparFloor.setTextureAttributes(texAttr);

	  Texture texGoku = null;
	  try{
	      texGoku = new TextureLoader(new java.net.URL("file:../images/goku3.jpg"), null).getTexture();
	  }
	  catch(Exception e){}

	  apparGoku = new Appearance();
	  apparGoku.setTexture(texGoku);
	  apparGoku.setTextureAttributes(texAttr);

	  Texture texDragonSphere = null;
	  try{
	      texDragonSphere = new TextureLoader(new java.net.URL("file:../images/esfera2.jpg"), null).getTexture();
	  }
	  catch(Exception e){}

	  apparDragonSphere = new Appearance();
	  apparDragonSphere.setTexture(texDragonSphere);
	  apparDragonSphere.setTextureAttributes(texAttr);
	  
	  Texture texExit = null;
	  try{
	      texExit = new TextureLoader(new java.net.URL("file:../images/035.jpg"), null).getTexture();
	  }
	  catch(Exception e){}

	  apparExit = new Appearance();
	  apparExit.setTexture(texExit);
	  apparExit.setTextureAttributes(texAttr);
	  
	  Texture texCell = null;
	  try{
	      texCell = new TextureLoader(new java.net.URL("file:../images/freezer.jpg"), null).getTexture();
	  }
	  catch(Exception e){}

	  apparCell = new Appearance();
	  apparCell.setTexture(texCell);
	  apparCell.setTextureAttributes(texAttr);
	  

	  apparBlack = new Appearance();
	  Color3f emissiveColourBlack = new Color3f(0.0f, 0.0f, 0.0f);
	  Color3f specularColourBlack = new Color3f(1.0f, 1.0f, 1.0f);      
	  Color3f ambientColourBlack = new Color3f(black);
	  Color3f diffuseColourBlack = new Color3f(black);

	  ColoringAttributes colorAtriBlack = new ColoringAttributes();
	  colorAtriBlack.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
	  apparBlack.setColoringAttributes(colorAtriBlack);
	  apparBlack.setMaterial(new Material(ambientColourBlack, emissiveColourBlack, diffuseColourBlack, specularColourBlack, shininess));

	  apparGreen = new Appearance();
	  Color3f emissiveColourGreen = new Color3f(0.0f, 0.0f, 0.0f);
	  Color3f specularColourGreen = new Color3f(1.0f, 1.0f, 1.0f);      
	  Color3f ambientColourGreen = new Color3f(green);
	  Color3f diffuseColourGreen = new Color3f(green);

	  ColoringAttributes colorAtriGreen = new ColoringAttributes();
	  colorAtriGreen.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
	  apparGreen.setColoringAttributes(colorAtriGreen);
	  apparGreen.setMaterial(new Material(ambientColourGreen, emissiveColourGreen, diffuseColourGreen, specularColourGreen, shininess));
      }

      public void addCheese(){
	  t3dSpCheese = new Transform3D();
	  t3dSpCheese.setTranslation( new Vector3d(ambiente.getPosicionXQueso(), -1*ambiente.getPosicionYQueso() + 10, 0.0));
	  vpTransSpCheese = new TransformGroup(t3dSpCheese);
	  cheese = new Sphere(0.25f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 100, apparDragonSphere);
	  //vpTransSps.addChild(new Sphere(0.5f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 100, apparGoku));
	  vpTransSpCheese.addChild(cheese);
	  vpTransSpCheese.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	  vpTransSpCheese.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	  addChild(vpTransSpCheese);
      }

      public void addCat1(){
	  Transform3D t3dSp = new Transform3D();
	  t3dSp.setTranslation( new Vector3d(ambiente.getPosicionXGato1(), -1*ambiente.getPosicionYGato1() + 10, 0.0));
	  TransformGroup vpTransSp = new TransformGroup(t3dSp);
	  //vpTransSp.addChild(new Box(0.6f, 0.6f, 0.6f, Box.GENERATE_TEXTURE_COORDS, apparCell));
	  vpTransSp.addChild(new Sphere(0.6f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 100, apparCell));
	  vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	  vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	  addChild(vpTransSp);
      }

      public void addCat2(){
	  Transform3D t3dSp = new Transform3D();
	  t3dSp.setTranslation( new Vector3d(ambiente.getPosicionXGato2(), -1*ambiente.getPosicionYGato2() + 10, 0.0));
	  TransformGroup vpTransSp = new TransformGroup(t3dSp);
	  //vpTransSp.addChild(new Sphere(0.5f, 1, 100, apparBlack));
	  vpTransSp.addChild(new Sphere(0.6f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 100, apparCell));
	  vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	  vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	  addChild(vpTransSp);
      }

      public void addExit(){
	  Transform3D t3dSp = new Transform3D();
	  t3dSp.setTranslation( new Vector3d(ambiente.getPosicionXSalida(), -1*ambiente.getPosicionYSalida() + 10, 0.0));
	  TransformGroup vpTransSp = new TransformGroup(t3dSp);
	  //vpTransSps.addChild(new Box(0.4f, 0.4f, 0.4f, Box.GENERATE_TEXTURE_COORDS, apparGoku));
	  vpTransSp.addChild(new Box(0.6f, 0.6f, 0.6f, Box.GENERATE_TEXTURE_COORDS, apparExit));
	  vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	  vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	  addChild(vpTransSp);
      }

      public void addMouse(){
	  final Transform3D t3dSp = new Transform3D();
	  x0 = ambiente.getPosicionXRaton();
	  y0 = -ambiente.getPosicionYRaton() + 10;
	  t3dSp.setTranslation( new Vector3d(x0, y0, 0.0));
	  final TransformGroup vpTransSps = new TransformGroup(t3dSp);
	  //vpTransSps.addChild(new Box(0.4f, 0.4f, 0.4f, Box.GENERATE_TEXTURE_COORDS, apparGoku));
	  //Sphere sphere = new Sphere(3, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, app);
	  vpTransSps.addChild(new Sphere(0.5f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 100, apparGoku));
	  vpTransSps.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	  vpTransSps.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	  addChild(vpTransSps);
	  compile();
	  
	  timer = new javax.swing.Timer (5, new ActionListener ()
          {
	      public void actionPerformed(ActionEvent e)
              {
		   if(tiempo <= 500 && i < movimientos.size()){
		      if(((int)x0 == ambiente.getPosicionXQueso()) && ((int)y0 == (-1*ambiente.getPosicionYQueso()+10) && (tmp==0))){
			  //javax.swing.JOptionPane.showMessageDialog(null, "queso!!!!!!!!!");
			  t3dSpCheese.setTranslation(new Vector3d(0.0, 0.0, -100.0));
			  tmp++;
			  if(sonido!= null){ sonido.task("play");}
			  else{ System.out.println("no se pudo cargar el sonido");}
			  vpTransSpCheese.setTransform(t3dSpCheese);
			  
		      }
		      tiempo += 5;
		      if((String)movimientos.get(i) == "D"){
			      x0 += 0.01;
			      t3dSp.setTranslation(new Vector3f(x0, y0, 0.0f));
			      vpTransSps.setTransform(t3dSp);
			  }
		      
		      if((String)movimientos.get(i) == "I"){
			      x0 -= 0.01;
			      t3dSp.setTranslation(new Vector3f(x0, y0, 0.0f));
			      vpTransSps.setTransform(t3dSp);
			  
		      }
		      if((String)movimientos.get(i) == "A"){
			      y0+=0.01;
			      t3dSp.setTranslation(new Vector3f(x0, y0, 0.0f));
			      vpTransSps.setTransform(t3dSp);
		      }
		      
		      if((String)movimientos.get(i) == "B"){
			      y0-=0.01;
			      t3dSp.setTranslation(new Vector3f(x0, y0, 0.0f));
			      vpTransSps.setTransform(t3dSp);
			  }
		      }
		      else{
			  if(i < movimientos.size()){
			      i++;
			      tiempo = 0;
			  }
			  else{
			      System.out.println("Eso es todo amigos !!!!!!");
			      timer.stop();
			      //System.exit(0);
			  }
		      }
		}
		
	  });
	  timer.start();
      }

      public void addMaze(){
	  for(int i=0; i < ambiente.getTamanoAmbiente(); i++){
	      for(int j=0; j < ambiente.getTamanoAmbiente(); j++){
		  Transform3D t3dSpf = new Transform3D();
		  t3dSpf.setTranslation( new Vector3d(((float)j), (float)(-1*i + 10), -1.0));
		  TransformGroup vpTransSpf = new TransformGroup(t3dSpf);
		  vpTransSpf.addChild(new Box(0.5f, 0.5f, 0.5f, Box.GENERATE_TEXTURE_COORDS, apparFloor));
		  vpTransSpf.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		  vpTransSpf.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		  addChild(vpTransSpf);
	      
		  if(mapa[i][j] == 1){
		      Transform3D t3dSp = new Transform3D();
		      t3dSp.setTranslation( new Vector3d(((float)j), (float)(-1*i + 10), 0.0));
		      TransformGroup vpTransSp = new TransformGroup(t3dSp);
		      vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, Box.GENERATE_TEXTURE_COORDS, apparBlock));
		      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		      addChild(vpTransSp);
		  }
	      }
	  }
	  for(int i=-1 ; i<11 ; i++){
	      Transform3D t3dSp = new Transform3D();
	      t3dSp.setTranslation( new Vector3d( (float)i, 0.0, 0.0));
	      TransformGroup vpTransSp = new TransformGroup(t3dSp);
	      //vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, apparYellow));
	      vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, Box.GENERATE_TEXTURE_COORDS, apparBlock));
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	      addChild(vpTransSp);
	  }
	  for(int i=-1 ; i<11 ; i++){
	      Transform3D t3dSp = new Transform3D();
	      t3dSp.setTranslation( new Vector3d( (float)i, 11.0, 0.0));
	      TransformGroup vpTransSp = new TransformGroup(t3dSp);
	      //vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, apparYellow));
	      vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, Box.GENERATE_TEXTURE_COORDS, apparBlock));
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	      addChild(vpTransSp);
	  }
	  for(int i=0 ; i<11 ; i++){
	      Transform3D t3dSp = new Transform3D();
	      t3dSp.setTranslation( new Vector3d( -1.0, (float)i, 0.0));
	      TransformGroup vpTransSp = new TransformGroup(t3dSp);
	      //vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, apparYellow));
	      vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, Box.GENERATE_TEXTURE_COORDS, apparBlock));
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	      addChild(vpTransSp);
	  }
	  for(int i=0 ; i<11 ; i++){
	      Transform3D t3dSp = new Transform3D();
	      t3dSp.setTranslation( new Vector3d( 10.0, (float)i, 0.0));
	      TransformGroup vpTransSp = new TransformGroup(t3dSp);
	      //vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, apparYellow));
	      vpTransSp.addChild(new Box(0.5f, 0.5f, 0.5f, Box.GENERATE_TEXTURE_COORDS, apparBlock));
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	      vpTransSp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	      addChild(vpTransSp);
	  }
      }
}
