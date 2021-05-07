package miu.kmt;

/**
 * @author KidusMT
 * Date: 5/7/2021
 */
public class Quiz {
    private static int score = 0;

    private static final String[] questions = {
            "3, 1, 4, 1, 5",   // pi
            "1, 1, 2, 3, 5",   // fibonacci
            "1, 4, 9, 16, 25", // squares
            "2, 3, 5, 7, 11",  // primes
            "1, 2, 4, 8, 16"   // powers of 2
    };
    private static final int[] answers = {9, 8, 26, 13, 32};

    public static boolean checkAnswer(int ans, int quesIndx) {
        if (ans == answers[quesIndx]) {
            if (score >= questions.length) score = 1;
            else score++;
            return true;
        }
        return false;
    }

    public static void setScore(int newScore){
        score = newScore;
    }

    public static int getScore() {
        return score;
    }

    public static String[] getAllQuestions() {
        return questions;
    }

    public static String getQuestion(int index) {
        return questions[index];
    }


}
