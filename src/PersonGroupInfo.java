import java.util.List;
import java.util.Objects;

public class PersonGroupInfo {
    private List<String> categories;
    private Human human;

    public PersonGroupInfo(List<String> categories, Human human) {
        this.categories = categories;
        this.human = human;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonGroupInfo that = (PersonGroupInfo) o;
        return Objects.equals(human, that.human);
    }


    @Override
    public String toString() {
        return "PersonGroupInfo{" +
                "categories=" + categories +
                ", human=" + human +
                '}';
    }
}
