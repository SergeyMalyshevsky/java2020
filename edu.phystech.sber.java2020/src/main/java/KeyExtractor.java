public interface KeyExtractor<K, T> {
    <V extends T> K extract(T entity);
}
