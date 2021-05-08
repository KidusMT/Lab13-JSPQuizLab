package miu.kmt;

/**
 * @author KidusMT
 * Date: 5/7/2021
 */
public class Quiz {
    private int score = 0;

    private final String[] questions = {
            "3, 1, 4, 1, 5",   // pi
            "1, 1, 2, 3, 5",   // fibonacci
            "1, 4, 9, 16, 25", // squares
            "2, 3, 5, 7, 11",  // primes
            "1, 2, 4, 8, 16"   // powers of 2
    };
    private final int[] answers = {9, 8, 26, 13, 32};

    public boolean checkAnswer(int ans, int quesIndx) {
        if (ans == answers[quesIndx]) {
            score++;
            return true;
        }
        return false;
    }

    public void setScore(int newScore){
        score = newScore;
    }

    public int getScore() {
        return score;
    }

    public String[] getAllQuestions() {
        return questions;
    }

    public String getQuestion(int index) {
        return questions[index];
    }


}
