package clases;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Partida extends JFrame implements Runnable {
	public static final double GROUND_HEIGHT_PERCENTAGE = 0.85;
	private static final int SECOND = 1000;
	private static final int FRAMES_PER_SECOND = 60;
	private static final int SKIP_FRAMES = SECOND / FRAMES_PER_SECOND;
	private static final int TICKS_PER_SECOND = 60;
	private static final int SKIP_TICKS = SECOND / TICKS_PER_SECOND;
	public int PLAYER_AMOUNT;
	boolean isRunning = true;

	public static void main(String[] args) throws FileNotFoundException {
		JFrame frame = new JFrame();

		JButton button = new JButton("Jugar");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame.add(button, BorderLayout.CENTER);
		frame.setExtendedState(MAXIMIZED_BOTH);
		button.setFont(new Font("arial", 0, 30));

		frame.setVisible(true);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Partida p = null;
				Thread tp = null;
				try {
					p = new Partida(1, 0, "0", 4);
					tp = new Thread(p);

				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
				tp.start();
			}
		});

	}

	private int velocidad;
	private long tiempo;

	ArrayList<Pantalla> pantallas;

	private ArrayList<Jugador> jugadores;
	private ArrayList<Obstaculo> obstaculos;
	private ArrayList<Jugador> jugadoresOrdenFin;

	public Partida(int velocidad, long tiempo, String mapa, int cantidadJugadores) throws FileNotFoundException {
		super();
		init(velocidad, tiempo, mapa, cantidadJugadores);
	}

	private void init(int velocidad, long tiempo, String mapa, int cantidadJugadores) throws FileNotFoundException {
		this.velocidad = velocidad;
		this.tiempo = tiempo;
//        this.cargarObstaculos(mapa);
		this.jugadores = new ArrayList<>();
		this.pantallas = new ArrayList<>();
		this.jugadoresOrdenFin = new ArrayList<>();
		this.PLAYER_AMOUNT = cantidadJugadores;

		// Init pantallas y jugadores
		for (int i = 1; i <= PLAYER_AMOUNT; i++) {
			Jugador jugador = new Jugador(50, 0, "dinogif.gif", "dino#" + i);
			jugador.saltar();
			jugadores.add(jugador);
		}
		for (int i = 0; i < PLAYER_AMOUNT; i++) {
			pantallas.add(new Pantalla(jugadores.get(i), this, "fondo.jpg"));
		}
		for (Pantalla pantalla : pantallas) {
			add(pantalla);
		}

		// Setup display
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
		setTitle("TheExceptions");
		setExtendedState(MAXIMIZED_BOTH);

		for (Jugador jugador : jugadores) {
			jugador.setY((int) (getContentPane().getSize().getHeight() * GROUND_HEIGHT_PERCENTAGE));
			System.out.println(jugador.getY());
		}

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_R) {
					jugadores.get(0).revivir();
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					jugadores.get(0).saltar();
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					jugadores.get(0).moverseX();
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					jugadores.get(0).moverseXneg();
				} else {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_Z:
						jugadores.get(1).saltar();
						break;
					case KeyEvent.VK_X:
						jugadores.get(2).saltar();
						break;
					case KeyEvent.VK_C:
						jugadores.get(3).saltar();
						break;
					}

				}
			}
		});

	}

	// Esto tiene que ejecutarse cada frame del juego
	public void verificarColisiones() {
		Iterator<Jugador> iterator = jugadores.iterator();

		while (iterator.hasNext()) {
			Jugador jugador = iterator.next();
			for (Obstaculo obstaculo : obstaculos) {
				if (jugador.colisionaCon(obstaculo)) {
					iterator.remove();
					break;
				}
			}
		}
	}

	public void cargarObstaculos(String nombreArchivo) throws FileNotFoundException {
		this.obstaculos = new ArrayList<>();
		Scanner scanner = new Scanner(new File("Archivos/entrada/" + nombreArchivo));
		while (scanner.hasNextInt()) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			String imagen = "imagenes/" + scanner.nextInt();
			obstaculos.add(new Obstaculo(x, y, imagen));
		}
	}

	public int getCantJugadores() {
		return this.jugadores.size();
	}

	public int getCantObstaculos() {
		return this.obstaculos.size();
	}

	@Override
	public void run() {
		long next_game_tick = System.currentTimeMillis();
		long next_game_frame = System.currentTimeMillis();
		long next_frame_calc = System.currentTimeMillis();
		int frames = 0;

		while (isRunning) {
			if (System.currentTimeMillis() > next_game_tick) {
				next_game_tick += SKIP_TICKS;
				update();
			}
			if (System.currentTimeMillis() > next_game_frame) {
				frames++;
				next_game_frame += SKIP_FRAMES;
				display();
			}
			if (System.currentTimeMillis() > next_frame_calc) {
				next_frame_calc += SECOND;
				frames = 0;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void display() {
		for (Pantalla pantalla : pantallas) {
			pantalla.repaint();
		}
	}
	
	public class compararPuntuacion implements Comparator<Jugador> {

	    @Override
	    public int compare(Jugador o1, Jugador o2) {
	        return o2.getPuntuacion() - o1.getPuntuacion();
	    }
	}
	

	private void update() {
		for (Pantalla pantalla : pantallas) {
			pantalla.update();
		}
		for (Jugador jugador : jugadores) {
			jugador.manejarSalto((int) (getContentPane().getHeight() * GROUND_HEIGHT_PERCENTAGE));
			if (jugador.isVivo()) {
				jugador.setPuntuacion(jugador.getPuntuacion() + 1);
			}
		}
		int cantVivos = 0;
		for (Jugador jugador : jugadores) {
			if (jugador.isVivo()) {
				cantVivos++;
			}
		}
		if (cantVivos == 0) {
			isRunning = false;
			JFrame frame = new JFrame();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(700, 250, 450, 614);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel lblNewLabel = new JLabel("Fin de la Partida");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

			lblNewLabel.setBounds(84, 11, 261, 58);
			contentPane.add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Ganador:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(104, 80, 93, 14);
			contentPane.add(lblNewLabel_1);
			
			jugadores.sort(new compararPuntuacion());
			
			JLabel lblNewLabel_2 = new JLabel(jugadores.get(0).getNombre());
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_2.setBounds(199, 77, 133, 20);
			contentPane.add(lblNewLabel_2);

			String[] header = { "Posición", "Jugador", "Puntuación" };
			Object[][] data = new Object[jugadores.size()][3];
			for (int i = 0; i < jugadores.size(); i++) {
				data[i][0] = i + 1;
				data[i][1] = jugadores.get(i).getNombre();
				data[i][2] = jugadores.get(i).getPuntuacion();
			}

			JTable table = new JTable(data, header);
			table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			JScrollPane pane = new JScrollPane(table);
			pane.setBounds(71, 108, 297, 16 * jugadores.size() + 25);
			contentPane.add(pane);
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\franc\\Documents\\GitHub\\TheExceptions\\auxilio-me.gif"));
			lblNewLabel_3.setBounds(38, 207, 361, 193);
			contentPane.add(lblNewLabel_3);
			
			JButton btnNewButton = new JButton("Reiniciar");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
			btnNewButton.setBounds(38, 438, 361, 41);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Salir");
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
			btnNewButton_1.setBounds(38, 503, 361, 41);
			contentPane.add(btnNewButton_1);

			// center the jframe on screen
			// frame.setLocationRelativeTo(null);			
			frame.add(contentPane);
			frame.setVisible(true);
		}

	}

}
