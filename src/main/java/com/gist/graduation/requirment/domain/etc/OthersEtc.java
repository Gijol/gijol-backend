package com.gist.graduation.requirment.domain.etc;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

/*
 * this includes GIST 콜로퀴움, 신입생 세미나 and GIST 전공탐색
 * */
public class OthersEtc {

    private static final TakenCourse COLLOQUIUM = new TakenCourse("GIST대학 콜로퀴움", "UC9331", "0");
    public static final List<TakenCourse> FRESHMAN = List.of(new TakenCourse("GIST 새내기", "GS1901", "1"), new TakenCourse("신입생 세미나", "GS9301", "1"));
    public static final TakenCourse FIND_MAJOR = new TakenCourse("GIST 전공탐색", "UC0902", "1");

    public static void checkOtherETC(EtcMandatory etcMandatory, int studentId, UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> inputUserTakenCoursesListTakenCourses = inputUserTakenCoursesList.getTakenCourses();

        List<TakenCourse> colloquiumList = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> s.getCourseCode().equals(COLLOQUIUM.getCourseCode()))
                .collect(Collectors.toList());
        checkCollquium(etcMandatory, colloquiumList);


        List<TakenCourse> freshManCourses = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> FRESHMAN.stream().anyMatch(t -> t.getCourseCode().equals(s.getCourseCode())))
                .collect(Collectors.toList());
        checkFreshMan(etcMandatory, freshManCourses);

        etcMandatory.getUserTakenCoursesList().addAll(colloquiumList);
        etcMandatory.getUserTakenCoursesList().addAll(freshManCourses);

        if (studentId >= 21) {
            List<TakenCourse> findMajorList = inputUserTakenCoursesListTakenCourses.stream()
                    .filter(s -> s.getCourseCode().equals(FIND_MAJOR.getCourseCode()))
                    .collect(Collectors.toList());
            checkFindMajor(etcMandatory, findMajorList);
            inputUserTakenCoursesListTakenCourses.addAll(findMajorList);
        }
    }

    private static void checkCollquium(EtcMandatory etcMandatory, List<TakenCourse> colloquiumList) {
        int colloquiumCondition = 2;
        if (colloquiumList.size() < colloquiumCondition) {
            etcMandatory.addMessage(String.format("%s과목를 %d회 수강해야 합니다.", COLLOQUIUM.getCourseName(), colloquiumCondition - colloquiumList.size()));
        }
    }

    private static void checkFreshMan(EtcMandatory etcMandatory, List<TakenCourse> freshManCourses) {
        if (freshManCourses.isEmpty()) {
            etcMandatory.addMessage(String.format("%s혹은 %s 과목을 수강해야 합니다.", FRESHMAN.get(0).getCourseName(), FRESHMAN.get(1).getCourseName()));
        }
    }

    private static void checkFindMajor(EtcMandatory etcMandatory, List<TakenCourse> findMajorList) {
        if (findMajorList.isEmpty()) {
            etcMandatory.addMessage(String.format("%s 과목을 수강해야 합니다.", FIND_MAJOR.getCourseName()));
        }
    }
}
