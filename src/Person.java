import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private String family;
    private Integer age;
    private Sex sex;
    private Education education;

    public Person(String name, String family, int age, Sex sex, Education education) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.sex = sex;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Education getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", education=" + education +
                '}';
    }

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(
                new Person("Иван", "Петров", 25, Sex.MAN, Education.HIGHER),
                new Person("Елена", "Иванова", 23, Sex.WOMAN, Education.HIGHER),
                new Person("Мария", "Сидорова", 35, Sex.WOMAN, Education.FURTHER),
                new Person("Петр", "Сидоров", 20, Sex.MAN, Education.SECONDARY),
                new Person("Анна", "Кузнецова", 18, Sex.WOMAN, Education.HIGHER),
                new Person("Сергей", "Смирнов", 30, Sex.MAN, Education.HIGHER),
                new Person("Ольга", "Петрова", 40, Sex.WOMAN, Education.HIGHER),
                new Person("Дмитрий", "Иванов", 22, Sex.MAN, Education.HIGHER),
                new Person("Александр", "Кузнецов", 19, Sex.MAN, Education.SECONDARY),
                new Person("Наталья", "Смирнова", 50, Sex.WOMAN, Education.HIGHER)
        );

        // Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long underageCount = personList.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println("Количество несовершеннолетних: " + underageCount);

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> conscriptSurnames = personList.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Список фамилий призывников: " + conscriptSurnames);

        // Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием.
        List<Person> potentialWorkers = personList.stream()
                .filter(person -> person.getEducation() == Education.HIGHER &&
                        ((person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60) ||
                                (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Отсортированный список потенциально работоспособных людей: " + potentialWorkers);
    }
}
