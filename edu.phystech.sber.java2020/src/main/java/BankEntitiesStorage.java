import java.util.List;

public interface BankEntitiesStorage<T> {
    void save(T entity);

    void saveAll(List<? extends T> entities);

    T findByKey(T key);

    List<T> findAll();

    void deleteByKey(T key);

    void deleteAll(List<? extends T> entities);
}

