package umu.tds.herramientas;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Etiqueta;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Video;

public class GeneradorPDF {
	
	public static ImageIcon getIcon(String imageUrl, float factor) {

		try {
			// Leer la imagen
			BufferedImage img = ImageIO.read(new File(imageUrl));
			// Obtenemos la proporcion ancho / altura.
			float proporcion = img.getWidth() / ((float) img.getHeight());
			// Obtenemos la Fuente (letra) por defecto especificada por el SO para un
			// textPane.
			Font font = UIManager.getDefaults().getFont("TextPane.font");
			// Obtenemos el tamaÃƒÂ±o de letra.
			int tamanoLetra = font.getSize();

			// Se reeescala la iamgen.
			Image newimg = img.getScaledInstance(Math.round(factor * tamanoLetra * proporcion), // Anchura: tamaÃƒÂ±o de
																								// la letra multiplicado
																								// por la proporcion
																								// original.
					Math.round(factor * tamanoLetra), // altura: tamaÃƒÂ±o de la letra
					java.awt.Image.SCALE_SMOOTH // MÃƒÂ©todo para reescalar (Calidad:SCALE_SMOOTH o rapidez SCALE_FAST)
			);
			// Se crea un ImageIcon
			return new ImageIcon(newimg);
		} catch (IOException e) {
			return null;
		}
	}
	private static void fillPDF(Document doc) throws DocumentException {
		
		doc.open();
		doc.add(new Paragraph("                                                     AppVideo(c) Premium Document"));
		doc.add(new Paragraph("\n\n"));
		doc.add(new Paragraph(
				"Este documento contiene todas las playlists con todos los videos que las componen.\n\n"));
		for (PlayList pl : Controlador.getUnicaInstancia().getPlayList()) {
			doc.add(new Paragraph(
					"Nombre de PlayList: " + pl.getNombre() + "\nNumero de videos: " + pl.getNumVideos() + ".\n"));
			for (Video v : pl.getListaVideos()) {
				doc.add(new Paragraph("Titulo: " + v.getTitulo() + "\nNumero de Reproducciones: " + v.getNumRepro() + "\n"));
			}	
			doc.add(new Paragraph("_______________________________________________________________________\n\n"));
		}
		doc.close();
	}
	
	public static void generarPDF(JFrame frame) {
		if (Controlador.getUnicaInstancia().getUsuarioActual().getPremiun()) {
			String sep = System.getProperty("os.name").startsWith("Windows") ? "\\" : "/";
			String ruta = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
			try {
				FileOutputStream archivo = new FileOutputStream(ruta + sep + "AppVideo_Summary.pdf");
				Document documento = new Document();
				PdfWriter.getInstance(documento, archivo);
				fillPDF(documento);
				JOptionPane.showMessageDialog(frame, "Pdf enviado a \"" + ruta + "\"", "¡PDF generado correctamente!",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frame, "Error: No se ha podido crear el pdf", "PDF: Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (DocumentException e) {
				JOptionPane.showMessageDialog(frame, "Error: No se ha podido crear el pdf", "PDF: Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(frame, "No eres un usuario premium");
		}
	}

}
