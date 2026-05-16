package problems.p204_count_primes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Edge cases ---

    @Test
    void testZero_returnsZero() {
        assertEquals(0, solution.countPrimes(0));
    }

    @Test
    void testOne_returnsZero() {
        assertEquals(0, solution.countPrimes(1));
    }

    @Test
    void testTwo_returnsZero() {
        // No primes strictly less than 2
        assertEquals(0, solution.countPrimes(2));
    }

    @Test
    void testThree_returnsOne() {
        // Only prime < 3 is 2
        assertEquals(1, solution.countPrimes(3));
    }

    // --- LeetCode examples ---

    @Test
    void testTen_returnsFour() {
        // Primes: 2, 3, 5, 7
        assertEquals(4, solution.countPrimes(10));
    }

    // --- Small boundary values ---

    @Test
    void testFive_returnsTwo() {
        // Primes: 2, 3
        assertEquals(2, solution.countPrimes(5));
    }

    @Test
    void testSix_returnsThree() {
        // Primes: 2, 3, 5
        assertEquals(3, solution.countPrimes(6));
    }

    @Test
    void testTwelve_returnsFive() {
        // Primes: 2, 3, 5, 7, 11
        assertEquals(5, solution.countPrimes(12));
    }

    // --- Boundary: n is prime itself ---

    @Test
    void testNisPrime_doesNotCountN() {
        // n=11 is prime but we count strictly less than n
        // Primes < 11: 2, 3, 5, 7 → 4
        assertEquals(4, solution.countPrimes(11));
    }

    @Test
    void testNisPrime_13() {
        // Primes < 13: 2, 3, 5, 7, 11 → 5
        assertEquals(5, solution.countPrimes(13));
    }

    // --- Overflow guard: large i*i ---

    @Test
    void testLargeN_noOverflow() {
        // Tests (long) i * i overflow guard
        assertEquals(3401, solution.countPrimes(31623));
    }

    // --- Performance: large input ---

    @Test
    void testVeryLargeN_performance() {
        // Should complete well within time limits
        assertEquals(78498, solution.countPrimes(1000000));
    }

    // --- Known prime counts ---

    @Test
    void testHundred_returns25() {
        // There are exactly 25 primes below 100
        assertEquals(25, solution.countPrimes(100));
    }
}