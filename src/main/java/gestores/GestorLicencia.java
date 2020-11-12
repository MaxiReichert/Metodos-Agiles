/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAOLicenciaJPA;
import dto.DTOLicencia;
import dto.DTOTitular;

/**
 *
 * @author Maxi
 */
public class GestorLicencia {
    
	public void ImprimirTicket(DTOTitular dtoTitular, DTOLicencia dtoLicencia) throws IOException, DocumentException{
		DAOLicenciaJPA daoLicencia= new DAOLicenciaJPA(); //creo una instancia de la DAO de licencia
		String numeroFactura= Long.toString(daoLicencia.ObtenerNumeroFactura()); //obtengo el numero de la factura
		Document documentoTicket= new Document(); //creo documento
        documentoTicket.setPageSize(PageSize.A4); //seteo tamaño de pagina
        FileOutputStream ficheroPdf= new FileOutputStream("pdf\ticket.pdf"); //creo el fichero
        PdfWriter.getInstance(documentoTicket,ficheroPdf).setInitialLeading(20); //obtengo una instancia del writer y seteo interlineado
        documentoTicket.open(); //abro documento
        
        //creo dos fuentes para el texto del archivo
        Font font1= new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        Font font2= new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
        
        Image tipoFactura= Image.getInstance("src\\main\\resources\\imagenes\\Logo tipo factura.jpg"); //obtengo la imagen
        tipoFactura.scaleToFit(35,35); //ajusto el tamaño
        tipoFactura.setAbsolutePosition(300, 770); //seteo la posicion
        documentoTicket.add(tipoFactura); //agrego la imagen al documento
        
        /* setFont establece la fuente para el parrafo
         * setTabSettings establece un tabulado para el parrafo*/
        
        Paragraph fact= new Paragraph();
        fact.setFont(font2);
        fact.setTabSettings(new TabSettings(328));
        fact.add(Chunk.TABBING); //agrego el tabulaod
        fact.add(new Chunk("FACTURA")); //agrego el texto
        documentoTicket.add(fact);
        
        Paragraph numFact= new Paragraph();
        numFact.setFont(font2);
        numFact.setTabSettings(new TabSettings(328));
        numFact.add(Chunk.TABBING);
        numFact.add(new Chunk("Nº "+ numeroFactura));
        documentoTicket.add(numFact);
        documentoTicket.add(Chunk.NEWLINE); //agrego una nueva liena en blanco

        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy"); //establesco el formato para la fecha
        String fechaTexto= formatter.format(new Date()); //obtengo la fecha del sistema y la formateo
        Paragraph fecha= new Paragraph();
        fecha.setFont(font1);
        fecha.setTabSettings(new TabSettings(328));
        fecha.add(Chunk.TABBING);
        fecha.add(new Chunk("FECHA "+fechaTexto));
        documentoTicket.add(fecha);
        
        documentoTicket.add(Chunk.NEWLINE);
        documentoTicket.add(new Paragraph("Centro Municipal de Eduación Vial",font2)); //agrego un parrafo al documento
        
	}
}
