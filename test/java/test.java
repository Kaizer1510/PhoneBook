import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class test {
    /** Функция для упрощённого ввода данных:
     * переводит перечисленные номера в множество номеров
     */
    private Set<String> setOf (String... phoneNumber) {
        Set<String> setOfNumbers = new HashSet<>();
        Collections.addAll(setOfNumbers, phoneNumber);
        return setOfNumbers;
    }

    /** Создаётся новая пустая книжка для всех тестов
     */
    private PhoneBook ph = new PhoneBook();

    /** Тест для метода внесения данных:
     * для проверки используется метод нахождения номеров;
     * + проверяет функцию определения корректности номера телефона.
     */
    @Test
    public void add() {
        ph.add("Anna", setOf("8-800-555-35-35", "8329093844309"));
        assertEquals(setOf("8-800-555-35-35", "8329093844309"), ph.findNumber("Anna"));
        ph.add("Anna", setOf("8329093844309", "*32453")); //Проверяет рекцию на существующий номер;
        assertEquals(setOf("8-800-555-35-35", "8329093844309", "*32453"), ph.findNumber("Anna"));
        boolean catchEx = false;                                        //
        try {                                                          //
            ph.add("Anna", setOf(" +23134424223", "*2  21#")); //Проверяет рекцию на неправильный номер;
        } catch (IllegalArgumentException e) {                      //
            catchEx = true;                                        //
        }                                                         //
        assertTrue(catchEx);                                     //
        ph.add("Valera", null); //Проверяет рекцию на null.
        assertEquals(new TreeSet<>(), ph.findNumber("Valera"));
    }

    /** Тест для метода удаления имени:
     * для проверки используется метод нахождения номеров;
     */
    @Test
    public void removeName() {
        ph.add("Valera", setOf("+7-937-132-24-54", "+23245664"));
        ph.removeName("Valera");
        assertNull(ph.findNumber("Valera"));
        ph.removeName("sd"); //Проверяет рекцию на невнесённое имя;
        ph.removeName(null); //Проверяет рекцию на null.
    }

    /** Тест для метода удаления номера:
     * для проверки используется метод нахождения номеров.
     */
    @Test
    public void removeNumber() {
        ph.add("Santehnik", setOf("+7-937-132-24-54", "+23245664", "8-800-555-35-35"));
        ph.removeNumber("Santehnik", "8-800-555-35-35");
        assertEquals(setOf("+7-937-132-24-54", "+23245664"), ph.findNumber("Santehnik"));
        ph.removeNumber("Santehnik", "8"); //Проверяет рекцию на невнесённый номер;
        ph.removeNumber("Santehnik", null); //Проверяет рекцию на null в номере;
        ph.removeNumber("wd", "34829"); //Проверяет рекцию на невнесённое имя;
        ph.removeNumber(null, "34829"); //Проверяет рекцию на null в имени.
    }

    /** Тест для метода нахождения номера по имени.
     */
    @Test
    public void findNumber() {
        ph.add("Richard", setOf("8329093844309", "*32453"));
        Set<String> a = ph.findNumber("Richard");
        boolean catchEx = false;                         //
        try {                                           //
            a.add("dfg");                              //
        } catch (UnsupportedOperationException e) {   // Проверяет реакцию на попытку
            catchEx = true;                          //  изменить мнжество номеров;
        }                                           //
        assertTrue(catchEx);                       //
        assertEquals(setOf("8329093844309", "*32453"), ph.findNumber("Richard"));
        assertNull(ph.findNumber("Phillip")); //Проверяет рекцию на невнесённое имя;
        assertNull(ph.findNumber(null)); //Проверяет рекцию на null.
    }

    /** Тест для метода нахождения имени по номеру телефона.
     */
    @Test
    public void findName() {
        ph.add("Vlad", setOf("8329093844309", "89373231231"));
        ph.add("Igor", setOf("12143563", "824564542"));
        assertEquals("Vlad", ph.findName("89373231231"));
        assertNull(ph.findName("23563543")); //Проверяет рекцию на невнесённый номер;
        assertNull(ph.findName(null)); //Проверяет рекцию на null.
    }
}
