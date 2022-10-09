import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrivingSchoolService {

    private School school;
    private SortService sortService = new SortService();

    public School getSchool() {
        return school;
    }

    public void generateGroups() {
        List<String> names = List.of("Nikita", "Bogdan", "Vlad", "Nika");
        List<String> lastNames = List.of("Liberman", "Nazarenko", "Rozhenko", "Olshansky");
        List<Human> studentsA = new ArrayList<>();
        List<Human> studentsB = new ArrayList<>();
        List<Human> studentsC = new ArrayList<>();
        Random random = new Random();
        int abDublicate = random.nextInt(0, 4);
        int bcDublicate = random.nextInt(0, 4);
        int acDublicate = random.nextInt(0, 4);
        for (int i = 0; i < 5; i++) {
            int name = random.nextInt(0, 4);
            int lastName = random.nextInt(0, 4);
            Human human = new Human(names.get(name), lastNames.get(lastName));
            addAvoidDuplicate(studentsA, human);
            if (abDublicate == i) {
                addAvoidDuplicate(studentsB, human);
            }
            if (acDublicate == i) {
                addAvoidDuplicate(studentsC, human);
            }
        }
        for (int i = 0; i < 5; i++) {
            int name = random.nextInt(0, 4);
            int lastName = random.nextInt(0, 4);
            Human human = new Human(names.get(name), lastNames.get(lastName));
            addAvoidDuplicate(studentsB, human);
            if (abDublicate == i) {
                addAvoidDuplicate(studentsA, human);
            }
            if (bcDublicate == i) {
                addAvoidDuplicate(studentsC, human);
            }
        }
        for (int i = 0; i < 5; i++) {
            int name = random.nextInt(0, 4);
            int lastName = random.nextInt(0, 4);
            Human human = new Human(names.get(name), lastNames.get(lastName));
            addAvoidDuplicate(studentsC, human);
            if (bcDublicate == i) {
                addAvoidDuplicate(studentsB, human);
            }
            if (acDublicate == i) {
                addAvoidDuplicate(studentsA, human);
            }
        }
        Group groupA = new Group("A", studentsA);
        Group groupB = new Group("B", studentsB);
        Group groupC = new Group("C", studentsC);
        school = new School(groupA, groupB, groupC);
    }

    private void addAvoidDuplicate(List<Human> list, Human human) {
        if (!list.contains(human)) {
            list.add(human);
        }
    }

    public List<PersonGroupInfo> getMultiGroupStudents() {
        List<PersonGroupInfo> list = new ArrayList<>();

        Group groupA = school.getGroupA();
        Group groupB = school.getGroupB();
        Group groupC = school.getGroupC();

        for (int i = 0; i < groupA.getStudents().size(); i++) {
            List<String> listCategories = new ArrayList<>();
            Human humanA = groupA.getStudents().get(i);
            listCategories.add(groupA.getCategory());
            if (groupB.getStudents().contains(humanA)) {
                listCategories.add(groupB.getCategory());
            }
            if (groupC.getStudents().contains(humanA)) {
                listCategories.add(groupC.getCategory());
            }
            if (listCategories.size() > 1) {
                list.add(new PersonGroupInfo(listCategories, humanA));
            }
        }

        for (int i = 0; i < groupB.getStudents().size(); i++) {
            List<String> listCategories = new ArrayList<>();
            Human humanB = groupB.getStudents().get(i);
            listCategories.add(groupB.getCategory());
            if (groupA.getStudents().contains(humanB)) {
                listCategories.add(groupA.getCategory());
            }
            if (groupC.getStudents().contains(humanB)) {
                listCategories.add(groupC.getCategory());
            }
            PersonGroupInfo personGroupInfo = new PersonGroupInfo(listCategories, humanB);
            if (listCategories.size() > 1) {
                if (list.contains(personGroupInfo)) {
                    continue;
                }
                list.add(new PersonGroupInfo(listCategories, humanB));
            }
        }

        for (int i = 0; i < groupC.getStudents().size(); i++) {
            List<String> listCategories = new ArrayList<>();
            Human humanC = groupC.getStudents().get(i);
            listCategories.add(groupC.getCategory());
            if (groupA.getStudents().contains(humanC)) {
                listCategories.add(groupA.getCategory());
            }
            if (groupB.getStudents().contains(humanC)) {
                listCategories.add(groupB.getCategory());
            }
            PersonGroupInfo personGroupInfo = new PersonGroupInfo(listCategories, humanC);
            if (listCategories.size() > 1) {
                if (list.contains(personGroupInfo)) {
                    continue;
                }
                list.add(new PersonGroupInfo(listCategories, humanC));
            }
        }
        return list;
    }

    public List<TimeTableOfDay> getTimetable() {

        List<Group> a = getSplitGroup(school.getGroupA().getStudents(), "A");
        List<Group> b = getSplitGroup(school.getGroupB().getStudents(), "B");
        List<Group> c = getSplitGroup(school.getGroupC().getStudents(), "C");

        List<Group> allGroups = new ArrayList<>();
        allGroups.addAll(a);
        allGroups.addAll(b);
        allGroups.addAll(c);
        System.out.println("===SUBGROUP===");
        System.out.println(allGroups);


        List<TimeTableOfDay> schedule = new ArrayList<>();
        Group largestGroup = null;
        for (Group group : allGroups) {
            if (largestGroup != null) {
                largestGroup = group.getStudents().size() > largestGroup.getStudents().size() ? group : largestGroup;
                continue;
            }
            largestGroup = group;
        }
        allGroups.remove(largestGroup);
        allGroups.add(0, largestGroup);
        List<Group> getSortedGroups = getSortedGroups(allGroups);

        if(getSortedGroups.size()==7){
            List<Human> students = getSortedGroups.get(6).getStudents();
            if(getSortedGroups.get(6).getCategory().equals("2")){
                schedule.add(new TimeTableOfDay(DayOfWeek.MONDAY.name(), getSortedGroups.get(0).getCategory()));
                schedule.add(new TimeTableOfDay(DayOfWeek.TUESDAY.name(), getSortedGroups.get(1).getCategory(),students));
                schedule.add(new TimeTableOfDay(DayOfWeek.WEDNESDAY.name(), getSortedGroups.get(2).getCategory(),students));
                schedule.add(new TimeTableOfDay(DayOfWeek.THURSDAY.name(), getSortedGroups.get(3).getCategory(),students));
                schedule.add(new TimeTableOfDay(DayOfWeek.FRIDAY.name(), getSortedGroups.get(4).getCategory(),students));
                schedule.add(new TimeTableOfDay(DayOfWeek.SATURDAY.name(), getSortedGroups.get(5).getCategory(),students));
                return schedule;
            }
            if(getSortedGroups.get(6).getCategory().equals("4")){
                schedule.add(new TimeTableOfDay(DayOfWeek.MONDAY.name(), getSortedGroups.get(0).getCategory()));
                schedule.add(new TimeTableOfDay(DayOfWeek.TUESDAY.name(), getSortedGroups.get(1).getCategory()));
                schedule.add(new TimeTableOfDay(DayOfWeek.WEDNESDAY.name(), getSortedGroups.get(2).getCategory()));
                schedule.add(new TimeTableOfDay(DayOfWeek.THURSDAY.name(), getSortedGroups.get(3).getCategory(),students));
                schedule.add(new TimeTableOfDay(DayOfWeek.FRIDAY.name(), getSortedGroups.get(4).getCategory(),students));
                schedule.add(new TimeTableOfDay(DayOfWeek.SATURDAY.name(), getSortedGroups.get(5).getCategory(),students));
                return schedule;
            }
        }

        schedule.add(new TimeTableOfDay(DayOfWeek.MONDAY.name(), getSortedGroups.get(0).getCategory()));
        schedule.add(new TimeTableOfDay(DayOfWeek.TUESDAY.name(), getSortedGroups.get(1).getCategory()));
        schedule.add(new TimeTableOfDay(DayOfWeek.WEDNESDAY.name(), getSortedGroups.get(2).getCategory()));
        schedule.add(new TimeTableOfDay(DayOfWeek.THURSDAY.name(), getSortedGroups.get(3).getCategory()));
        schedule.add(new TimeTableOfDay(DayOfWeek.FRIDAY.name(), getSortedGroups.get(4).getCategory()));
        schedule.add(new TimeTableOfDay(DayOfWeek.SATURDAY.name(), getSortedGroups.get(5).getCategory()));

        return schedule;
    }

    private List<Group> getSplitGroup(List<Human> students, String category) {
        List<Group> groups = new ArrayList<>();
        List<Human> listOfStudents1 = new ArrayList<>();
        List<Human> listOfStudents2 = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Human human = students.get(i);
            int sort = sortService.sort(human.getLastName());
            if (sort == 1) {
                listOfStudents1.add(human);
                continue;
            }
            listOfStudents2.add(human);
        }
        Group group1 = new Group(category + 1, listOfStudents1);
        Group group2 = new Group(category + 2, listOfStudents2);
        groups.add(group1);
        groups.add(group2);
        return groups;
    }

    private List<Group> getSortedGroups(List<Group> groups) {
        boolean isContains = false;
        List<Group> sortedList = new ArrayList<>();
        sortedList.add(groups.get(0));
        groups.remove(groups.get(0));
        for (int i = 0; i < groups.size(); i++) {
            Group currentGroup = groups.get(i);
            Group searchGroup = sortedList.get(sortedList.size() - 1);
            String currentCategory = currentGroup.getCategory();
            String searchCategory = searchGroup.getCategory();
            char firstSearchLetter = searchCategory.charAt(0);
            char firstCurrentLetter = currentCategory.charAt(0);

            if (firstCurrentLetter == firstSearchLetter) {
                sortedList.add(currentGroup);
                groups.remove(currentGroup);
                i = 0;
                if (groups.size() == 0) {
                    break;
                }
                searchGroup = sortedList.get(sortedList.size() - 1);
                currentGroup = groups.get(i);

                if(searchGroup.getStudents().size()==0){
                    sortedList.add(currentGroup);
                    groups.remove(currentGroup);
                    i=-1;
                    continue;
                }
                if(currentGroup.getStudents().size()==0){
                    sortedList.add(currentGroup);
                    groups.remove(currentGroup);
                    i=-1;
                    continue;
                }

                for (int j = 0; j < currentGroup.getStudents().size(); j++) {
                    currentGroup = groups.get(i);

                    if(currentGroup.getStudents().size()==0){
                        sortedList.add(currentGroup);
                        groups.remove(currentGroup);
                        i=-1;
                        break;
                    }

                    Human currentStudent = currentGroup.getStudents().get(j);

                    boolean contains = searchGroup.getStudents().contains(currentStudent);
                    if (contains) {
                        isContains = true;
                    }

                    if (j == currentGroup.getStudents().size() - 1 && isContains == true) {
                        searchGroup = sortedList.get(sortedList.size() - 1);
                        if(i==groups.size()-1){
                            int entryIndex = sortedList.size();
                            i=0;
                            j=0;
                            List<Human> multiStudents = new ArrayList<>();
                            while(j<currentGroup.getStudents().size()){
                                if(i==groups.size()){
                                    Group multiGroup = new Group(String.valueOf(entryIndex),multiStudents);
                                    sortedList.add(multiGroup);
                                    break;
                                }
                                currentGroup = groups.get(i);
                                currentStudent = currentGroup.getStudents().get(j);
                                contains = searchGroup.getStudents().contains(currentStudent);
                                if(contains){
                                    multiStudents.add(currentStudent);
                                }
                                if(j==currentGroup.getStudents().size()-1){
                                    sortedList.add(currentGroup);
                                    groups.remove(currentGroup);
                                    j=0;
                                    continue;
                                }
                                j++;
                            }
                            break;
                        }
                        j = -1;
                        i++;
                        isContains = false;
                        continue;
                    }

                    if (j == currentGroup.getStudents().size() - 1) {
                        sortedList.add(currentGroup);
                        groups.remove(currentGroup);
                        i = -1;
                        break;
                    }
                }
            }
        }
        return sortedList;
    }
}