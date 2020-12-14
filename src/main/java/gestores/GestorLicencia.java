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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Predicate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import dao.DAOLicenciaJPA;
import dao.DAOTitularJPA;
import dto.DTOLicencia;
import dto.DTOTitular;
import entidades.Licencia;
import entidades.Titular;
import entidades.Tramite;
import entidades.Usuario;
import exceptions.EmitirLicenciaException;

/**
 *
 * @author Maxi
 */
public class GestorLicencia {
    
	private final static long SECONDS_IN_YEAR = 31536000;

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

	public void ImprimirLicencia(DTOTitular dtoTitular, DTOLicencia dtoLicencia) throws IOException, DocumentException{
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); //formato fecha
		Document documento= new Document(); //creo el documento
        documento.setPageSize(PageSize.A4.rotate()); //establezco tamaño de pagina en A4, orientacion horizontal
        FileOutputStream ficheroPdf= new FileOutputStream("licencia.pdf");// creo el fichero
        PdfWriter writer= PdfWriter.getInstance(documento, ficheroPdf);// obtengo una isntancia de PdfWriter
        documento.open();// abro el documento
        
        
        PdfContentByte canvas= writer.getDirectContent(); //creo un contenedor para dibujar
        Rectangle rectAdelante= new Rectangle(50,430,275,545); //creo el rectangulo para la parte de adelante de la licencia
        rectAdelante.setBorder(Rectangle.BOX);// le seteo los bordes
        rectAdelante.setBorderWidth((float) 0.5);// seteo el ancho de los bordes
        canvas.rectangle(rectAdelante);// dobujo el rectangulo
        
        // creo las fuentes que voy a usar en el archivo
        Font fontLocalidad= new Font(FontFamily.TIMES_ROMAN, 7, Font.BOLD, BaseColor.BLACK);
        Font fontCatDatos= new Font(FontFamily.TIMES_ROMAN, 5, Font.NORMAL, BaseColor.BLACK);
        Font fontDatosEnNegro= new Font(FontFamily.TIMES_ROMAN, 5, Font.BOLD, BaseColor.BLACK);
        Font fontDatosEnRojo= new Font(FontFamily.TIMES_ROMAN, 5, Font.BOLD, BaseColor.RED);
        
        // creo el texto que indica localidad y provincia
        Paragraph locProv= new Paragraph();
        locProv.setFont(fontLocalidad);// seteo fuente al parrafo
        locProv.setTabSettings(new TabSettings(40));// seteo tabulacion
        locProv.setLeading(12);// seteo interlineado del parrafo
        locProv.add(new Chunk("\n\n"));
        locProv.add(Chunk.TABBING);// aplico la tabulacion
        locProv.add(new Chunk("SANTA FE - SANTA FE"));
        documento.add(locProv);// agrego el parrafo al documento
        
        //linea que muestra el numero de licencia
        Paragraph numLic= new Paragraph();
        numLic.setFont(fontCatDatos); // seteo la fuente al parrafo
        numLic.setTabSettings(new TabSettings(95));// seteo la tabulacion al parrafo
        numLic.add(Chunk.TABBING);// aplico la tabulacion
        numLic.add(new Chunk("LICENCIA Nº "));
        Chunk numLicText= new Chunk(dtoTitular.getNroDoc()); // creo un chunk aparte para poder ponerle propiedades distintas al parrafo
        numLicText.setFont(fontDatosEnRojo);// seteo la fuente al chunk que contiene el numero de doc
        numLic.add(numLicText);// agrego el chunk que tiene el numero de doc al parrafo
        documento.add(numLic);// agrego el parrafo al documento
        
        //linea que muestra el apellido del titular
        Paragraph apellido= new Paragraph();
        apellido.setFont(fontCatDatos);// seteo la fuente al parrafo
        apellido.setLeading(8);// seteo el interlineado al parrafo
        apellido.setTabSettings(new TabSettings(99));// seteo la tabulacion al parrafo
        apellido.add(Chunk.TABBING);// aplico la tabulacion
        apellido.add(new Chunk("APELLIDO "));
        Chunk apellidoTitular= new Chunk(dtoTitular.getApellido().toUpperCase());// creo un chunk que cotiene el apellido del titular
        apellidoTitular.setFont(fontDatosEnNegro);// seteo la fuente para el apellido del titular
        apellido.add(apellidoTitular);// agreog el apellido del titular al parrafo
        documento.add(apellido);// agrego el parrafo al documento
        
        //linea que muesta el nombre
        Paragraph nombre= new Paragraph();
        nombre.setFont(fontCatDatos);// seteo la fuente al parrafo
        nombre.setLeading(8);// seteo interlineado al parrafo
        nombre.setTabSettings(new TabSettings(102));// seteo tabulacion al parrafo
        nombre.add(Chunk.TABBING);// aplico tabulacion
        nombre.add(new Chunk("NOMBRE "));
        Chunk nombreTitular= new Chunk(dtoTitular.getNombre().toUpperCase()); // creo un chunk que contiene el nombre del titular
        nombreTitular.setFont(fontDatosEnNegro);// seteo la fuente al nombre
        nombre.add(nombreTitular);// agrego el nombre al parrafo
        documento.add(nombre);// agrego el parrafo al documento
        
        //linea que muestra la fecha de nacimiento
        Paragraph fechaNac= new Paragraph();
        fechaNac.setFont(fontCatDatos);// seteo la fuente al parrafo
        fechaNac.setLeading(8);// seteo el itnerlineado
        fechaNac.setTabSettings(new TabSettings(88));// seteo la tabulacion
        fechaNac.add(Chunk.TABBING);// aplico la tabulacion
        fechaNac.add(new Chunk("FECHA DE NAC "));
        Chunk fechaNacTitular= new Chunk(sdf.format(dtoTitular.getFechaNac()));// creo un chunk con la fecha de nacimiento del titular
        fechaNacTitular.setFont(fontDatosEnNegro);// seteo la fuente a la fecha de nacimiento
        fechaNac.add(fechaNacTitular);// agrego la fecha de nacimiento al parrafo
        documento.add(fechaNac);// agrego el parrafo al documento
       
        //linea que muestra la direccion del titular
        Paragraph domicilio= new Paragraph();
        domicilio.setFont(fontCatDatos);// seteo fuente al parrafo
        domicilio.setLeading(8);// seteo interlineado al parrafo
        domicilio.setTabSettings(new TabSettings(98));// seteo tabulacion al parrafo
        domicilio.add(Chunk.TABBING);// aplico tabulacion
        domicilio.add(new Chunk("DOMICILIO "));
        Chunk domicilioTitular= new Chunk(dtoTitular.getDireccion().toUpperCase());// creo un chunk que contiene la direccion del titular
        domicilioTitular.setFont(fontDatosEnNegro);// seteo la fuente a la direccion
        domicilio.add(domicilioTitular);// agrego la direccion al parrafo
        documento.add(domicilio);// agrego el parrafo al documento
        
        //linea que muestra la localidad del titular
        Paragraph localidad= new Paragraph();
        localidad.setFont(fontCatDatos); //seteo fuente al parrafo
        localidad.setLeading(8); //seteo interlineado al parrafo
        localidad.setTabSettings(new TabSettings(96)); //seteo tabulacion
        localidad.add(Chunk.TABBING); //aplico tabulacion
        localidad.add(new Chunk("LOCALIDAD "));
        Chunk localidadTitular= new Chunk("SANTA FE");
        localidadTitular.setFont(fontDatosEnNegro); // seteo fuente de la localicad
        localidad.add(localidadTitular); // agrego localidad al parrafo
        documento.add(localidad); // agrego parrafo al documento
        
        //linea que muestra la nacionalidad del titular
        Paragraph nacionalidad= new Paragraph();
        nacionalidad.setFont(fontCatDatos); //seteo fuente al parrafo
        nacionalidad.setLeading(8); //seteo el interlineado
        nacionalidad.setTabSettings(new TabSettings(86)); //seteo el tabulado
        nacionalidad.add(Chunk.TABBING); //aplico el tabulado
        nacionalidad.add(new Chunk("NACIONALIDAD "));
        Chunk nacionalidadTitular= new Chunk("ARGENTINA");
        nacionalidadTitular.setFont(fontDatosEnNegro); //seteo la fuente a la nacionalidad
        nacionalidad.add(nacionalidadTitular);// agrego la nacionalidad al parrafo
        documento.add(nacionalidad); // agrego la nacionalidad al parrafo
        
        Paragraph fechaOtorVenc= new Paragraph();
        fechaOtorVenc.setFont(fontCatDatos); //seteo la fuente al parrafo
        fechaOtorVenc.setLeading(8); //seteo el interlineado
        fechaOtorVenc.setTabSettings(new TabSettings(84)); // seteo el tabulaod
        fechaOtorVenc.add(Chunk.TABBING);// aplico el tabulado
        fechaOtorVenc.add(new Chunk("OTORGAMIENTO "));
        Chunk fechaOtor= new Chunk(sdf.format(dtoLicencia.getFechaOtor())); //creo un chunk con la fecha de otorgamiento
        fechaOtor.setFont(fontDatosEnNegro); //seteo la fuente a la fecha de otorgamiento
        fechaOtorVenc.add(fechaOtor); //agreog al parrafo la fecha de otorgamiento
        Chunk fechaVenc= new Chunk("    VTO "+sdf.format(dtoLicencia.getFechaVenc())); // creo un chunk con la fecha de vencimeinto
        fechaVenc.setFont(fontDatosEnRojo); //seteo la fuente a la fecha de vencimiento
        fechaOtorVenc.add(fechaVenc); //agrego la fecha de vencimeinto al parrafo
        documento.add(fechaOtorVenc); //agrego el parrafo al documento
        
        Paragraph clase= new Paragraph();
        clase.setFont(fontCatDatos); //seteo la fuente al parrafo
        clase.setLeading(8); //seteo el interlineado
        clase.setTabSettings(new TabSettings(108)); //seteo la tabulacion
        clase.add(Chunk.TABBING);// aplico la tabulacion
        clase.add(new Chunk("CLASE "));
        Chunk claseLic= new Chunk(dtoLicencia.getTipo().toUpperCase()); //creo un chunk con la clase de licencia
        claseLic.setFont(fontDatosEnRojo); //seteo fuente a la clase de licencia
        clase.add(claseLic); //agrego la clase de licencia al parrafo
        documento.add(clase); //agrego el parrafo al documento
        
        Image escudoProv= Image.getInstance("src\\main\\resources\\imagenes\\escudoProvSantaFe.png"); //obtengo la imagen
        escudoProv.scalePercent(6); //seteo el tamaño como una escala porcentual del tamaño original
        escudoProv.setAbsolutePosition(240, 520);// seteo la posicion en el documento
        documento.add(escudoProv);// agrego la imagen al documento
        
        Image avatar= Image.getInstance("src\\main\\resources\\imagenes\\avatar.png"); //obtengo la imagen
        avatar.scalePercent(4); //seteo el tamaño como escala porcentual del tamaño real
        avatar.setAbsolutePosition(70, 470); //seteo la posicion en el documento
        documento.add(avatar); //agrego la imagen al documento
        
        Rectangle rectAtras= new Rectangle(50,285,275,400); //creo el rectangulo para la parte de atras
        rectAtras.setBorder(Rectangle.BOX); //seteo los bordes
        rectAtras.setBorderWidth((float) 0.5);// setel el anchi de los bordes
        canvas.rectangle(rectAtras);// dibujo el rectangulo
        
        //fuente para el texto de la parte de atras de la licencia
        Font fontAtras= new Font(FontFamily.TIMES_ROMAN, 6, Font.NORMAL, BaseColor.BLACK);
        
        //seteo un conjunto de lineas en blanco para poder escribir dentro del rectangulo
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
        
        //linea que muestra el tipo de la licencia
        Paragraph claseDescript= new Paragraph();
        claseDescript.setFont(fontAtras); // seteo fuente al parrafo
        claseDescript.setLeading(10); //seteo el interlineado
        claseDescript.setTabSettings(new TabSettings(75)); //seteo tabulacion
        claseDescript.add(Chunk.TABBING); // aplico tabulacion
        
        /* HashMap que contiene la descripcion de cada clase
           Los espacios en las descripciones son para alinear el inicio del texto despues de un salto de linea*/
        HashMap<String,String> descriptClase= new HashMap<String, String>();
        descriptClase.put("A", "CICLOMOTORES, MOTOCICLETAS Y TRICICLOS MOTORIZAODS");
        descriptClase.put("B", "AUTOMOVILES Y CAMIONETAS");
        descriptClase.put("C", "CAMIONES SIN ACOPLADOS Y LOS COMPRENDIDOS \n                                       "
        		+ "           EN LA CLASE B");
        descriptClase.put("D", "SERVICIO DE TRANSPORTE DE PASAJEROS, \n                              "
        		+ "                    EMERGENCIAS, SEGURIDAD Y LOS DE CLASE C O B");
        descriptClase.put("E", "CAMIONES ARTICULADOS O CON ACOPLADO, \n       "
        		+ "                                          MAQUINARIAS ESPECIAL NO AGRICOLA Y LOS DE \n"
        		+ "                                                 CLASE B Y C");
        descriptClase.put("F", "AUTOMOTORES ADAPTADOS PARA DISCAPACITADOS");
        descriptClase.put("G", "TRACTORES AGRICOLAS Y MAQUINARIA ESPECIAL \n                                         "
        		+ "         AGRICOLA");
        
        claseDescript.add(new Chunk(dtoLicencia.getTipo().toUpperCase()+" - "+descriptClase.get(dtoLicencia.getTipo())));
        documento.add(claseDescript); //agrego el parrafo al documento
        
        Image codigoQR= Image.getInstance("src\\main\\resources\\imagenes\\codigo qr.png"); //obtengo la imagen
        codigoQR.scalePercent(15); //le seteo el tamaño de acuerdo a un porcentaje del tamaño real
        codigoQR.setAbsolutePosition(61, 357); //seteo la posicion en el documento
        documento.add(codigoQR); //añado la imagen al documento
        
        Paragraph observaciones= new Paragraph();
        observaciones.setFont(fontAtras); //seteo la fuente al parrafo
        observaciones.setLeading(40); //seteo el interlineado del parrafo
        observaciones.setTabSettings(new TabSettings(28)); //seteo el tabulado
        observaciones.add(Chunk.TABBING); //aplico el tabulado
        observaciones.add(new Chunk("OBSERVACIONES: "+dtoLicencia.getObservaciones().toUpperCase()));
        documento.add(observaciones); //añado el parrafo al documento
        
       //linea que dice si el titular es donante o no
        Paragraph donante= new Paragraph();
        donante.setFont(fontAtras); //seteo la fuente al parrafo
        donante.setLeading(15); // seteo el interlineado
        donante.setTabSettings(new TabSettings(28)); //seteo el tabulado
        donante.add(Chunk.TABBING); //aplico el tabulado
        
        if(dtoTitular.getDonador()) donante.add(new Chunk("DONANTE: SI"));
        else donante.add(new Chunk("DONANTE: SI"));
        
        documento.add(donante); //agrego el parrafo al documento
        
        //linea que muestra grupo y factor sanguineo del titular
        Paragraph grupoFactor= new Paragraph();
        grupoFactor.setFont(fontAtras); //seteo la fuente al parrafo
        grupoFactor.setLeading(15); //seteo el interlineado
        grupoFactor.setTabSettings(new TabSettings(28)); //seteo el tabulado
        grupoFactor.add(Chunk.TABBING); //aplico el tabulaod
        grupoFactor.add(new Chunk("GRUPO Y FACTOR: "+dtoTitular.getGrupoS().toUpperCase()+dtoTitular.getFactorS()));
        documento.add(grupoFactor); //agrego el parrafo al documento
        
        
        documento.close(); //cierro el documento
        
        String path= new File("licencia.pdf").getAbsolutePath(); //obtengo la ruta absoluta del pdf
        
        //abro el pdf
        Process process= Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+path);
	}

	public static void emitirLicencia(DTOLicencia DTOLicencia) throws EmitirLicenciaException{
		
		String tipo = DTOLicencia.getTipo();
		
		DTOTitular titularDTO = DTOLicencia.getTitular();
		
		long timeToday = Calendar.getInstance().getTime().getTime();
		long timeBorn = titularDTO.getFechaNac().getTime();
		
		if(tipo.equals("A") || tipo.equals("B") || tipo.equals("F") || tipo.equals("G")) {
			
			if( timeToday - timeBorn  < SECONDS_IN_YEAR * 17) {
				throw new EmitirLicenciaException("El titular debe tener 17 años o más para obtener una licencia de clase "+tipo);
			}
		}else if(tipo.equals("C") || tipo.equals("D") || tipo.equals("E") {
			if( timeToday - timeBorn  < SECONDS_IN_YEAR * 21 ) {
				throw new EmitirLicenciaException("El titular debe tener 21 años o más para obtener una licencia de clase "+tipo);
			}
			Predicate<DTOLicencia> licenciaBUnAnioAntesPredicate = lic -> lic.getTipo().equals("B") && timeToday - lic.getFechaOtor().getTime() >= SECONDS_IN_YEAR;
			Predicate<DTOLicencia> licenciaProfesionalPredicate = lic -> lic.getTipo().equals("C") || lic.getTipo().equals("D") || lic.getTipo().equals("E");
			
			boolean alMenosLicenciaBUnAnioAntes = titularDTO.getLicenciaList().stream().anyMatch(licenciaBUnAnioAntesPredicate);
			
			if(!alMenosLicenciaBUnAnioAntes) {
				throw new EmitirLicenciaException("El titular debe haber obtenido una licencia de clase B, como mínimo, un año antes");
			}
				
			if(timeToday - timeBorn >= 65*SECONDS_IN_YEAR) {
				boolean existeLicenciaProfesionalPrevia = titularDTO.getLicenciaList().stream().anyMatch(licenciaProfesionalPredicate);				
				if(!existeLicenciaProfesionalPrevia) {
					throw new EmitirLicenciaException("El titular debe ser menor a 65 años para obtener su primera licencia profesional");
				}
			}
		}
		Titular titular;
		try {
			titular = DAOTitularJPA.getInstance().obtenerTitular(DTOLicencia.getTitular().getNroDoc());
		}
		catch(Exception ex) {
			throw new EmitirLicenciaException("Error al recuperar el titular desde la base de datos");
		}
		Predicate<Licencia> licenciaDelMismoTipoPredicate = lic -> lic.getTipo().equals(DTOLicencia.getTipo());
		boolean tieneOTuvoLicenciaDelMismoTipo = titular.getLicenciaList().stream().anyMatch(licenciaDelMismoTipoPredicate);
		
		if(tieneOTuvoLicenciaDelMismoTipo) {
			throw new EmitirLicenciaException("El titular ya posee la licencia. Si expiró, debe renovarla");
		}
		

		Licencia nuevaLicencia = new Licencia();
		
		nuevaLicencia.setTipo(tipo);
		nuevaLicencia.setFechaOtor(Calendar.getInstance().getTime());
		
		nuevaLicencia.setObservaciones(DTOLicencia.getObservaciones());
		Tramite tramite = new Tramite();
		tramite.setFechaReali(Calendar.getInstance().getTime());
		Usuario usuario = GestorUsuario.obtenerUsuarioActual();
		tramite.setUsuario(usuario);
		nuevaLicencia.setTramite(tramite);
		
		

		
		
		try {
			nuevaLicencia = DAOLicenciaJPA.getInstance().darDeAltaLicencia(nuevaLicencia, DTOLicencia.getTitular().getNroDoc());
		}
		catch(Exception e){
			throw new EmitirLicenciaException("Error al guardar la licencia en la base de datos. Si el problema persiste, contacte al administrador del sistema");
		}
		
		
		
			

	}

}

	
