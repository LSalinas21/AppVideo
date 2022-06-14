package umu.tds.dominio;

public class Usuario {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String nick;
	private String password;
	private String fechaNacimiento;
	
	public Usuario(String nombre, String apellidos, String email, String nick, String password,
			String fechaNacimiento) {
		this.id=0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nick = nick;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
	}
/**
	public Usuario() {
		
		
		
	}
*/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}
	
	

}
