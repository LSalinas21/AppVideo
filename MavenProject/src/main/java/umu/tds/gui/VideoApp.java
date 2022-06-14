package  umu.tds.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tds.video.VideoWeb;

public class VideoApp extends JFrame {
	private JPanel contentPane;
	private static VideoWeb videoWeb = new VideoWeb();
	private  JLabel titulo;
	private  JLabel miniatura;

	public VideoApp() {
		
		setDefaultCloseOperation(0);
		setBounds(0, 0, 410, 500);
		

		contentPane = (JPanel) getContentPane();
		contentPane.setSize(new Dimension(730, 630)); //20 de barra de t�tulo
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		titulo = new JLabel("Sin titulo",JLabel.CENTER);
		titulo.setMinimumSize(new Dimension(760, 60));
		titulo.setPreferredSize(new Dimension(760, 60));
		titulo.setMaximumSize(new Dimension(760, 60));
		Font ftit= new Font ("Arial",Font.BOLD,24);
		titulo.setFont(ftit);
		titulo.setAlignmentX(0.5f); //centrar
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel botones = new JPanel ();
		botones.setMinimumSize(new Dimension(760, 60));
		botones.setPreferredSize(new Dimension(760, 60));
		botones.setMaximumSize(new Dimension(760, 60));
		botones.setLayout(new FlowLayout());

		JButton btCancelar = new JButton("Cerrar");
		btCancelar.setBackground(Color.RED);
		btCancelar.setForeground(Color.WHITE);
		botones.add(btCancelar);

		JLabel reprod=new JLabel("reproduciendo:");
		reprod.setAlignmentX(0.5f);
		miniatura= new JLabel();
		miniatura.setAlignmentX(0.5f);

		JLabel copyright=new JLabel(videoWeb.getVersion());
		copyright.setAlignmentX(0.5f);
		contentPane.add(titulo);
		contentPane.add(videoWeb);
		contentPane.add(botones);
		contentPane.add(reprod);
		contentPane.add(miniatura);
		contentPane.add(copyright);

		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titulo.setText("Sin titulo");
				miniatura.setIcon(null);
				videoWeb.cancel();
				dispose();
			}
		});
		
	}
	public void reproducir(String tituloV, String url) {
		
		titulo.setText(tituloV);
		miniatura.setIcon(videoWeb.getThumb(url));
		videoWeb.playVideo(url);
		setVisible(true);
		
		
	}

}
