public class School {
   private final Group groupA;
   private final Group groupB;
   private final Group groupC;

    public School(Group groupA, Group groupB, Group groupC) {
        this.groupA = groupA;
        this.groupB = groupB;
        this.groupC = groupC;
    }

    public Group getGroupA() {
        return groupA;
    }

    public Group getGroupB() {
        return groupB;
    }

    public Group getGroupC() {
        return groupC;
    }

    @Override
    public String toString() {
        return "School{" +
                "groupA=" + groupA +
                "\n, groupB=" + groupB +
                "\n, groupC=" + groupC +
                '}'+"\n";
    }
}
