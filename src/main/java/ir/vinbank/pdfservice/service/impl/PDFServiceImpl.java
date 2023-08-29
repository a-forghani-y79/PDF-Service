package ir.vinbank.pdfservice.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import ir.vinbank.pdfservice.dto.AnswerQuestionDto;
import ir.vinbank.pdfservice.dto.PDFRequestDto;
import ir.vinbank.pdfservice.dto.SectionDto;
import ir.vinbank.pdfservice.service.PDFService;
import ir.vinbank.pdfservice.utils.PDFUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class PDFServiceImpl implements PDFService {
    @Override
    public byte[] generatePDF(PDFRequestDto pdfRequestDto) throws DocumentException, IOException {


        String html = PDFUtils.body;
        html = html.replace("%title", pdfRequestDto.getTitle());
        StringBuilder tmpQA;
        String tmpSection = "";
        StringBuilder tmpSections = new StringBuilder();
        for (SectionDto section : pdfRequestDto.getSections()) {
            tmpSection = PDFUtils.section.replace("%title", section.getTitle());
            tmpQA = new StringBuilder();
            for (AnswerQuestionDto answerQuestion : section.getAnswerQuestions()) {
                tmpQA = tmpQA.append(PDFUtils.questionAnswer.replace("%question", answerQuestion.getQuestion())
                        .replace("%answer", answerQuestion.getAnswer()));
            }
            tmpSection = tmpSection.replace("%questionAnswers", tmpQA.toString());
            tmpSections = tmpSections.append(tmpSection);
        }
        html = html.replace("%sections", tmpSections.toString());


        return convertHtmlToPdfBytes(html);
    }


    private byte[] convertHtmlToPdfBytes( String htmlString ) throws IOException, DocumentException {
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();

        InputStream in = IOUtils.toInputStream(htmlString);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, in);
        document.close();
        return out.toByteArray();
    }
}
