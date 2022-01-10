package collection;

public class Person implements Comparable<Person> {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Person(String name, String sex, int weight, int height, int age) {
        super();
        this.name = name;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public Person() {
        super();
    }

    public Person(String name, String sex) {
        super();
        this.name = name;
        this.sex = sex;
    }

    public String name;
    public String sex;
    public int weight;
    public int height;
    public int age;


    public int compareTo(Person o) {
        if (getAge() > o.getAge()) return 1;
        else if (getAge() < o.getAge()) return -1;
        return 0;
    }
}
