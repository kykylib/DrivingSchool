public class Human {
    private String firstName;
    private String lastName;
    private boolean A;
    private boolean B;
    private boolean C;

    public Human(String firstName, String lastName, boolean a, boolean b, boolean c) {
        this.firstName = firstName;
        this.lastName = lastName;
        A = a;
        B = b;
        C = c;
    }

    public boolean isA() {
        return A;
    }

    public boolean isB() {
        return B;
    }

    public boolean isC() {
        return C;
    }



    @Override
    public String toString() {
        return "\n" + "Human || " + "first name: " + firstName + " || last name: " + lastName +  " || group A: " + A + " || group B: " + B + " || group C: " + C + "\n" + "\n";
    }
}
