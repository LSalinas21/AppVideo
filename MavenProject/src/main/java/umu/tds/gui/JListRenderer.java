package umu.tds.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Video;

public class JListRenderer {
	
	private static JListRenderer instancia;
	
	private JListRenderer() {
		
	}
	
	public static JListRenderer getInstancia() {
		if(instancia == null)
			instancia = new JListRenderer();
		
		return instancia;
	}
	public JList<Video> getListaR(List<Video> videos){
		
		JList<Video> jList = new JList<Video>(videos.toArray(new Video[videos.size()]));
	    jList.setCellRenderer(createListRenderer());
	    
	    return jList;
	}

	 private static ListCellRenderer<? super Video> createListRenderer() {
	      return new DefaultListCellRenderer() {
	          private Color background = new Color(0, 100, 255, 15);
	          private Color defaultBackground = (Color) UIManager.get("List.background");

	          @Override
	          public Component getListCellRendererComponent(JList<?> list, Object value, int index,
	                                                        boolean isSelected, boolean cellHasFocus) {
	              Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	              if (c instanceof JLabel) {
	                  JLabel label = (JLabel) c;
	                  Video emp = (Video) value;
	                  label.setIcon(Controlador.getUnicaInstancia().getIcono(emp.getUrl()));
	                  label.setText(emp.getTitulo() +"      Num Repro: " + emp.getNumRepro());
	                  if (!isSelected) {
	                      label.setBackground(index % 2 == 0 ? background : defaultBackground);
	                  }
	              }
	              return c;
	          }
	      };
	  }
}
