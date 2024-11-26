package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {
    private final ArrayList<Livro> livros = new ArrayList<>();
    private final HashMap<String, Usuario> usuarios = new HashMap<>();

    public boolean cadastrarLivro(String titulo) {
        if (buscarLivro(titulo) != null) {
            return false;
        }
        livros.add(new Livro(titulo));
        return true;
    }

    public boolean cadastrarUsuario(String nome) {
        if (usuarios.containsKey(nome)) {
            return false;
        }
        usuarios.put(nome, new Usuario(nome));
        return true;
    }

    public Usuario buscarUsuario(String nome) {
        return usuarios.get(nome);
    }

    public Livro buscarLivro(String titulo) {
        return livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public boolean removerLivro(String titulo) {
        return livros.removeIf(livro -> livro.getTitulo().equalsIgnoreCase(titulo));
    }

    public boolean removerUsuario(String nome) {
        return usuarios.remove(nome) != null;
    }

    public ArrayList<Livro> listarLivros() {
        return livros;
    }

    public ArrayList<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}
