public class Persona {
    private int id;
    private String nombre;
    private int edad;
    private Integer dir;
    public Persona( int id, String nombre, int edad, Integer dir) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.dir = dir;
    }
    public int getId() {  return id;  }
    public String getNombre() {  return nombre;  }
    public int getEdad() {  return edad;  }
    public Integer getDir() {  return dir;  }

    @Override
    public String toString(){
        return "id: "+this.id+" | Persona: "+this.nombre+" - Edad: "+this.edad+" - Dirección: "+this.dir;
    }
}
