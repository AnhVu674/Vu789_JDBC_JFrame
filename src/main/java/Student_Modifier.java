import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Student_Modifier {
	public static List<Student> showAll() {
		Connection con = null;
		Statement st = null;
		List<Student> listStudent = new ArrayList<Student>();
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_students", "root", "");
			st = (Statement) con.createStatement();
			String sql = "select * from students ";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Student sv = new Student(rs.getInt("id"), rs.getString("fullName"), rs.getString("email"),
						rs.getString("address"));
				listStudent.add(sv);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
		return listStudent;

	}

	public static void insert(Student d) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_students", "root", "");
			String sql = "insert into students(id, fullname, email, address) values(?,?,?,?) ";
			st = (PreparedStatement) con.prepareCall(sql);
			st.setInt(1, d.getId());
			st.setString(2, d.getFullName());
			st.setString(3, d.getEmail());
			st.setString(4, d.getAdress());
			st.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
	}

	public static void update(Student d) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_students", "root", "");
			String sql = "update students set fullname=?,email=?,address=?, where id=?";
			st = (PreparedStatement) con.prepareCall(sql);
			st.setString(2, d.getFullName());
			st.setString(3, d.getEmail());
			st.setString(4, d.getAdress());
			st.setInt(1, d.getId());
			st.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
	}

	public static void delete(int id) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_students", "root", "");
			String sql = "delete from students where id=? ";
			st = (PreparedStatement) con.prepareCall(sql);
			st.setInt(1, id);
			st.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
	}

	public static List<Student> find(String fullname) {
		Connection con = null;
		PreparedStatement st = null;
		List<Student> listStudent = new ArrayList<Student>();
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_students", "root", "");
			String sql = "select * from students where fullname like ?";
			st = (PreparedStatement) con.prepareCall(sql);
			st.setString(1, "%" + fullname + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Student sv = new Student(rs.getInt("id"), rs.getString("fullName"), rs.getString("email"),
						rs.getString("address"));
				listStudent.add(sv);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
		return listStudent;

	}
}
