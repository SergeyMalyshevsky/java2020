import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Ivan", 18));
        personList.add(new Person("Fedor", 19));
        personList.add(new Person("Stepan", 20));
        personList.add(new Person("Ilya", 21));
        personList.add(new Person("Petr", 22));

        ReportGenerator<Person> reportGenerator = new ReportGeneratorImpl<>();
        Report report = reportGenerator.generate(personList);
        OutputStream output = new FileOutputStream("person.csv");
        report.writeTo(output);
    }
}
