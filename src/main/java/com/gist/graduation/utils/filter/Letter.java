package com.gist.graduation.utils.filter;

public enum Letter {
    A,
    B,
    C,
    D;

    public static boolean isLetter(String grade) {

        if(grade.isBlank()){
            return true;
        }

        for (Letter letter : Letter.values()) {
            if (grade.contains(letter.name())) {
                return true;
            }
        }
        return false;
    }
}
