import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        DataBase<Human> dataBase = new DataBase<>();

        DrivingSchoolService<Human> drivingSchoolService = new DrivingSchoolService<>(dataBase);

         drivingSchoolService.showListOfStudents();

        Iterator<Human> iterator = drivingSchoolService.iterator();

        while (iterator.hasNext()) {
            Human next = iterator.next();
            if (next != null) {
                System.out.println(next);
            }
        }

        drivingSchoolService.showtimeTableForGroups();
    }

}
