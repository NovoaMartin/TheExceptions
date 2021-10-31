import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class prueba extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba frame = new prueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 614);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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

		JLabel lblNewLabel_2 = new JLabel("Nombre jugador");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(199, 77, 133, 20);
		contentPane.add(lblNewLabel_2);

		String[] columnNames = { "Posición", "Jugador", "Puntuación"};

		Object[][] data = { { "1", "Smith", 45 },
				{ "2", "Doe", "Rowing" },
				{ "3", "Black", "Knitting" },
				{ "4", "White", "Speed reading" },
				{ "5", "Brown", "Pool" }};

		JTable table = new JTable(data, columnNames);
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(71, 108, 297, 89);
		contentPane.add(pane);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
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
	}
}
