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
    private Set<String> checkPhone(Set<String> phoneNumber) {
        if (phoneNumber == null) return new HashSet<>();
         phoneNumber.forEach(it -> {
             if (!Pattern.matches("[\\d+\\-*#]+", it))
                 throw new IllegalArgumentException("Неправильно набран номер: " + it);
        });
        return phoneNumber;
    }

    /** Внесение данных:
     * получает имя и номер;
     * вносит имя с номером телефона в виде множества;
     * добавляет к уже существующему имени новый номер;
     * использует Функцию checkPhone для проверки номера.
     */
    void addNumber (String name, String phoneNumber) {
        Set<String> ph = new HashSet<>();
        ph.add(phoneNumber);
        checkPhone(ph);
        ph.addAll(book.getOrDefault(name, new HashSet<>()));
        book.put(name,ph);
    }

    /** Внесение данных:
     * получает имя и множество номеров;
     * вносит имя с номерами телефонов в виде множества;
     * добавляет к уже существующему имени новые номера;
     * использует Функцию checkPhone для проверки номеров.
     */
    void addNumbers(String name, Set<String> phoneNumbers) {
        Set<String> ph;
        ph = book.getOrDefault(name, new HashSet<>());
        ph.addAll(checkPhone(phoneNumbers));
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
        if (book.get(name) != null) {
            Set<String> phones = book.get(name);
            phones.remove(phoneNumber);
        }
    }

    /** Поиск номера по имени:
     * получает имя;
     * возвращает номер\номера телефонов;
     * Null и несуществующее имя игнорирует.
     */
    Set<String> findNumber(String name) {
        Set<String> set = book.get(name);
        if (set == null) return null;
        return Collections.unmodifiableSet(set);
    }

    /** Поиск имени по номеру телефона:
     * получает номер;
     * возвращает имя, если оно есть;
     * при значении Null и неправильном/не входящем в книгу номере возвращает Null.
     */
    String findName(String phoneNumber) {
        for (String name: book.keySet()) {
          if (book.get(name).contains(phoneNumber)) return name;
        }
        return null;
    }
}
