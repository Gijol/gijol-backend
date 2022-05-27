package com.gist.graduation.utils.filter;

public enum Duplication {
    GS01,
    GS02,
    UC9331;

    public static boolean canDuplicate(String code) {
        for (Duplication duplicateCode : values()) {
            if (code.contains(duplicateCode.name())) {
                return true;
            }
        }
        return false;
    }

}
