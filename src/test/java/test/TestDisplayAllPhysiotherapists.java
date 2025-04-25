package test;

import Clinic.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDisplayAllPhysiotherapists {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create mock physiotherapist and setup demo data
        Physiotherapist physio = new Physiotherapist("Dr. Test", "Test Address", "123456");
        physio.setId("PHY999");

        AreaOfExpertise expertise = new AreaOfExpertise("TestArea");
        Treatment treatment = new Treatment("TestTreatment");
        expertise.addTreatment(treatment);
        physio.addExpertise(expertise);

        ScheduledSlot slot = new ScheduledSlot(
            "TST001", expertise, treatment, DayOfWeek.MONDAY,
            LocalDate.of(2025, 5, 5),
            LocalTime.of(9, 0), LocalTime.of(10, 0), physio
        );
        physio.addAssignedSlot(slot);

        // Set the private static field allPhysiotherapists in Clinic.Test
        try {
            Class<?> testClass = Class.forName("Clinic.Test");
            Field field = testClass.getDeclaredField("allPhysiotherapists");
            field.setAccessible(true);
            List<Physiotherapist> list = new ArrayList<>();
            list.add(physio);
            field.set(null, list);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Setup failed: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

 
   void testDisplayAllPhysiotherapistsPrintsExpectedOutput() throws Exception {
        Class<?> testClass = Class.forName("Clinic.Test");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        Method method = testClass.getDeclaredMethod("displayAllPhysiotherapists");
        method.setAccessible(true);
        method.invoke(null);

        String output = outputStream.toString();

        assertTrue(output.contains("Dr. Test"), "Should contain physiotherapist name");
        assertTrue(output.contains("TestArea"), "Should display area of expertise");
        assertTrue(output.contains("TestTreatment"), "Should display treatment");
        assertTrue(output.contains("TST001"), "Should include slot ID");
        assertTrue(output.contains("9:00 AM") && output.contains("10:00 AM"), "Should include formatted time slot");


    }
}
