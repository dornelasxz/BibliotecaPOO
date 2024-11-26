package controller;

import model.Biblioteca;
import model.Livro;
import model.Usuario;
import view.BibliotecaView;

import javax.swing.*;
import java.util.ArrayList;

public class BibliotecaController {
    private final Biblioteca biblioteca;
    private final BibliotecaView view;

    public BibliotecaController(Biblioteca biblioteca, BibliotecaView view) {
        this.biblioteca = biblioteca;
        this.view = view;
        configurarBotoes();
    }

    private void configurarBotoes() {
        JButton btnCadastrarLivro = new JButton("Cadastrar Livro");
        btnCadastrarLivro.addActionListener(e -> cadastrarLivro());

        JButton btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        btnCadastrarUsuario.addActionListener(e -> cadastrarUsuario());

        JButton btnAlugarLivro = new JButton("Alugar Livro");
        btnAlugarLivro.addActionListener(e -> alugarLivro());

        JButton btnDevolverLivro = new JButton("Devolver Livro");
        btnDevolverLivro.addActionListener(e -> devolverLivro());

        JButton btnRemoverLivro = new JButton("Remover Livro");
        btnRemoverLivro.addActionListener(e -> removerLivro());

        JButton btnRemoverUsuario = new JButton("Remover Usuário");
        btnRemoverUsuario.addActionListener(e -> removerUsuario());

        JButton btnListarLivros = new JButton("Listar Livros");
        btnListarLivros.addActionListener(e -> listarLivros());

        JButton btnListarUsuarios = new JButton("Listar Usuários");
        btnListarUsuarios.addActionListener(e -> listarUsuarios());

        view.adicionarBotao(btnCadastrarLivro);
        view.adicionarBotao(btnCadastrarUsuario);
        view.adicionarBotao(btnAlugarLivro);
        view.adicionarBotao(btnDevolverLivro);
        view.adicionarBotao(btnRemoverLivro);
        view.adicionarBotao(btnRemoverUsuario);
        view.adicionarBotao(btnListarLivros);
        view.adicionarBotao(btnListarUsuarios);

        view.exibir();
    }

    private void cadastrarLivro() {
        String titulo = view.solicitarInput("Digite o título do livro:");
        if (titulo != null && !titulo.isEmpty()) {
            if (biblioteca.cadastrarLivro(titulo)) {
                view.exibirMensagem("Livro cadastrado com sucesso!");
            } else {
                view.exibirMensagem("Livro já cadastrado!");
            }
        }
    }

    private void cadastrarUsuario() {
        String nome = view.solicitarInput("Digite o nome do usuário:");
        if (nome != null && !nome.isEmpty()) {
            if (biblioteca.cadastrarUsuario(nome)) {
                view.exibirMensagem("Usuário cadastrado com sucesso!");
            } else {
                view.exibirMensagem("Usuário já cadastrado!");
            }
        }
    }

    private void alugarLivro() {
        String nomeUsuario = view.solicitarInput("Digite o nome do usuário:");
        Usuario usuario = biblioteca.buscarUsuario(nomeUsuario);

        if (usuario == null) {
            view.exibirMensagem("Usuário não encontrado!");
            return;
        }

        String tituloLivro = view.solicitarInput("Digite o título do livro:");
        Livro livro = biblioteca.buscarLivro(tituloLivro);

        if (livro == null) {
            view.exibirMensagem("Livro não encontrado!");
        } else if (!livro.isDisponivel()) {
            view.exibirMensagem("Livro já está alugado para " + livro.getAlugadoPara());
        } else {
            livro.alugar(usuario.getNome());
            view.exibirMensagem("Livro alugado com sucesso!");
        }
    }

    private void devolverLivro() {
        String tituloLivro = view.solicitarInput("Digite o título do livro:");
        Livro livro = biblioteca.buscarLivro(tituloLivro);

        if (livro == null) {
            view.exibirMensagem("Livro não encontrado!");
        } else if (livro.isDisponivel()) {
            view.exibirMensagem("O livro já está disponível!");
        } else {
            livro.devolver();
            view.exibirMensagem("Livro devolvido com sucesso!");
        }
    }

    private void removerLivro() {
        String titulo = view.solicitarInput("Digite o título do livro a ser removido:");
        if (titulo != null && !titulo.isEmpty()) {
            if (biblioteca.removerLivro(titulo)) {
                view.exibirMensagem("Livro removido com sucesso!");
            } else {
                view.exibirMensagem("Livro não encontrado!");
            }
        }
    }

    private void removerUsuario() {
        String nome = view.solicitarInput("Digite o nome do usuário a ser removido:");
        if (nome != null && !nome.isEmpty()) {
            if (biblioteca.removerUsuario(nome)) {
                view.exibirMensagem("Usuário removido com sucesso!");
            } else {
                view.exibirMensagem("Usuário não encontrado!");
            }
        }
    }

    private void listarLivros() {
        ArrayList<Livro> livros = biblioteca.listarLivros();
        ArrayList<String> lista = new ArrayList<>();
        for (Livro livro : livros) {
            lista.add(livro.getTitulo() + " - " +
                    (livro.isDisponivel() ? "Disponível" : "Alugado para " + livro.getAlugadoPara()));
        }
        view.exibirLista("Livros na Biblioteca:", lista);
    }

    private void listarUsuarios() {
        ArrayList<Usuario> usuarios = biblioteca.listarUsuarios();
        ArrayList<String> lista = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            lista.add(usuario.getNome());
        }
        view.exibirLista("Usuários cadastrados:", lista);
    }
}