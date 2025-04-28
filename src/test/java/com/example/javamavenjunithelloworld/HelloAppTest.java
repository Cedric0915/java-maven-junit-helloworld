package com.example.javamavenjunithelloworld;

import com.example.javamavenjunithelloworld.TestingSecurityManager.TestExitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for HelloApp.
 * <p/>
 * A unit test aims to test all code and code paths of a specific class.
 */
@ExtendWith(MockitoExtension.class)
public class HelloAppTest {
    static SecurityManager originalSecurityManager;

    @BeforeAll
    public static void setup() {
        // Insert our own custom SecurityManager that throws an exception when System.exit() is called.
        //originalSecurityManager = System.getSecurityManager();
        //System.setSecurityManager(new TestingSecurityManager());
    }

    @AfterAll
    public static void tearDown() {
        // Reinsert the original SecurityManager now that we are done with these tests.
        //System.setSecurityManager(originalSecurityManager);
    }

    @Test
    public void testMain() {
        String[] args = {"1"};
        HelloApp.main(args);
    }

    @Test
    public void testBogusArgument() {
        String[] args = {"bicycle"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HelloApp.main(args);
        });
        assertEquals("Parameter not understood", exception.getMessage());
    }

    @Test
    public void testTooHighArgument() {
        String[] args = {"999"};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HelloApp.main(args);
        });

        assertThat(exception.getMessage(), is("Parameter not understood"));
    }

    @Test
    public void testDefaultArgument() {
        // Passing no arguments should work.
        String[] args = {};
        HelloApp.main(args);
    }

    @Test
    public void classInstanceForCodeCoverageTest() {
        // Strictly speaking this test doesn't achieve anything, because HelloApp contains only a single static
        // method, but for purposes of full code coverage it is included. In general,
        // it is easier to aim for full code coverage and be done with it, than to remember why class X is stuck at
        // 95% code coverage.
        new HelloApp();
    }
}
