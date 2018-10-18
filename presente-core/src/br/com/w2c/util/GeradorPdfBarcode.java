package br.com.w2c.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeCodabar;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorPdfBarcode {

	private Logger log = LogManager.getLogger();
	
    public static final String LOCAL = "barcodes.pdf";

    /**
     * Cria as células padronizadas
     * @return
     */
    private PdfPCell createCell() {
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setPaddingTop(9);
		cell.setPaddingBottom(10);
		cell.setFixedHeight(72.345514545f);
		return cell;
	}

	/**
     * Passar os códigos para geração.
     * @param local Local onde será criado o pdf
     * @param codigos Quantos códigos forem necessários. Exemplo: "A123456789A"
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdfCodebar(String local, String... codigos) {
    	try {
    		local = local == null || local.equals("") ? LOCAL : local;
    		
			Document document = new Document(PageSize.A4, 10, 10, 30, 0);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(local));
			document.open();
			PdfContentByte cb = writer.getDirectContent();

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			
			for (String codigo : codigos) {
				BarcodeCodabar codabar = new BarcodeCodabar();
				codabar.setCode(codigo);
				codabar.setStartStopText(false);
				Image image = codabar.createImageWithBarcode(cb, null, null);
				image.setWidthPercentage(75);
				image.setAlignment(Element.ALIGN_CENTER);
				PdfPCell cell = createCell();
				cell.addElement(image);
				table.addCell(cell);
			}
			int numTemp = 0;
			int numCell = 33;
			if (codigos.length < numCell) {
				numTemp = numCell - codigos.length;
				for (int i = 0; i < numTemp; i++) {
					table.addCell(createCell());
				}
			} else {
				numTemp = codigos.length;
				do {
					numTemp -= numCell;
				} while (numTemp > numCell);
				for (int i = 0; i < numTemp; i++) {
					table.addCell(createCell());
				}
			}
			document.add(table);
			document.close();
			System.out.println(local+" criado com sucesso.");
		} catch (Exception e) {
			log.error("Erro ao criar "+local, e);
		}
    }

	/**
     * Passar os códigos para geração.
     * @param codigos Quantos códigos forem necessários. Exemplo: "A123456789A"
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdfCodebar(String... codigos) throws IOException, DocumentException {
    	createPdfCodebar(null, codigos);
    }

	/**
	 * Código de exemplo
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws IOException, DocumentException {
		String[] s = new String[33];
		for (int i = 0; i < s.length; i++) {
			s[i] = "A123456789A";
		}
	    new GeradorPdfBarcode().createPdfCodebar(s);
	}
    
}
