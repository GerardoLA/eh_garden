package principal;

public class Arbol {

		private int id;
		private String nombreComun;
		private String nombrecientifico;
		private int altura;
		private String origen;
		
		public Arbol() {};
		
		
		
		
		public Arbol(int id, String nombreComun, String nombrecientifico, int altura, String origen) {

			this.id = id;
			this.nombreComun = nombreComun;
			this.nombrecientifico = nombrecientifico;
			this.altura = altura;
			this.origen = origen;
		}




		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombreComun() {
			return nombreComun;
		}
		public void setNombreComun(String nombreComun) {
			this.nombreComun = nombreComun;
		}
		public String getNombrecientifico() {
			return nombrecientifico;
		}
		public void setNombrecientifico(String nombrecientifico) {
			this.nombrecientifico = nombrecientifico;
		}
		public int getAltura() {
			return altura;
		} 
		
		
		public void setAltura(int altura) {
			this.altura = altura;
		}
		public String getOrigen() {
			return origen;
		}
		public void setOrigen(String origen) {
			this.origen = origen;
		}
	
	
}
