package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract class Quadrado extends JPanel implements MouseListener, KeyListener {
	public final static int TAMANHO = 40;
	protected Color cor;
	protected char letra;
	protected JLabel labelLetra;

	public char getLetra() {
		return letra;
	}

	public Quadrado(int x, int y) {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.setVisible(true);
		this.setLayout(null);
		this.setBounds(x, y, TAMANHO, TAMANHO);
		cor = Color.white;
		letra = 0;
		labelLetra = new JLabel();
		labelLetra.setText(new Character(letra).toString());
		labelLetra.setVisible(true);
		labelLetra.setFont(new Font("Serif", Font.BOLD, (int) (TAMANHO * 0.8)));
		labelLetra
				.setBounds(6, 3, (int) (TAMANHO * 0.8), (int) (TAMANHO * 0.8));
		this.add(labelLetra);
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		this.setBackground(cor);
		super.paintComponent(g);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		this.setFocusable(true);
		this.requestFocus();
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		this.repaint();
	}

	public void mouseExited(MouseEvent e) {
		this.setFocusable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.repaint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void keyPressed(KeyEvent arg0) {
		String ipt = new Character(arg0.getKeyChar()).toString().toUpperCase();
		if (ipt.matches("[A-Z-]")) {
			labelLetra.setText(ipt);
		}
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}