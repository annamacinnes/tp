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

        // blank/whitespace urgency levels
        assertFalse(UrgencyLevel.isValidUrgencyLevel("")); // empty string
        assertFalse(UrgencyLevel.isValidUrgencyLevel(" ")); // spaces only
        assertFalse(UrgencyLevel.isValidUrgencyLevel(" high ")); // leading/trailing spaces
        assertFalse(UrgencyLevel.isValidUrgencyLevel("low ")); // trailing space

        // invalid urgency levels
        assertFalse(UrgencyLevel.isValidUrgencyLevel("urgent")); // not a valid level
        assertFalse(UrgencyLevel.isValidUrgencyLevel("12345")); // numeric
        assertFalse(UrgencyLevel.isValidUrgencyLevel("extreme!")); // contains symbols

        // valid urgency levels
        assertTrue(UrgencyLevel.isValidUrgencyLevel("low"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("moderate"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("high"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("extreme"));

        // valid urgency levels (case-insensitive)
        assertTrue(UrgencyLevel.isValidUrgencyLevel("LOW"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("lOw"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("MoDeRaTe"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("HIGH"));
        assertTrue(UrgencyLevel.isValidUrgencyLevel("EXTREME"));
    }

    @Test
    public void getPriorityValue() {
        // Tests the numerical weight used for sorting
        assertEquals(1, new UrgencyLevel("low").getPriorityValue());
        assertEquals(2, new UrgencyLevel("moderate").getPriorityValue());
        assertEquals(3, new UrgencyLevel("high").getPriorityValue());
        assertEquals(4, new UrgencyLevel("extreme").getPriorityValue());

        // Tests that case-insensitivity still maps to the correct priority
        assertEquals(4, new UrgencyLevel("eXtReMe").getPriorityValue());
        assertEquals(3, new UrgencyLevel("HIGH").getPriorityValue());
    }

    @Test
    public void equals() {
        UrgencyLevel lowUrgency = new UrgencyLevel("low");
        UrgencyLevel moderateUrgency = new UrgencyLevel("moderate");

        // same values -> returns true
        assertTrue(lowUrgency.equals(new UrgencyLevel("low")));

        // case-insensitive equality -> returns true
        assertTrue(new UrgencyLevel("high").equals(new UrgencyLevel("HIGH")));
        assertTrue(new UrgencyLevel("eXtrEmE").equals(new UrgencyLevel("extreme")));

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

        // case-insensitivity -> same hashcode
        UrgencyLevel mixedCase = new UrgencyLevel("eXtReMe");
        assertEquals(extreme.hashCode(), mixedCase.hashCode());

        // different value -> different hashcode
        assertNotEquals(extreme.hashCode(), low.hashCode());
    }

    @Test
    public void toStringMethod() {
        // Ensures the display string matches the internal enum name completely
        assertEquals("LOW", new UrgencyLevel("low").toString());
        assertEquals("MODERATE", new UrgencyLevel("moderate").toString());
        assertEquals("HIGH", new UrgencyLevel("high").toString());
        assertEquals("EXTREME", new UrgencyLevel("extreme").toString());

        // Ensures weird casing is normalized in the toString
        assertEquals("HIGH", new UrgencyLevel("hIgH").toString());
    }
}
