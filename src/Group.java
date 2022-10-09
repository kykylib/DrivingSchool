import java.util.List;

public class Group {
   private final String category;
   private final List<Human> students;

    public Group(String category, List<Human> students) {
        this.category = category;
        this.students = students;
    }

    public String getCategory() {
        return category;
    }

    public List<Human> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "category='" + category + '\'' +
                ", students= \n" + students +
                '}';
    }
}
