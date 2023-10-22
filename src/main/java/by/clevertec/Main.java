package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19("C-3");
        task20();
        task21();
        task22();
    }

    public static List<Animal> task1() {
        List<Animal> animals = Util.getAnimals();
        int countAnimalInZoo = 7;
        List<Animal> animalList = animals.stream()
                .filter(a -> a.getAge() >= 10 && a.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(2 * countAnimalInZoo)
                .limit(countAnimalInZoo)
                .toList();
        System.out.println("Task1");
        animalList.forEach(System.out::println);
        return animalList;
    }

    public static List<String> task2() {
        List<Animal> animals = Util.getAnimals();
        List<String> breed = animals.stream()
                .filter(a -> a.getOrigin().equals("Japanese"))
                .peek(a -> a.setBread(a.getBread().toUpperCase()))
                .filter(a -> a.getGender().equals("Female"))
                .map(Animal::getBread)
                .toList();
        System.out.println("\nTask2");
        breed.forEach(System.out::println);
        return breed;
    }

    public static List<String> task3() {
        List<Animal> animals = Util.getAnimals();
        List<String> countries = animals.stream()
                .filter(a -> a.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(a -> a.startsWith("A"))
                .distinct()
                .toList();
        System.out.println("\nTask3");
        countries.forEach(System.out::println);
        return countries;
    }

    public static long task4() {
        List<Animal> animals = Util.getAnimals();
        long countFemaleAnimals = animals.stream()
                .filter(a -> a.getGender().equals("Female"))
                .count();
        System.out.println("\nTask4");
        System.out.println("Count of all animals of the Female gender: " + countFemaleAnimals);
        return countFemaleAnimals;
    }

    public static List<Animal> task5() {
        List<Animal> animals = Util.getAnimals();
        List<Animal> animalsFromHungarian = animals.stream()
                .filter(a -> a.getOrigin().equals("Hungarian"))
                .toList();
        System.out.println("\nTask5");
        animalsFromHungarian.forEach(System.out::println);
        return animalsFromHungarian;
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .map(Animal::getGender)
                .allMatch(s -> s.equals("Female|Male"));
        System.out.println("\nTask6");
        if (result) {
            System.out.println("All animals are female or male");
        } else {
            System.out.println("Not all animals are female or male");
        }
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .map(Animal::getOrigin)
                .noneMatch(s -> s.equals("Oceania"));
        System.out.println("\nTask7");
        if (result) {
            System.out.println("No animals are from Oceania");
        } else {
            System.out.println("There are animals from Oceania");
        }
    }

    public static Animal task8() {
        List<Animal> animals = Util.getAnimals();
        Animal oldestAnimal = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparingInt(Animal::getAge))
                .get();
        System.out.println("\nTask8");
        System.out.println(oldestAnimal);
        return oldestAnimal;
    }

    public static int task9() {
        List<Animal> animals = Util.getAnimals();
        int shortestLength = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingLong(chars -> chars.length))
                .get()
                .length;
        System.out.println("\nTask9");
        System.out.println("Length of the shortest array: " + shortestLength);
        return shortestLength;
    }

    public static int task10() {
        List<Animal> animals = Util.getAnimals();
        int sumAge = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println("\nTask10");
        System.out.println("The total age of all animals: " + sumAge);
        return sumAge;
    }

    public static Double task11() {
        List<Animal> animals = Util.getAnimals();
        Double averageAge = animals.stream()
                .filter(a -> a.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .orElse(0.0);
        System.out.println("\nTask11");
        System.out.println("Average age of all animals from Indonesia: " + averageAge);
        return averageAge;
    }

    public static List<Person> task12() {
        List<Person> persons = Util.getPersons();
        LocalDate dateNow = LocalDate.now();
        LocalDate eighteen = dateNow.minusYears(18);
        LocalDate twentySeven = dateNow.minusYears(27);

        List<Person> maleForAcademy = persons.stream()
                .filter(p -> p.getGender().equals("Male"))
                .filter(p -> p.getDateOfBirth().isBefore(eighteen)
                        && p.getDateOfBirth().isAfter(twentySeven))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .toList();
        System.out.println("\nTask12");
        maleForAcademy.forEach(System.out::println);
        return maleForAcademy;
    }

    public static List<Person> task13() {
        List<House> houses = Util.getHouses();
        LocalDate dateNow = LocalDate.now();
        LocalDate eighteen = dateNow.minusYears(18);
        LocalDate malePensionYear = dateNow.minusYears(63);
        LocalDate femalePensionYear = dateNow.minusYears(58);

        List<Person> people = houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(p -> Map.entry(
                                house.getBuildingType().equals("Hospital") ? 1 :
                                        p.getDateOfBirth().isAfter(eighteen) ||
                                                (p.getGender().equals("Male") &&
                                                        p.getDateOfBirth().isBefore(malePensionYear)) ||
                                                (p.getGender().equals("Female") &&
                                                        p.getDateOfBirth().isBefore(femalePensionYear)) ? 2 :
                                                3, p)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, toList())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .flatMap(e -> e.getValue().stream())
                .limit(500)
                .toList();
        System.out.println("\nTask13");
        people.forEach(System.out::println);
        return people;
    }

    public static double task14() {
        List<Car> cars = Util.getCars();
        Double transportationCostPerTon = 7.14;
        double totalRevenue = cars.stream()
                .map(car -> Map.entry(
                        car.getCarModel().equals("Jaguar") || car.getColor().equals("White")
                                ? "Turkmenistan" :
                        car.getMass() < 1500 && List.of("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake())
                                ? "Uzbekistan" :
                        car.getColor().equals("Black") && car.getMass() > 4000 || List.of("GMC", "Dodge").contains(car.getCarMake())
                                ? "Kazakhstan" :
                        car.getReleaseYear() < 1982 || List.of("Civic", "Cherokee").contains(car.getCarMake())
                                ? "Kyrgyzstan" :
                        List.of("Yellow", "Red", "Green", "Blue").contains(car.getColor()) || car.getPrice() <= 40000
                                ? "Russia" :
                        car.getVin().contains("59")
                                ? "Mongolia" : "Discarded",
                        car))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, toList())))//Здесь имеем Map: key - ешелон, value -  лист машин, отправленных в ешелон
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().
                                stream()
                                .mapToInt(Car::getMass)
                                .sum()))//Здесь имеем Map: key - ешелон, value -  суммарные массы автомобилей для ешелона
             .entrySet().stream()
             .collect(Collectors.toMap(
                     Map.Entry::getKey,
                     entry -> entry.getValue() * transportationCostPerTon
             ))//Здесь имеем Map: key - ешелон, value -  суммарные транспортные расходы для ешелона
             .values().stream()
                .mapToDouble(Double::doubleValue)
                .sum(); //общие расходы ешелонов, или выручка логистической компании

        System.out.println("\nTask14");
        String formattedValue = String.format("%.2f", totalRevenue);
        System.out.println("Total revenue: " + formattedValue + " $");
        return totalRevenue;
    }

    public static double task15() {
        List<Flower> flowers = Util.getFlowers();
        List<String> vaseMaterial = List.of("Glass", "Aluminum", "Steel");
        double costOfCubicMeterOfWater = 1.39;
        double totalcostOfMainTenanceOfPlans = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(f -> f.getCommonName().matches("^[C-S].*$"))
                .filter(Flower::isShadePreferred)
                .filter(f -> f.getFlowerVaseMaterial()
                        .stream()
                        .anyMatch(vaseMaterial::contains))
                .mapToDouble(f -> {
                    double waterCostFor5Years = f.getWaterConsumptionPerDay() * 365 * 5 * costOfCubicMeterOfWater;
                    return f.getPrice() + waterCostFor5Years;
                })
                .sum();

        System.out.println("\nTask15");
        String formattedValue = String.format("%.2f", totalcostOfMainTenanceOfPlans);
        System.out.println("Total cost of maintenance of all plants: " + formattedValue  + " $");
        return totalcostOfMainTenanceOfPlans;
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        System.out.println("\nTask16");
        students.stream()
                .filter(s -> s.getAge() <= 18) //По условию: Вывод списка студентов младше 18 лет, но таких нет, поэтому хотя бы те кому 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(student -> {
                    System.out.println("Surname: " + student.getSurname() +
                            ", Faculty: " + student.getFaculty() +
                            ", Age: " + student.getAge());
                });
    }

    public static List<String> task17() {
        List<Student> students = Util.getStudents();
        List<String> studentGroups = students.stream()
                .map(Student::getGroup)
                .distinct()
                .toList();
        System.out.println("\nTask17");
        studentGroups.forEach(System.out::println);
        return studentGroups;
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        System.out.println("\nTask18");
        students.stream()
                .map(s -> Map.entry(s.getFaculty(), s))
                .collect(
                        Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, toList())))
                .entrySet().stream()//Здесь имеем Map: key - факультет, value -  лист студентов факультета
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .mapToInt(Student::getAge)
                                .average()
                                .orElse(0.0)))
                .entrySet().stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                .forEach(entry -> {
                    String formattedValue = String.format("%.1f", entry.getValue());
                    System.out.println(entry.getKey() + ": " + formattedValue);
                });
    }

    public static List<Student> task19(String group) {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        Map<Integer, List<Examination>> examsByStudentId = examinations.stream()
                .collect(Collectors.groupingBy(Examination::getStudentId));

        List<Student> studentsWithGoodExam =
                students.stream()
                .filter(s -> s.getGroup().equals(group))
                .toList()
                        .stream()
                        .filter(s -> {
                            List<Examination> studentExams  = examsByStudentId.get(s.getId());
                            return studentExams != null && studentExams.stream().anyMatch(exam -> exam.getExam3() > 4);
                        })
                        .toList();
        System.out.println("\nTask19");
        studentsWithGoodExam.forEach(System.out::println);
        return studentsWithGoodExam;
    }

    public static String task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        String facultyWithMaxAverageExam1Score  = students.stream()
                .map(s -> {
                    int exam1 = examinations.stream()
                            .filter(e -> e.getStudentId() == s.getId())
                            .mapToInt(Examination::getExam1)
                            .findFirst()
                            .orElse(0);
                    return new AbstractMap.SimpleEntry<>(s.getFaculty(), exam1);
                })
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey,
                        Collectors.averagingInt(AbstractMap.SimpleEntry::getValue)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Нет данных");

        System.out.println("\nTask20");
        System.out.println("Faculty with max average Exam1 score: " + facultyWithMaxAverageExam1Score);
        return facultyWithMaxAverageExam1Score;
    }

    public static Map<String, Long> task21() {
        List<Student> students = Util.getStudents();
        Map<String, Long> countOfStudentsInGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGroup,
                        Collectors.mapping(Student::getId, toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (long) entry.getValue()
                                .size()));

        System.out.println("\nTask21");
        countOfStudentsInGroup
                .forEach((key, value) -> System.out.println("Faculty: " + key + ", count of students : " + value));
        return countOfStudentsInGroup;
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        System.out.println("\nTask22");
        students.stream()
                .map(s -> Map.entry(s.getFaculty(), s))
                .collect(
                        Collectors.groupingBy(Map.Entry::getKey,
                                Collectors.mapping(Map.Entry::getValue, toList())))
                .entrySet().stream()//Здесь имеем Map: key - факультет, value -  лист студентов факультета
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .mapToInt(Student::getAge)
                                .min()
                                .orElse(0)))
                .forEach((key, value) -> System.out.println("Faculty: " + key + ", min age of students : " + value));
    }
}
