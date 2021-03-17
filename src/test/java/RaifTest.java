import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.addAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("listFormat function Test")
public class RaifTest {


    private RaifPage page;
    private List<String> list;

    @BeforeEach
    void beforeTest() {
        page = new RaifPage();
        list = new ArrayList<>();
    }

    @DisplayName("Given list with Latin letters " +
            "When sortList method called " +
            "Then return sorted list values with capitalized first letters")
    @Test
    void listPrintTest() {
        addAll(list, "Bob", "Alice", "Joe", "bob", "alice", "dEN");
        List<String> expectedList = new ArrayList<>();
        addAll(expectedList, "Alice", "Alice", "Bob", "Bob", "Den", "Joe");
        assertEquals(expectedList, page.sortList(list));
    }

    @DisplayName("Given list with null value " +
            "When function called " +
            "Then return Exception with message")
    @Test
    void listContainsNullTest() {
        addAll(list, null, "Alice", "Joe", "bob", "alice", "dEN");
        assertThrows(Exception.class, () -> page.listFormat(list), "В списке присутствует null");
    }

    @DisplayName("Given list with not Latin value " +
            "When function called " +
            "Then return Exception with message")
    @Test
    void listContainsLatinTest() {
        addAll(list, "фываыв", "Alice", "Joe", "bob", "alice", "dEN");
        assertThrows(Exception.class, () -> page.listFormat(list), "В списке должны присутсвовать только латинские буквы");
    }

    @DisplayName("Given list with Latin letters with 3 repeated values " +
            "When function called " +
            "Then return response contains Alice: 3")
    @Test
    void expectedValueTest() throws Exception {
        addAll(list, "Bob", "Alice", "Joe", "bob", "alice", "dEN", "aLice");
        assertEquals(3, page.listFormat(list).get("Alice"));
    }

    @DisplayName("Given list with Latin letters " +
            "When function called " +
            "Then return response contains only first letter Capitalize")
    @Test
    void expectedFirstLetterCapitalizeTest() throws Exception {
        addAll(list, "Bob", "Alice", "Joe", "bob", "alice", "dEN");
        assertTrue(page.listFormat(list).keySet().stream().allMatch(s -> s.matches("^[A-Z][a-z]*$")));
    }


    @DisplayName("Given list with Latin letters " +
            "When function called " +
            "Then return valid response")
    @Test
    void SmokeTest() throws Exception {
        addAll(list, "Bob", "Alice", "Joe", "bob", "alice", "dEN");
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("Alice", 2);
        expectedMap.put("Bob", 2);
        expectedMap.put("Den", 1);
        expectedMap.put("Joe", 1);
        assertEquals(expectedMap, page.listFormat(list));
    }
}
