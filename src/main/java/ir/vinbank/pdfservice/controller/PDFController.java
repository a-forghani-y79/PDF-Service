package ir.vinbank.pdfservice.controller;

import com.itextpdf.text.DocumentException;
import ir.vinbank.pdfservice.dto.PDFRequestDto;
import ir.vinbank.pdfservice.service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/pdf-generator")
@RequiredArgsConstructor
public class PDFController {

    private final PDFService pdfService;

    @PostMapping()
    public ResponseEntity<InputStreamResource> generatePDF(@RequestBody PDFRequestDto pdfRequestDto) throws DocumentException, IOException {
        byte[] bytes = pdfService.generatePDF(pdfRequestDto);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", String.format("attachment; filename=your_file_name"));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
