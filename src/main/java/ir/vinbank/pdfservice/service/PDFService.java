package ir.vinbank.pdfservice.service;

import com.itextpdf.text.DocumentException;
import ir.vinbank.pdfservice.dto.PDFRequestDto;

import java.io.IOException;

public interface PDFService {

    byte[] generatePDF(PDFRequestDto pdfRequestDto) throws DocumentException, IOException;
}
