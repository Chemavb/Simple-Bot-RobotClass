import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;

public class Principal {
	private static Robot robot = null;
	private static ArrayList<Color> listaColoresHP = null; 
	private static boolean inicioPartida;

	public static void main(String[] args) throws Exception {
		robot = new Robot();
		
		inicioPartida = true;
		
		robot.delay(2000);
		movimientoInicial();
		
		robot.delay(30000);
		
		while(true) {
			if (!estaMuerto()) { //Si está vivo 
				autoA ();
			} else {
				robot.delay(20000);
				movimientoInicial();
				spaceBar();
				robot.delay(30000);
			}
		}

	}
	
	private static void movimientoInicial() {
		ctrlQ(); // sube nivel Q
		accionMuerto(); //Haces lo mismo si mueres que si empiezas partida.		
	}

	private static void printColor(Color color) {
		System.out.println("Blue color: " + color.getBlue());
		System.out.println("Green color: " + color.getGreen());
		System.out.println("Red color: " + color.getRed());
	}

	public static void klick (int x, int y) {
		robot.mouseMove(x,y);
		robot.delay(100);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	public static void klick_der (int x, int y) {
		robot.mouseMove(x,y);
		robot.delay(100);
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
	}
	
	public static void ctrlQ () {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_Q);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_Q);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public static void Q() {
		robot.keyPress(KeyEvent.VK_Q);
		robot.delay(200);
		robot.keyRelease(KeyEvent.VK_Q);
	}
	
	public static void ctrlW () {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.delay(200);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public static void ctrlE () {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_E);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public static void ctrlR () {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_R);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public static void spaceBar () {
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_SPACE);
	}
	
	public static void autoA () {
		spaceBar();
		robot.delay(750);
		robot.mouseMove(605, 250);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_A);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_A);
		
		if(Math.random() * 10 > 8 ) //si random, usa la habilidad Q x-D
			Q();
		getHP();
		//si le queda poca vida flashea random x-DD
		System.out.println(listaColoresHP.get(2).getGreen());
		if(listaColoresHP.get(2).getGreen() < 50) 
			flashea();
	}
	
	public static void flashea() {
		robot.keyPress(KeyEvent.VK_D);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_D);
	}
	public static void getHP() {
		listaColoresHP = new ArrayList<Color>();
		Color colorHP1 = robot.getPixelColor(318, 760);
		Color colorHP2 = robot.getPixelColor(405, 760);
		Color colorHP3 = robot.getPixelColor(500, 760);
		Color colorHP4 = robot.getPixelColor(605, 760);
		listaColoresHP.add(colorHP1);
		listaColoresHP.add(colorHP2);
		listaColoresHP.add(colorHP3);
		listaColoresHP.add(colorHP4);
	
	}
	
	public static boolean estaMuerto() {
		//Return true si está muerto
		Color color = robot.getPixelColor(318, 760);
		System.out.println(color);
		if (color.getGreen() < 50) //Estás muerto
			return true;
		else
			return false;
	}
	
	public static void accionMuerto() {
		int x = 925;
		int y = 695;
		robot.mouseMove(x,y);
		klick(x,y);
		klick_der(x,y);
	}
	
	public static void partidaEmpezada() {
		inicioPartida = false;
	}
	
}
