import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Student_JFame extends JFrame {
	DefaultTableModel dtm;
	List<Student> studentList = new ArrayList<Student>();

	private JPanel contentPane;
	private JTextField textId;
	private JTextField textFullname;
	private JTextField textEmail;
	private JTextField textAdress;
	public JTable table_sv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_JFame frame = new Student_JFame();
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
	public Student_JFame() {
		table_sv = new JTable();
		table_sv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_sv.setModel(
				new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"ID", "FullName", "Email", "Adress"
			}
		));
		dtm = (DefaultTableModel) table_sv.getModel();
		showStudent();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 747);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textId = new JTextField();
		textId.setBounds(158, 37, 597, 36);
		contentPane.add(textId);
		textId.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 37, 108, 36);
		contentPane.add(lblNewLabel);

		JLabel lblFullname = new JLabel("Fullname");
		lblFullname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFullname.setBounds(10, 95, 108, 36);
		contentPane.add(lblFullname);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 156, 108, 36);
		contentPane.add(lblEmail);

		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdress.setBounds(10, 225, 108, 36);
		contentPane.add(lblAdress);

		textFullname = new JTextField();
		textFullname.setColumns(10);
		textFullname.setBounds(158, 95, 597, 36);
		contentPane.add(textFullname);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(158, 156, 597, 36);
		contentPane.add(textEmail);

		textAdress = new JTextField();
		textAdress.setColumns(10);
		textAdress.setBounds(158, 225, 597, 36);
		contentPane.add(textAdress);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// int id = Integer.parseInt(textId.getText());
				String fullname = textFullname.getText();
				String email = textEmail.getText();
				String adress = textAdress.getText();
				Student sv1 = new Student(fullname, email, adress);
				Student_Modifier.insert(sv1);
				// showStudent();
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(54, 370, 94, 36);
		contentPane.add(btnSave);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textId.setText("");
				textFullname.setText("");
				textEmail.setText("");
				textAdress.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBounds(202, 370, 94, 36);
		contentPane.add(btnReset);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectIndex = table_sv.getRowCount();
				System.out.println(selectIndex);
				if (selectIndex >= 0) {
					List<Student> listStudent = Student_Modifier.showAll();
					Student sv = listStudent.get(selectIndex - 1);
					int option = JOptionPane.showConfirmDialog(null, "Do you want delete");
					System.out.println("option:" + option);
					if (option == 0) {
						Student_Modifier.delete(sv.getId());
						showStudent();
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(353, 370, 94, 36);
		contentPane.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(512, 370, 113, 36);
		contentPane.add(btnUpdate);

		JScrollPane scrollPane = new JScrollPane(table_sv);
		scrollPane.setBounds(10, 446, 852, 254);
		contentPane.add(scrollPane);

		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStudent();
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnShow.setBounds(661, 370, 94, 36);
		contentPane.add(btnShow);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(this, "Enter fullname to search:");
				if (input != null && input.length() > 0) {
					studentList = Student_Modifier.find(input);
					for (Student student : studentList) {
						dtm.setRowCount(0);
						dtm.addRow(new Object[] { dtm.getRowCount() + 1, student.getFullName(), student.getEmail(),
								student.getAdress() });
					}
				} else {
					showStudent();
				}
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFind.setBounds(54, 305, 701, 36);
		contentPane.add(btnFind);

	}

	private void showStudent() {
		List<Student> listStudent = Student_Modifier.showAll();
		for (Student student : listStudent) {
			System.out.println(student);
			dtm.setRowCount(0);
			dtm.addRow(new Object[] { dtm.getRowCount() + 1, student.getFullName(), student.getEmail(),
					student.getAdress() });
		}
	}
}