public class Direccion {
    private int id;
    private Integer numero;
    private String calle;
    private String ciudad;
    public Direccion(int id, Integer numero, String calle, String ciudad) {
        this.id = id;
        this.numero = numero;
        this.calle = calle;
        this.ciudad = ciudad;
    }
    public int getId() {  return id;   }
    public Integer getNumero() {  return numero;  }
    public String getCalle() {  return calle;  }
    public String getCiudad() {  return ciudad;  }

    @Override
    public String toString(){
        return "id: "+this.id+" | Dirección: "+this.calle+" "+this.numero+" - "+this.ciudad;
    }
}
