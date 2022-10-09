import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        DrivingSchoolService drivingSchoolService = new DrivingSchoolService();
        drivingSchoolService.generateGroups();
        System.out.println(drivingSchoolService.getSchool());
        System.out.println(drivingSchoolService.getMultiGroupStudents());
        System.out.println("===SCHEDULE===");
        System.out.println(drivingSchoolService.getTimetable());
        System.out.println();


    }
}
