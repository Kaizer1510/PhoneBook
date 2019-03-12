import org.junit.Test;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
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
        boolean cathEx = false;
        try {
            ph.add("Anna", setOf(" +23134424223", "*2  21#"));
        } catch (IllegalArgumentException e) {
            cathEx = true;
        }
        assertTrue(cathEx);
    }

    @Test
    public void removeNameTest() {
        
    }

/**"89373231231", "8-800-555-35-35", "8329093844309", "*32453",
 "+7-937-132-24-54", "+23245664", " +23134424223", "*2  21#", "0", "12345678910",
 "dsf", "", "  ", null
 "Anna", "Valera", "Richard", "Phillip", "Santehnik", "", "  ", null**/
}
