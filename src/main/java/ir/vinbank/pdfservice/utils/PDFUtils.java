package ir.vinbank.pdfservice.utils;

public class PDFUtils {

    public static String body = """
                        
            <body>
                        
                <style>
                        
            body {
                margin: 36px;
                padding: 0;
            }
                        
            body h1 {
                width: 100%;
                text-align: center;
            }
                        
            section .question-answers{
                display: flex;
                flex-wrap: wrap;
                flex-direction: row;
                align-content: center;
                justify-content: space-between;
                align-items: center;
            }
                        
            section .question-answers .question-answer {
                display: flex;
                flex-wrap: nowrap;
                flex-direction: row;
                align-content: center;
                justify-content: space-between;
                align-items: center;
            }
                        
            section .question-answers .question-answer .question{
                padding: 8px;
            }
            section .question-answers .question-answer .answer{
                padding: 8px;
            }
                        
                        
                </style>
                        
                <h1>%title</h1>
                
                %sections
                
                </body>
                        
                        
            """;

    public static String section = """
             <section>
                    <h2>%title</h2>
                    <div class="question-answers">
                       %questionAnswers
                    </div>
                </section>
            """;

    public static String questionAnswer = """
            <div class="question-answer">
                            <div class="question">%question</div>
                            <div class="answer">%answer</div>
                        </div>
            """;
}
