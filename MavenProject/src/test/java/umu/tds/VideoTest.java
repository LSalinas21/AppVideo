package umu.tds;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import umu.tds.dominio.Etiqueta;
import umu.tds.dominio.Video;

public class VideoTest {
	
	
	
	@Test
	public void testVideosPorEtiqueta() {
		
		List<Etiqueta> listaEtiquetaVideo1 = new ArrayList<Etiqueta>();
		List<Etiqueta> listaEtiquetaVideo2 = new ArrayList<Etiqueta>();
		List<Etiqueta> listaEtiquetaVideo3 = new ArrayList<Etiqueta>();
		
		Etiqueta et1 = new Etiqueta("eti1");
		Etiqueta et2 = new Etiqueta("eti2");
		
		Video vTest1 = new Video("titulo1","url1",0);
		Video vTest2 = new Video("titulo2","url2",0);
		Video vTest3 = new Video("titulo3","url3",0);
		
		vTest1.agregarEtiqueta("et1");
		vTest1.agregarEtiqueta("et2");
		
		vTest2.agregarEtiqueta("et1");
		
		vTest3.agregarEtiqueta("et2");
		
		listaEtiquetaVideo1.add(et1);
		listaEtiquetaVideo1.add(et2);
		listaEtiquetaVideo2.add(et1);
		listaEtiquetaVideo3.add(et2);
		
		assertEquals(vTest1.getEtiquetas(), listaEtiquetaVideo1);
		assertEquals(vTest2.getEtiquetas(), listaEtiquetaVideo2);
		assertEquals(vTest3.getEtiquetas(), listaEtiquetaVideo3);
	}

}
