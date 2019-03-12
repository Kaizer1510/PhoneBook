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
        assertNull(ph.findName("Valera"));

    }

/*"89373231231", "8-800-555-35-35", "8329093844309", "*32453",
 "+7-937-132-24-54", "+23245664", " +23134424223", "*2  21#", "0", "12345678910",
 "dsf", "", "  ", null
 "Anna", "Valera", "Richard", "Phillip", "Santehnik", "", "  ", null**/
}
