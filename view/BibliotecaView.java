package view;

import javax.swing.*;
import java.util.ArrayList;

public class BibliotecaView {
    private final JFrame frame;
    private final JPanel panel;

    public BibliotecaView() {
        frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    public void adicionarBotao(JButton botao) {
        panel.add(botao);
    }

    public void exibir() {
        frame.setVisible(true);
    }

    public String solicitarInput(String mensagem) {
        return JOptionPane.showInputDialog(frame, mensagem);
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(frame, mensagem);
    }

    public void exibirLista(String titulo, ArrayList<String> itens) {
        StringBuilder sb = new StringBuilder(titulo).append("\n");
        for (String item : itens) {
            sb.append(item).append("\n");
        }
        JOptionPane.showMessageDialog(frame, sb.toString());
    }
}