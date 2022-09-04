import java.util.Iterator;

public class DrivingSchoolService<T> implements Iterable {
    private DataBase<T> dataBase;

    public DrivingSchoolService(DataBase<T> dataBase) {
        this.dataBase = dataBase;
    }

    public void showListOfStudents() {
        System.out.println(dataBase.toString());
    }

    public void showtimeTableForGroups() {
        int date = 1;
        while (date <= 31) {
            if (date > 31) {
                break;
            }
            System.out.println("Group A date: " + date);
            date = date + 4;
            if (date > 31) {
                break;
            }
            System.out.println("Group B date: " + date);
            date = date + 4;
            if (date > 31) {
                break;
            }
            System.out.println("Group C date: " + date);
            date = date + 4;
        }
    }


    @Override
    public Iterator iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            int counter = 0;
            int num = 0;

            @Override
            public boolean hasNext() {
                if (counter < dataBase.getGroupABC().size()) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                num = 0;
                Human human = (Human) dataBase.getGroupABC().get(counter);
                if (human.isA()) {
                    num++;
                }
                if (human.isB()) {
                    num++;
                }
                if (human.isC()) {
                    num++;
                }
                counter++;
                return num > 1 ? (T) human : null;
            }
        };
        return iterator;
    }
}
