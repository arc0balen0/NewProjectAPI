import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10_000_000; i++) {
            String name = names.get(random.nextInt(names.size()));
            String family = families.get(random.nextInt(families.size()));
            int age = random.nextInt(100);
            Sex sex = Sex.values()[random.nextInt(Sex.values().length)];
            Education education = Education.values()[random.nextInt(Education.values().length)];

            persons.add(new Person(name, family, age, sex, education));
        }

        // Найти количество несовершеннолетних (людей младше 18 лет)
        long countMinors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + countMinors);

        // Получить список фамилий призывников (мужчин от 18 до 27 лет)
        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + conscripts);

        // Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        List<Person> potentialWorkers = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER &&
                        ((person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60) ||
                                (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Отсортированный список потенциально работоспособных людей: " + potentialWorkers);
    }
}
