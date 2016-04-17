package team.megabyte.testforanger;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Asus1 on 31.01.2016.
 */
public class QuestionAnswer implements Parcelable{
    private String question;
    private boolean answer;

    public QuestionAnswer(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    protected QuestionAnswer(Parcel in) {
        question = in.readString();
        answer = in.readByte() != 0;
    }

    public static final Creator<QuestionAnswer> CREATOR = new Creator<QuestionAnswer>() {
        @Override
        public QuestionAnswer createFromParcel(Parcel in) {
            return new QuestionAnswer(in);
        }

        @Override
        public QuestionAnswer[] newArray(int size) {
            return new QuestionAnswer[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeByte((byte) (answer ? 1 : 0));
    }
}
