import java.util.ArrayList;
import java.util.List;

public class TimeTableOfDay {
    private final String day;
    private final String category;
    private List<Human> multiStudent=new ArrayList<>();

    public TimeTableOfDay(String day, String category) {
        this.day = day;
        this.category = category;
    }

    public TimeTableOfDay(String day, String category, List<Human> multiStudent) {
        this.day = day;
        this.category = category;
        this.multiStudent = multiStudent;
    }


    public String getDay() {
        return day;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return multiStudent.size() == 0 ? "TimeTableOfDay{" + "day='" + day + '\'' + " category='" + category + '\'' + '}' + "\n" : "TimeTableOfDay{" + "day='" + day + '\'' + ", category='" + category + '\'' + "\n multiStudent="+ multiStudent + '}' + "\n";
    }
}
