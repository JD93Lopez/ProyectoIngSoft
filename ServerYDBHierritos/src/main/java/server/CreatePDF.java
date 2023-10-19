package server;

import clases.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

public class CreatePDF implements Serializable {
    String numFactura, fechaFactura, totalFactura, formaPago, consecutivoDIAN;
    Cliente cliente;
    Vendedor vendedor;
    Ferreteria ferreteria;
    List<Producto> listaProductos;
    FacturaVenta facturaVenta;

    public CreatePDF(FacturaVenta facturaVenta) {
        this.facturaVenta = facturaVenta;
    }

    public void getPDF() throws FileNotFoundException, MalformedURLException {

        String path = "src\\pdfs\\" + facturaVenta.getIdFacturaVenta() + ".pdf";
        String paraText = facturaVenta.getFerreteria().getNombre();
        String imString = "src\\images\\icono.png";
        ImageData data = ImageDataFactory.create(imString);
        Image icono = new Image(data);
        Paragraph paragraph1 = new Paragraph(paraText).setFontColor(Color.DARK_GRAY).setBold().setFontSize(20);
        paragraph1.setTextAlignment(TextAlignment.CENTER);
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        float[] pointColumnWidths = { 150F, 150F, 150F, 150F, 150F };
        float[] tamañoTablaDatos = { 200F, 200F, 200F, 200F };
        Table encabezado = new Table(tamañoTablaDatos);
        System.out.println(facturaVenta.getConsecutivoDian());
        Cell datosFactura = new Cell();
        if (facturaVenta.getConsecutivoDian() == 0) {
            datosFactura.add("N° Cotización" );
        }else{
            datosFactura.add("N° Factura");
            datosFactura.add("N° DIAN");
        }
        datosFactura.add("Fecha");
        datosFactura.setBorder(Border.NO_BORDER);
        encabezado.addCell(datosFactura);

        Cell datosFacturCell = new Cell();
        if (facturaVenta.getConsecutivoDian() == 0) {
            datosFacturCell.add(facturaVenta.getIdFacturaVenta() + "");
        } else {
          datosFacturCell.add(facturaVenta.getIdFacturaVenta() + "");  
        }
        

        if (facturaVenta.getConsecutivoDian() == 0) {
            datosFacturCell.add("");
        } else {
          datosFacturCell.add(facturaVenta.getConsecutivoDian() + "");  
        }
        datosFacturCell.add(facturaVenta.getFechaYHora());
        datosFacturCell.setBorder(Border.NO_BORDER);
        datosFacturCell.setTextAlignment(TextAlignment.CENTER);
        encabezado.addCell(datosFacturCell);
        Cell cellEspacio = new Cell().setHeight(20).setBackgroundColor(DeviceRgb.WHITE).setBorder(Border.NO_BORDER);

        Cell informacionFerreteria = new Cell();
        informacionFerreteria.add(paragraph1);
        informacionFerreteria.add(facturaVenta.getFerreteria().getDireccion());
        informacionFerreteria.add("Cel. " + facturaVenta.getFerreteria().getTelefono());
        informacionFerreteria.add("NIT: " + facturaVenta.getFerreteria().getNit());
        informacionFerreteria.add(facturaVenta.getFerreteria().getCorreo());
        informacionFerreteria.setBorder(Border.NO_BORDER);
        encabezado.addCell(informacionFerreteria);
        encabezado.setBorder(Border.NO_BORDER);
        encabezado.setTextAlignment(TextAlignment.RIGHT);

        Cell iconoCell = new Cell();
        iconoCell.add(icono);
        iconoCell.setBorder(Border.NO_BORDER);
        encabezado.addCell(iconoCell);

        encabezado.addCell(cellEspacio);
        document.add(encabezado);

        // Segunda parte del PDF
        Table tableDatos = new Table(tamañoTablaDatos);
        Cell datosCliente = new Cell();
        datosCliente.add("Datos Cliente").setBold();
        datosCliente.setBorder(Border.NO_BORDER);
        tableDatos.addCell(datosCliente);
        Cell casillasVacias01 = new Cell();
        casillasVacias01.add("");
        casillasVacias01.setBorder(Border.NO_BORDER);
        tableDatos.addCell(casillasVacias01);

        Cell casillasvacias02 = new Cell();
        casillasvacias02.add("");
        casillasvacias02.setBorder(Border.NO_BORDER);
        tableDatos.addCell(casillasvacias02);

        Cell vendedorCell = new Cell();
        vendedorCell.add("Datos del vendedor").setBold();
        vendedorCell.setBorder(Border.NO_BORDER);
        tableDatos.addCell(vendedorCell);

        Cell listaDatosCliente = new Cell();
        listaDatosCliente.add(facturaVenta.getCliente().getNombres());
        listaDatosCliente.add(facturaVenta.getCliente().getTelefono());
        listaDatosCliente.add(facturaVenta.getCliente().getTipoDocumento() + "");
        listaDatosCliente.add(facturaVenta.getCliente().getNumDocumento());
        listaDatosCliente.add(facturaVenta.getCliente().getDireccion());
        listaDatosCliente.add(facturaVenta.getCliente().getCorreo());
        listaDatosCliente.setBorder(Border.NO_BORDER);
        tableDatos.addCell(listaDatosCliente);

        Cell listaTipoPersona = new Cell();
        listaTipoPersona.add(facturaVenta.getCliente().getTipoPersona() + "");
        if (facturaVenta.getCliente().getResponsableDeIva() == true) {
            listaTipoPersona.add("Responsable de IVA");
        } else {
            listaTipoPersona.add("IVA = No");
        }
        listaTipoPersona.setBorder(Border.NO_BORDER);
        tableDatos.addCell(listaTipoPersona);

        Cell c2 = new Cell();
        c2.add("");
        c2.setBorder(Border.NO_BORDER);
        tableDatos.addCell(c2);

        Cell datosVendedorCell = new Cell();
        datosVendedorCell.add(facturaVenta.getVendedor().getNombres());
        datosVendedorCell.add(facturaVenta.getVendedor().getId());
        //datosVendedorCell.add(facturaVenta.getFechaYHora());
        datosVendedorCell.setBorder(Border.NO_BORDER);
        datosVendedorCell.setTextAlignment(TextAlignment.LEFT);
        tableDatos.addCell(datosVendedorCell);
        tableDatos.addCell(cellEspacio);

        Table table1 = new Table(pointColumnWidths);

        Cell descripcionCell = new Cell();
        descripcionCell.add("DESCRIPCIÓN");
        descripcionCell.setBackgroundColor(Color.GRAY);
        descripcionCell.setBorder(Border.NO_BORDER);
        descripcionCell.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(descripcionCell);

        Cell cantidadCell = new Cell();
        cantidadCell.add("CANTIDAD");
        cantidadCell.setBackgroundColor(Color.GRAY);
        cantidadCell.setBorder(Border.NO_BORDER);
        cantidadCell.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(cantidadCell);

        Cell ivaCell = new Cell();
        ivaCell.add("IVA");
        ivaCell.setBackgroundColor(Color.GRAY);
        ivaCell.setBorder(Border.NO_BORDER);
        ivaCell.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(ivaCell);

        Cell precioUnitarioCell = new Cell();
        precioUnitarioCell.add("PRECIO UNITARIO ");
        precioUnitarioCell.setBackgroundColor(Color.GRAY);
        precioUnitarioCell.setBorder(Border.NO_BORDER);
        precioUnitarioCell.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(precioUnitarioCell);

        Cell precioCell = new Cell();
        precioCell.add("PRECIO TOTAL");
        precioCell.setBackgroundColor(Color.GRAY);
        precioCell.setBorder(Border.NO_BORDER);
        precioCell.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(precioCell);

        Cell descripcionProductos = new Cell();
        for (Producto producto : facturaVenta.getProductos()) {
            descripcionProductos.add(producto.getNombre());
        }
        descripcionProductos.add("TOTAL: ");
        descripcionProductos.setBorder(Border.NO_BORDER);
        descripcionProductos.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(descripcionProductos);

        Cell cantidadProductos = new Cell();
        for (Producto producto : facturaVenta.getProductos()) {
            cantidadProductos.add(producto.getExistencias() + "");
        }
        cantidadProductos.setBorder(Border.NO_BORDER);
        cantidadProductos.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(cantidadProductos);

        Cell ivaProducto = new Cell();
        for (Producto producto : facturaVenta.getProductos()) {
            double doubleTemporal = producto.getpIva();
            String stringTemporal = Double.toString(doubleTemporal);
            ivaProducto.add(stringTemporal);
        }
        ivaProducto.setBorder(Border.NO_BORDER);
        ivaProducto.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(ivaProducto);

        Cell precioUnitarioProducto = new Cell();
        for (Producto producto : facturaVenta.getProductos()) {
            double doubleTemporal = producto.getPrecioVenta();
            String stringTemporal = Double.toString(doubleTemporal);
            precioUnitarioProducto.add(stringTemporal);
        }
        precioUnitarioProducto.setBorder(Border.NO_BORDER);
        precioUnitarioProducto.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(precioUnitarioProducto);

        Cell precioTotalProducto = new Cell();
        for (Producto producto : facturaVenta.getProductos()) {
            double precioTemporal = producto.getPrecioTotal();
            String stringTemporal = Double.toString(precioTemporal);
            precioTotalProducto.add(stringTemporal);
        }
        precioTotalProducto.add(facturaVenta.getTotal() + "");
        precioTotalProducto.setBorder(Border.NO_BORDER);
        precioTotalProducto.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(precioTotalProducto);
        table1.addCell(cellEspacio);
        Paragraph formaDePago = new Paragraph("Forma de pago: " + facturaVenta.getFormaDePago()).setTextAlignment(TextAlignment.LEFT);

        document.add(tableDatos);
        document.add(table1);
        document.add(formaDePago);
        document.close();
        System.out.println("PDF CREATED");
    }

/*    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        Producto productoPrueba01 = new Producto("codigo", "nombre", "descripcion", 3000, 3, 10, 19, 9000);
        productoPrueba01.setPrecioTotal(3000);
        Producto productoPrueba02 = new Producto("codigo", "nombre", "descripcion02", 2000, 6, 10, 19, 15000);
        productoPrueba02.setPrecioTotal(200000);
        Producto productoPrueba03 = new Producto("codigo", "nombre", "descripcion03", 4000, 3, 10, 19, 90000);
        productoPrueba03.setPrecioTotal(50000);
        LinkedList<Producto> listaProductos = new LinkedList<Producto>();
        listaProductos.add(productoPrueba01);
        listaProductos.add(productoPrueba02);
        listaProductos.add(productoPrueba03);
        Cliente clientePrueba = new Cliente(Cliente.TipoPersona.NATURAL, false, "Maria Jose Romero", "3167836748",
                Persona.TipoDocumento.CEDULA_CIUDADANIA, "1100950146",
                "Calle 1N #19-24", "mariajoseromero2018@gmail.com");
        Vendedor vendedorPrubea = new Vendedor(Usuario.TipoUsuario.VENDEDOR, "Vendedor 1", "1234",
                "Nombres del vendedor", Persona.TipoDocumento.CEDULA_CIUDADANIA,
                "Documento vendedor");
        vendedorPrubea.setId("1234");
        Ferreteria ferreteriaPrueba = new Ferreteria("Ferreteria Hierritos", "3002156030", "8-8383828",
                "Calle 23 # 32-13", "estudiantes.2023@upb.edu.co");
        FacturaVenta facturaVenta = new FacturaVenta(ferreteriaPrueba, "18/10/2023", vendedorPrubea, 0, clientePrueba, EmpresaProveedora.FormaDePago.EFECTIVO, listaProductos, 40000, 000002);
        CreatePDF createPDF = new CreatePDF(facturaVenta);
        createPDF.getPDF();
    }*/
}
