import org.jetbrains.annotations.Contract;
import java.util.*;
import java.util.regex.Pattern;

class PhoneBook {
    /** Основа класса - ассоциативный массив
     */
    private final Map<String, Set<String>> book = new HashMap<>();

    /** Проверка входных данных:
     * получает множество номеров;
     * выбрасывает ошибку при неправильном номере;
     * значение null переводится в пустое множество
     */
    @Contract("null -> new")
    private Set<String> checkPhone(Set<String> phoneNumber) {
        if (phoneNumber == null) return new TreeSet<>();
         phoneNumber.forEach(it -> {
             if (!Pattern.matches("[\\d+\\-*#]+", it))
                 throw new IllegalArgumentException("Неправильно набран номер: " + it);
        });
        return phoneNumber;
    }

    /** Внесение данных:
     * получает имя и множество номеров;
     * вносит имя с номером/номерами телефонов в виде множества;
     * добавляет к уже существующему имени новый номер;
     * использует Функцию checkPhone для проверки номеров.
     */
    void add(String name, Set<String> phoneNumber) {
        Set<String> ph;
        ph = book.getOrDefault(name, new TreeSet<>());
        ph.addAll(checkPhone(phoneNumber));
        book.put(name, ph);
    }

    /** Удаление имени:
     * получает имя;
     * удаляет имя с номером/номерами телефонов;
     * Null и несуществующее имя игнорирует
     */
    void removeName(String name) { book.remove(name); }

    /** Удаление номера:
     * получает имя и номер телефона;
     * удаляет номер телефона, если он существует;
     * Null и несуществующие данные игнорирует.
     */
    void removeNumber(String name, String phoneNumber) {
        if (book.get(name) != null && phoneNumber != null) {
            Set<String> phones = book.get(name);
            phones.remove(phoneNumber);
            book.replace(name, phones);
        }
    }

    /** Поиск номера по имени:
     * получает имя;
     * возвращает номер\номера телефонов;
     * Null и несуществующее имя игнорирует.
     */
    Set<String> findNumber(String name) { return book.get(name); }

    /** Поиск имени по номеру телефона:
     * получает номер;
     * возвращает имя, если оно есть;
     * при значении Null и неправильном/не входящем в книгу номере возвращает Null.
     */
    String findName(String phoneNumber) {
        if (phoneNumber == null) return null;
        for (String name: book.keySet()) {
          if (book.get(name).contains(phoneNumber)) return name;
        }
        return null;
    }
}
