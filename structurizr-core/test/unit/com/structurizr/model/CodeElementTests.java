package com.structurizr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodeElementTests {

    @Test
    void construction_WhenAFullyQualifiedNameIsSpecified() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertEquals("SomeComponent", codeElement.getName());
        assertEquals("com.structurizr.component.SomeComponent", codeElement.getType());
    }

    @Test
    void construction_WhenAFullyQualifiedNameIsSpecifiedInTheDefaultPackage() {
        CodeElement codeElement = new CodeElement("SomeComponent");
        assertEquals("SomeComponent", codeElement.getName());
        assertEquals("SomeComponent", codeElement.getType());
    }

    @Test
    void descriptionProperty() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertNull(codeElement.getDescription());

        codeElement.setDescription("Description");
        assertEquals("Description", codeElement.getDescription());
    }

    @Test
    void sizeProperty() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertEquals(0, codeElement.getSize());

        codeElement.setSize(123456);
        assertEquals(123456, codeElement.getSize());
    }

    @Test
    void languageProperty() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertEquals("Java", codeElement.getLanguage());

        codeElement.setLanguage("Scala");
        assertEquals("Scala", codeElement.getLanguage());
    }

    @Test
    void categoryProperty() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertNull(codeElement.getCategory());

        codeElement.setCategory("class");
        assertEquals("class", codeElement.getCategory());
    }

    @Test
    void visibilityProperty() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertNull(codeElement.getVisibility());

        codeElement.setVisibility("package");
        assertEquals("package", codeElement.getVisibility());
    }

    @Test
    void setUrl() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        codeElement.setUrl("https://structurizr.com");
        assertEquals("https://structurizr.com", codeElement.getUrl());
    }

    @Test
    void setUrl_ThrowsAnIllegalArgumentException_WhenAnInvalidUrlIsSpecified() {
        assertThrows(IllegalArgumentException.class, () -> {
            CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
            codeElement.setUrl("htt://blah");
        });
    }

    @Test
    void setUrl_DoesNothing_WhenANullUrlIsSpecified() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        codeElement.setUrl(null);
        assertNull(codeElement.getUrl());
    }

    @Test
    void setUrl_DoesNothing_WhenAnEmptyUrlIsSpecified() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        codeElement.setUrl(" ");
        assertNull(codeElement.getUrl());
    }

    @Test
    void construction_ThrowsAnIllegalArgumentException_WhenANullFullyQualifiedNameIsSpecified() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CodeElement(null);
        });
    }

    @Test
    void construction_ThrowsAnIllegalArgumentException_WhenAnEmptyFullyQualifiedNameIsSpecified() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CodeElement("  ");
        });
    }

    @Test
    void equals_ReturnsFalse_WhenComparedToNull() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertNotEquals(codeElement, null);
    }

    @Test
    void equals_ReturnsFalse_WhenComparedToDifferentTypeOfObject() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertNotEquals(codeElement, "hello");
    }

    @Test
    void equals_ReturnsFalse_WhenComparedToAnotherCodeElementWithADifferentType() {
        CodeElement codeElement1 = new CodeElement("com.structurizr.component.SomeComponent1");
        CodeElement codeElement2 = new CodeElement("com.structurizr.component.SomeComponent2");
        assertNotEquals(codeElement1, codeElement2);
    }

    @Test
    void equals_ReturnsFalse_WhenComparedToAnotherCodeElementWithTheSameType() {
        CodeElement codeElement1 = new CodeElement("com.structurizr.component.SomeComponent1");
        CodeElement codeElement2 = new CodeElement("com.structurizr.component.SomeComponent1");
        assertEquals(codeElement1, codeElement2);
    }

    @Test
    void getPackage_ReturnsThePackageName() {
        CodeElement codeElement = new CodeElement("com.structurizr.component.SomeComponent");
        assertEquals("com.structurizr.component", codeElement.getPackage());
    }

}
