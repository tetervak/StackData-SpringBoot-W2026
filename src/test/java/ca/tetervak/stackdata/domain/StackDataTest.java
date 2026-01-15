package ca.tetervak.stackdata.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StackDataTest {

    /**
     * Test for the pop method in the StackData class.
     * The pop method removes and returns the top item from the stack.
     * If the stack is empty, it returns null.
     */

    @Test
    void testPop_RemovesAndReturnsTopItem() {
        // Arrange
        StackData stackData = new StackData(); // Stack initialized with "Item-3", "Item-2", "Item-1"

        // Act
        String result = stackData.pop();

        // Assert
        assertEquals("Item-3", result);
        assertFalse(stackData.isEmpty());
        assertEquals(2, stackData.getItems().size());
    }

    @Test
    void testPop_ReturnsNullWhenStackIsEmpty() {
        // Arrange
        StackData stackData = new StackData();

        // Clear the stack completely
        stackData.pop();
        stackData.pop();
        stackData.pop();

        // Act
        String result = stackData.pop();

        // Assert
        assertNull(result);
        assertTrue(stackData.isEmpty());
    }

    @Test
    void testPop_MultiplePopsWorkAsExpected() {
        // Arrange
        StackData stackData = new StackData();

        // Act & Assert
        assertEquals("Item-3", stackData.pop());
        assertEquals("Item-2", stackData.pop());
        assertEquals("Item-1", stackData.pop());
        assertNull(stackData.pop()); // Stack should now be empty
        assertTrue(stackData.isEmpty());
    }

    @Test
    void testPush_PushAndPopOneItem(){
        // Arrange
        StackData stackData = new StackData();

        // Act
        stackData.push("Item-4");

        // Assert
        assertEquals("Item-4", stackData.pop());
        assertEquals(3, stackData.getItems().size());
    }

    @Test
    void  testPush_PushAndPopMultipleItems(){
        StackData stackData = new StackData();
        stackData.push("Item-4");
        stackData.push("Item-5");
        stackData.push("Item-6");

        assertEquals("Item-6", stackData.pop());
        assertEquals("Item-5", stackData.pop());
        assertEquals("Item-4", stackData.pop());
        assertEquals(3, stackData.getItems().size());
    }

    @Test
    void testGetItems(){
        StackData stackData = new StackData();
        List<StackItem> items = stackData.getItems();

        assertEquals(3, items.size());
        assertEquals(3, items.get(0).order());
        assertEquals("Item-3", items.get(0).value());
        assertEquals(2, items.get(1).order());
        assertEquals("Item-2", items.get(1).value());
        assertEquals(1, items.get(2).order());
        assertEquals("Item-1", items.get(2).value());
    }
}