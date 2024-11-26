package model;

public class Livro {
    private final String titulo;
    private boolean disponivel = true;
    private String alugadoPara = null;

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getAlugadoPara() {
        return alugadoPara;
    }

    public void alugar(String usuario) {
        this.disponivel = false;
        this.alugadoPara = usuario;
    }

    public void devolver() {
        this.disponivel = true;
        this.alugadoPara = null;
    }
}