package connectarBD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class AplicacionConsulta {

	public static void main(String[] args) {

		marcobd marco = new marcobd();

		marco.setVisible(true);

		marco.setDefaultCloseOperation(3);

	}

}

class marcobd extends JFrame {
private	JComboBox secciones;
private	JComboBox paises;
private JTextArea resultado;
private PreparedStatement enviaconsulpais;	
private PreparedStatement enviaconsul;	
private PreparedStatement enviaconsultodos;
private final String consultaselect="select seccion ,nombrearticulo,precio from producto where seccion=?";
private final String consultapais="select seccion, nombrearticulo, precio from producto where paisdeorigen= ?";
private final String consulttodos="select seccion ,nombrearticulo, precio from producto where seccion=? and paisdeorigen=?";
 private Connection conexion;
 
	public marcobd() {

		setTitle("consulta bd-Breinner");

		setBounds(100, 100, 350, 350);

		setLayout(new BorderLayout());

		JPanel menus = new JPanel();

		secciones = new JComboBox();
		secciones.setEditable(false);
		secciones.addItem("todos");

		paises = new JComboBox();
		paises.setEditable(false);
		paises.addItem("todos");

		resultado = new JTextArea(4, 50);
		resultado.setEditable(false);

		menus.add(secciones);
		menus.add(paises);

		add(menus, BorderLayout.NORTH);
		add(resultado, BorderLayout.CENTER);

		JButton boton = new JButton("boton");
		boton.addActionListener(new ActionListener() { // accion del boton 

			@Override
			public void actionPerformed(ActionEvent e) {
				resultado.setText("");
				setejecutaconsul();
			}
			
			
			
			
		});
		
		add(boton, BorderLayout.SOUTH);

		// CONEXION DE BASE DE DATOS

		
		String usuario = "root";
		String contraseña = "pon tu contraseña";
		String link = "jdbc:mysql://localhost:5019/pildoras";

		try {

			conexion = DriverManager.getConnection(link, usuario, contraseña);
			Statement st = conexion.createStatement();
			String consulta = "select distinctrow seccion from producto ";
			ResultSet rs = st.executeQuery(consulta);

			while (rs.next()) {

				String seccion = rs.getString("SECCION");

				this.secciones.addItem(seccion);
			}
			rs.close();
			

			// OTRA CONSULTA PARA PAISES 
			
			consulta = "select distinctrow paisdeorigen from producto ";
			 rs = st.executeQuery(consulta);
			 
			 while (rs.next()) {

				String pais = rs.getString("paisdeorigen");

				this.paises.addItem(pais);
			}
			rs.close();
			
			

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private void setejecutaconsul() {
													//METODO PARA CONSULTA PREPARADAS
		ResultSet rs=null;
		
		
		try {
			
			String seccion=(String)secciones.getSelectedItem();
			String pais=(String)paises.getSelectedItem();
			
		    if (!seccion.equals("todos")&& pais.equals("todos"))  {
		    	
		    	enviaconsul = conexion.prepareStatement(consultaselect);
				
				enviaconsul.setString(1, seccion);
				
			    	rs = enviaconsul.executeQuery();
			    
			    
		    } else if(seccion.equals("todos") && !pais.equals("todos")) {
		    	
		    	enviaconsulpais = conexion.prepareStatement(consultapais);
				
				enviaconsulpais.setString(1, pais);
				
				rs = enviaconsulpais.executeQuery();
			
					
		    } else if(!seccion.equals("todos")  && !pais.equals("todos")) {
		    	
		    	
		    			
		    		enviaconsultodos = conexion.prepareStatement(consulttodos);
				
		    		enviaconsultodos.setString(1, seccion);
		    		enviaconsultodos.setString(2, pais);
				
		    		rs = enviaconsultodos.executeQuery();
		    	
		    	
		    }
		    	
		
			    while (rs.next()) {
			    	
			       resultado.append((rs.getString(1)+", "+rs.getString(2)+", "+rs.getDouble(3) ));
			       resultado.append("\n");
			    	
			    }
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
}


