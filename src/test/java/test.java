import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class test {
    private Set<String> setOf (String... s) {
        Set<String> set = new TreeSet<>();
        Collections.addAll(set, s);
        return set;
    }

    @Test
    public void addTest() {
        PhoneBook ph = new PhoneBook();
        ph.add("Anna", setOf("8-800-555-35-35", "8329093844309"));
        assertEquals(setOf("8-800-555-35-35", "8329093844309"), ph.findNumber("Anna"));
        ph.add("Anna", setOf("8329093844309", "*32453"));
        assertEquals(setOf("8-800-555-35-35", "8329093844309", "*32453"), ph.findNumber("Anna"));
        boolean catchEx = false;
        try {
            ph.add("Anna", setOf(" +23134424223", "*2  21#"));
        } catch (IllegalArgumentException e) {
            catchEx = true;
        }
        assertTrue(catchEx);
    }

    @Test
    public void removeNameTest() {
        PhoneBook ph = new PhoneBook();
        ph.add("Valera", setOf("+7-937-132-24-54", "+23245664"));
        ph.removeName("Valera");
        assertNull(ph.findNumber("Valera"));
        ph.removeName("sd");
    }

    @Test
    public void removeNumberTest() {
        PhoneBook ph = new PhoneBook();
        ph.add("Santehnik", setOf("+7-937-132-24-54", "+23245664", "8-800-555-35-35"));
        ph.removeNumber("Santehnik", "8-800-555-35-35");
        assertEquals(setOf("+7-937-132-24-54", "+23245664"), ph.findNumber("Santehnik"));
        ph.removeNumber("wd", "34829");
    }

    @Test
    public void findNumberTest() {
        PhoneBook ph = new PhoneBook();
        ph.add("Richard", setOf("8329093844309", "*32453"));
        assertEquals(setOf("8329093844309", "*32453"), ph.findNumber("Richard"));
        assertNull(ph.findNumber("Phillip"));
    }

    @Test
    public void findNameTest() {
        PhoneBook ph = new PhoneBook();
        ph.add("Vlad", setOf("8329093844309", "89373231231"));
        ph.add("Igor", setOf("8329093844309", "89373231231"));
        assertEquals("Vlad", ph.findName("89373231231"));
        assertNull(ph.findName("893732312311"));
    }
}
