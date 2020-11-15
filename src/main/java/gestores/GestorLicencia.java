/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import java.io.File;
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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

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
        FileOutputStream ficheroPdf= new FileOutputStream("ticket.pdf"); //creo el fichero
        PdfWriter.getInstance(documentoTicket,ficheroPdf).setInitialLeading(20); //obtengo una instancia del writer y seteo interlineado
        documentoTicket.open(); //abro documento
        
        //creo dos fuentes para el texto del archivo
        Font font1= new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        Font font2= new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
        
        //Tipo de facura
        Image tipoFactura= Image.getInstance("src\\main\\resources\\imagenes\\Logo tipo factura.jpg"); //obtengo la imagen
        tipoFactura.scaleToFit(35,35); //ajusto el tamaño
        tipoFactura.setAbsolutePosition(300, 770); //seteo la posicion
        documentoTicket.add(tipoFactura); //agrego la imagen al documento
        
        /* setFont establece la fuente para el parrafo
         * setTabSettings establece un tabulado para el parrafo*/
        
        //numero de factura
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

        //fecha de facturacion
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy"); //establesco el formato para la fecha
        String fechaTexto= formatter.format(new Date()); //obtengo la fecha del sistema y la formateo
        Paragraph fecha= new Paragraph();
        fecha.setFont(font1);
        fecha.setTabSettings(new TabSettings(328));
        fecha.add(Chunk.TABBING);
        fecha.add(new Chunk("FECHA "+fechaTexto));
        documentoTicket.add(fecha);
        
        documentoTicket.add(Chunk.NEWLINE);
        //Nombre del lugar donde se facturo
        documentoTicket.add(new Paragraph("Centro Municipal de Eduación Vial",font2)); //agrego un parrafo al documento
        
        //Dirección y cuit del lugar donde se facturo
        Paragraph dirCuit= new Paragraph();
        dirCuit.setFont(font1);
        dirCuit.setTabSettings(new TabSettings(328));
        dirCuit.add(new Chunk("SALVADOR CAPUTTO 3800"));
        dirCuit.add(Chunk.TABBING);
        dirCuit.add(new Chunk("CUIT 12345678910"));
        documentoTicket.add(dirCuit);
        
        documentoTicket.add(new Paragraph("SANTA FE - SANTA FE",font1));
        documentoTicket.add(new Paragraph("(0342) 4574166",font1)); //numero de telefono del Centro de Educacion Vial
        
        Chunk linebreak= new Chunk(new LineSeparator());//creo una linea separadora
        documentoTicket.add(linebreak); //agrego la linea separadora
        
        //nombre y apellido del titular
        documentoTicket.add(new Paragraph("Señores: "+dtoTitular.getApellido().toUpperCase()+" "
        		+dtoTitular.getNombre().toUpperCase(), font1));
        
        //direccion del titular
        documentoTicket.add(new Paragraph("Domicilio: "+dtoTitular.getDireccion().toUpperCase(), font1));
        
        //Categoria y condición de pago
        Paragraph condPago= new Paragraph();
        condPago.setFont(font1);
        condPago.setTabSettings(new TabSettings(328));
        condPago.add(new Chunk("Cat. IVA: CONSUMIDOR FINAL"));
        condPago.add(Chunk.TABBING);
        condPago.add(new Chunk("Condición de pago: CONTADO"));
        documentoTicket.add(condPago);
        documentoTicket.add(linebreak);
        
        PdfPTable tabla= new PdfPTable(4); //creo tabla de 4 columnas
        float[] anchos= new float[4]; //ancho de cada columna
        anchos[0]=75f;
        anchos[1]=30f;
        anchos[2]=45f;
        anchos[3]=25f;
        tabla.setWidths(anchos);
        tabla.setWidthPercentage(100);
        
        /* setBorderWidth (Left, Right, Top, Down) determina el ancho de la linea de borde
         * addElement agrega un elemento a la celda*/
        
        //Encabezados de las columnas
        PdfPCell headerColConcepto= new PdfPCell();
        Paragraph headerColConceptoText= new Paragraph("Concepto");
        headerColConcepto.setBorderWidthLeft(0f);
        headerColConcepto.setBorderWidthRight(0f);
        headerColConcepto.setBorderWidthTop(0f);
        headerColConcepto.addElement(headerColConceptoText);
        
        PdfPCell headerColCantidad= new PdfPCell();
        Paragraph headerColCantidadText= new Paragraph("Cantidad");
        headerColCantidad.setBorderWidthLeft(0f);
        headerColCantidad.setBorderWidthRight(0f);
        headerColCantidad.setBorderWidthTop(0f);
        headerColCantidad.addElement(headerColCantidadText);
        
        PdfPCell headerColPrecUnit= new PdfPCell();
        Paragraph headerColPrecUnitText= new Paragraph("Precio Unitario");
        headerColPrecUnit.setBorderWidthLeft(0f);
        headerColPrecUnit.setBorderWidthRight(0f);
        headerColPrecUnit.setBorderWidthTop(0f);
        headerColPrecUnit.addElement(headerColPrecUnitText);
        
        PdfPCell headerColImporte= new PdfPCell();
        Paragraph headerColImporteText= new Paragraph("Importe");
        headerColImporte.setBorderWidthLeft(0f);
        headerColImporte.setBorderWidthRight(0f);
        headerColImporte.setBorderWidthTop(0f);
        headerColImporte.addElement(headerColImporteText);
        
        //El concepto que se va a pagar
        PdfPCell conceptoCell= new PdfPCell();
        Paragraph concepto= new Paragraph("LICENCIA DE CONDUCIR CLASE "+dtoLicencia.getTipo());
        conceptoCell.setBorderWidthLeft(0f);
        conceptoCell.setBorderWidthRight(0f);
        conceptoCell.setBorderWidthTop(0f);
        conceptoCell.addElement(concepto);
        
        //Cantidad de Licencias Facturadas del mismo tipo
        int cantidadLicencia=1;
        PdfPCell cantidadCell= new PdfPCell();
        Paragraph cantidad= new Paragraph(Integer.toString(cantidadLicencia));
        cantidadCell.setBorderWidthLeft(0f);
        cantidadCell.setBorderWidthRight(0f);
        cantidadCell.setBorderWidthTop(0f);
        cantidadCell.addElement(cantidad);
        
        //Costo unitario de la licencia
        PdfPCell precUnitCell= new PdfPCell();
        Paragraph precUnit= new Paragraph("$"+Integer.toString(dtoLicencia.getCosto()));
        precUnitCell.setBorderWidthLeft(0f);
        precUnitCell.setBorderWidthRight(0f);
        precUnitCell.setBorderWidthTop(0f);
        precUnitCell.addElement(precUnit);
        
        //Importe a pagar por la licencia
        PdfPCell importeCell= new PdfPCell();
        Paragraph importe= new Paragraph("$"+Integer.toString(cantidadLicencia*dtoLicencia.getCosto()));
        importeCell.setBorderWidthLeft(0f);
        importeCell.setBorderWidthRight(0f);
        importeCell.setBorderWidthTop(0f);
        importeCell.addElement(importe);
        
        //celdas vacias para saltear lugares
        PdfPCell vacia= new PdfPCell();
        vacia.setBorderWidthLeft(0f);
        vacia.setBorderWidthRight(0f);
        vacia.setBorderWidthTop(0f);
        
        //Texto TOTAL
        PdfPCell headerTotal= new PdfPCell();
        Paragraph headerTotalText= new Paragraph("Total");
        headerTotal.setBorderWidthLeft(0f);
        headerTotal.setBorderWidthRight(0f);
        headerTotal.setBorderWidthTop(0f);
        headerTotal.addElement(headerTotalText);
        
        //Total a pagar
        PdfPCell totalCell= new PdfPCell();
        Paragraph total= new Paragraph("$"+Integer.toString(cantidadLicencia*dtoLicencia.getCosto()));
        totalCell.setBorderWidthLeft(0f);
        totalCell.setBorderWidthRight(0f);
        totalCell.setBorderWidthTop(0f);
        totalCell.addElement(total);
        
        //agrego las celdas a la tabla
        tabla.addCell(headerColConcepto);
        tabla.addCell(headerColCantidad);
        tabla.addCell(headerColPrecUnit);
        tabla.addCell(headerColImporte);
        tabla.addCell(conceptoCell);
        tabla.addCell(cantidadCell);
        tabla.addCell(precUnitCell);
        tabla.addCell(importeCell);
        tabla.addCell(vacia);
        tabla.addCell(vacia);
        tabla.addCell(headerTotal);
        tabla.addCell(totalCell);
        documentoTicket.add(tabla);
        
        documentoTicket.close();//cierro el documento
        
        String path= new File("ticket.pdf").getAbsolutePath(); //obtengo la ruta absoluta del pdf
        
        //abro el pdf
        Process process= Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+path);
        
	}
}
