import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEntitiesStorage<T> implements BankEntitiesStorage<T> {
    private final Map<Object, T> storage = new HashMap();
    private final KeyExtractor<?,? super T> keyExtractor;

    public SimpleEntitiesStorage(KeyExtractor<?,? super T> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    @Override
    public void save(T entity) {
        storage.put(keyExtractor.extract(entity), entity);
    }

    @Override
    public void saveAll(List<? extends T> entities) {
        for (T entity : entities) {
            save(entity);
        }
    }

    @Override
    public T findByKey(Object key) {
        return storage.get(key);
    }

    @Override
    public List<T> findAll() {
        return (List) storage.values();
    }

    @Override
    public void deleteByKey(Object key) {
        storage.remove(key);
    }

    @Override
    public void deleteAll(List<? extends T> entities) {
        storage.values().removeAll(entities);
    }
}
