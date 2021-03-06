package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import domain.*;

public class AnimalDAO implements IAnimalDAO{
	
	private ConexionDB conector = new ConexionDB();
	
	public AnimalDAO() {};
	
	@Override // Listado
	public ArrayList<Animal> listarAnimalesByCategoria(Categoria actual) {
		ArrayList<Animal> busqueda = new ArrayList<Animal>();
		
		return busqueda;
	}

	@Override //Detalle
	public ArrayList<Animal> listarAnimalByID(int id) {
		ArrayList<Animal> busqueda = new ArrayList<Animal>();
		
		return busqueda;
	}
	
	@Override // Buscador
	public ArrayList<Animal> listarAnimalesByPalabra(String palabra) {
		ArrayList<Animal> busqueda = new ArrayList<Animal>();
		
		return busqueda;
	}
	
	public boolean anadirAnimal(Animal nuevo) throws ClassNotFoundException, SQLException {
		String query = "INSERT INTO animales (id_animal, nombre, fecha_entrada, fecha_salida, descripcion, cabecera, estado)"
				+ "  VALUES('"+nuevo.getId()+"', '"+nuevo.getNombre()+"', '"+nuevo.getFechaEntrada()+"', null, '"
				+nuevo.getDescripcion().getCuerpo()+"', '"+nuevo.getDescripcion().getCabecera()+"', '"+nuevo.getEstado()+"')";
		return conector.anadir(query);
	}
	
	public boolean editarAnimal(Animal nuevo) throws ClassNotFoundException, SQLException {
		String query = "UPDATE animales SET nombre = '"+nuevo.getNombre()+"', fecha_entrada = '"+nuevo.getFechaEntrada()+
				"', fecha_salida = '"+nuevo.getFechaSalida()+"', descripcion = '"+nuevo.getDescripcion().getCuerpo()+
				"', cabecera = '"+nuevo.getDescripcion().getCabecera()+"', estado = '"+nuevo.getEstado()+
				"' WHERE id_animal = '"+nuevo.getId()+"'";
		return conector.modificar(query);
	}
	
	public boolean eliminarAnimal(Animal nuevo) throws ClassNotFoundException, SQLException {
		String query = "DELETE FROM animales WHERE '"+nuevo.getId()+"'";
		return conector.borrar(query);
	}	
	
	public Animal veranimal(Animal nuevo)throws ClassNotFoundException, SQLException{
		String query= "SELECT * FROM animales where ID_ANIMAL="+nuevo.getId();
		java.sql.ResultSet resultadoquery = conector.mostrar(query);
		Animal encontrado = new Animal();
		while(resultadoquery.next()){
			encontrado.setId(resultadoquery.getInt("ID_ANIMAL"));
			encontrado.setNombre(resultadoquery.getString("NOMBRE"));
			encontrado.setFechaEntrada(resultadoquery.getTime("FECHA_ENTRADA"));
			encontrado.setEstado((Categoria.valueOf(resultadoquery.getString("ESTADO"))));
			Descripcion aux = new Descripcion(resultadoquery.getString("CABECERA"),resultadoquery.getString("CUERPO"));
			encontrado.setDescripcion(aux);
			
		}
		return encontrado;
		
	}

}