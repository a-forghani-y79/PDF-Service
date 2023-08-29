package ir.vinbank.pdfservice.dto;

import lombok.Data;

@Data
public class PDFRequestDto {

    private String title;
    private SectionDto[] sections;

}
