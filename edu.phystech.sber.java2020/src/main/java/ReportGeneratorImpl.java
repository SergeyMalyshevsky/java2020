import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ReportGeneratorImpl<T> implements ReportGenerator<T> {
    private List<String> columnNames;

    @Override
    public Report generate(List<T> entities) {
        List<List<String>> objectList = new ArrayList<>();
        List<String> header = new ArrayList<>();

        if (entities !=null && ! entities.isEmpty()){
            T firstEntity = entities.get(0);
            Field[] fields = firstEntity.getClass().getDeclaredFields();

            for (Field field : fields) {
                header.add(field.getName());
            }
            objectList.add(header);

            for (T entity : entities) {
                List<String> row = new ArrayList<>();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        row.add(field.get(entity).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                objectList.add(row);
            }
        }
        return new ReportCSV(objectList);
    }
}
