package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UrgencyLevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UrgencyLevel(null));
    }

    @Test
    public void constructor_invalidUrgencyLevel_throwsIllegalArgumentException() {
        String invalidUrgencyLevel = "";
        assertThrows(IllegalArgumentException.class, () -> new UrgencyLevel(invalidUrgencyLevel));
    }

    @Test
    public void isValidUrgencyLevel() {
        // null urgency level
        assertThrows(NullPointerException.class, () -> UrgencyLevel.isValidUrgencyLevel(null));

        // invalid urgency levels
        assertFalse(UrgencyLevel.isValidUrgencyLevel("")); // empty string
        assertFalse(UrgencyLevel.isValidUrgencyLevel(" ")); // spaces only
        assertFalse(UrgencyLevel.isValidUrgencyLevel("urgent")); // not a valid level
        assertFalse(UrgencyLevel.isValidUrgencyLevel("12345")); // numeric

        // valid urgency levels
        assertTrue(UrgencyLevel.isValidUrgencyLevel("low"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("lOw")); // case-insensitive
        assertTrue(UrgencyLevel.isValidUrgencyLevel("moderate"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("high"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("extreme"));
    }

    @Test
    public void getPriorityValue() {
        // Tests the numerical weight used for sorting
        assertEquals(1, new UrgencyLevel("low").getPriorityValue());
        assertEquals(2, new UrgencyLevel("moderate").getPriorityValue());
        assertEquals(3, new UrgencyLevel("high").getPriorityValue());
        assertEquals(4, new UrgencyLevel("extreme").getPriorityValue());
    }

    @Test
    public void equals() {
        UrgencyLevel lowUrgency = new UrgencyLevel("low");
        UrgencyLevel moderateUrgency = new UrgencyLevel("moderate");

        // same values -> returns true
        assertTrue(lowUrgency.equals(new UrgencyLevel("low")));

        // same object -> returns true
        assertTrue(lowUrgency.equals(lowUrgency));

        // null -> returns false
        assertFalse(lowUrgency.equals(null));

        // different types -> returns false
        assertFalse(lowUrgency.equals(5.0f));

        // different values -> returns false
        assertFalse(lowUrgency.equals(moderateUrgency));
    }

    @Test
    public void hashCodeMethod() {
        UrgencyLevel extreme = new UrgencyLevel("extreme");
        UrgencyLevel extremeCopy = new UrgencyLevel("extreme");
        UrgencyLevel low = new UrgencyLevel("low");

        // same value -> same hashcode
        assertEquals(extreme.hashCode(), extremeCopy.hashCode());

        // different value -> different hashcode
        assertNotEquals(extreme.hashCode(), low.hashCode());
    }

    @Test
    public void toStringMethod() {
        // Ensures the display string matches the internal enum name
        assertEquals("LOW", new UrgencyLevel("low").toString());
        assertEquals("EXTREME", new UrgencyLevel("extreme").toString());
    }
}
