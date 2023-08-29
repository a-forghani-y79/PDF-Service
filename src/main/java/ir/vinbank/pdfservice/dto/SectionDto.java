package ir.vinbank.pdfservice.dto;

import lombok.Data;

@Data
public class SectionDto {

    private String title;
    private AnswerQuestionDto[] answerQuestions;
}
