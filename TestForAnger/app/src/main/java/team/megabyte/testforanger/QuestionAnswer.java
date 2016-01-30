package team.megabyte.testforanger;

/**
 * Created by Asus1 on 31.01.2016.
 */
public class QuestionAnswer {
    private String question;
    private boolean answer;

    public QuestionAnswer(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
