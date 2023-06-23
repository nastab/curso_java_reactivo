import java.util.Set;

public class Employee {
    private String id;
    private String name;
    private Set<String> skills;

    public Employee(String id, String name, Set<String> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getSkills() {
        return skills;
    }
}
