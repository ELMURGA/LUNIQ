package Model;

public class Categoria {
    private int categoriaId;
    private String nombre;
    private String descripcion;
    private Integer categoriaPadreId;

    // Getters y setters
    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCategoriaPadreId() {
        return categoriaPadreId;
    }

    public void setCategoriaPadreId(Integer categoriaPadreId) {
        this.categoriaPadreId = categoriaPadreId;
    }
}