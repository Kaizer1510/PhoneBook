
import org.jetbrains.annotations.Contract;

import java.util.*;
import java.util.regex.Pattern;

class PhoneBook {
    private final HashMap<String, Set<String>> book = new HashMap<>();


    @Contract("null -> null")
    private Set<String> checkPhone(Set<String> phone) {
        if (phone == null) return null;
         phone.forEach(it -> {
             if (!Pattern.matches("[\\d+\\-*#]+", it))
                 throw new IllegalArgumentException("Неправильно набран номер: " + it);
        });
        return phone;
    }

    void add(String name, Set<String> phone) {
        Set<String> ph;
        ph = book.getOrDefault(name, new TreeSet<>());
        ph.addAll(checkPhone(phone));
        book.put(name, ph);
    }

    void removeName(String name) {
        book.remove(name);
    }

    void removeNumber(String name, String phone) {
        if (book.get(name) != null) {
            Set<String> phones = book.get(name);
            phones.remove(phone);
            book.replace(name, phones);
        }
    }

    Set<String> findNumber(String name) {
        //if (book.containsKey(name))
        return book.get(name);
        // else throw new IllegalArgumentException("Книга не содержит данное имя");
    }

    String findName(String phone) {
        for (String name: book.keySet()) {
          if (book.get(name).contains(phone)) return name;
        }
        return null;
       // throw new IllegalArgumentException("Книга не содержит данный номер");
    }
}
