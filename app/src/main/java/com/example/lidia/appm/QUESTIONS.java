package com.example.lidia.appm;

class QUESTIONS {
    private String question;
    private String answer1;
    private String mistake1;
    private String mistake2;
    private String solution;
    private String wrong_solution;
    private String solution1;



    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }


    public void setMistake1(String mistake1) {
        this.mistake1 = mistake1;
    }

    public void setMistake2(String mistake2) {
        this.mistake2 = mistake2;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setWrong_Solution(String wrong_solution) {
        this.wrong_solution = wrong_solution;
    }

    public void setSolution1(String solution1) {
        this.solution1 = solution1;
    }


    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getMistake1() {
        return mistake1;
    }

    public String getMistake2() {
        return mistake2;
    }

    public String getSolution() {
        return solution;
    }

    public String getWrong_Solution() {
        return wrong_solution;
    }

    public String getSolution1() {
        return solution1;
    }


    QUESTIONS(){}
    public QUESTIONS(String question, String answer1, String answer2, String mistake1, String mistake2, String mistake3, String mistake4, String solution, String wrong_solution, String solution1){
        this.question=question;
        this.answer1=answer1;
        this.mistake1=mistake1;
        this.mistake2=mistake2;
        this.solution=solution;
        this.wrong_solution=wrong_solution;
        this.solution1=solution1;
    }

    @Override
    public String toString() {
        return question;
    }
}
