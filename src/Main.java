
public class Main {

	public static boolean checkInterval(Interval i, int d, int h, int m, boolean sign, int hits, int total) {
		String s;
		char znak;
		// Model Intervalovog toString-a
		znak = (sign) ? '+' : '-';
		if (d > 0) {
			s = String.format("%c%02d.%02d:%02d", znak, d, h, m);
		} else {
			s = String.format("%c%02d:%02d", znak, h, m);
		}

		if (s.equals(i.toString())) {
			System.out.printf("Matching! Score: %d/%d \r\n", (hits + 1), (total));
			return true;
		}
		System.out.printf("Not Matching! (Interval: %s, Expected: %s) Score: %d/%d \r\n", i.toString(), s, (hits),
				(total));
		return false;
	}

	public static boolean checkIntervalEquality(Interval a, Interval b, boolean expect, int type, int hits, int total) {
		boolean equals = true;
		if (type == 1) {
			equals = a.equals(b);
		} else if (type == 2) {
			equals = a.greaterThan(b);
		} else if (type == 3) {
			equals = a.lessThan(b);
		}

		if (equals == expect) {
			System.out.printf("Matching! Score: %d/%d \r\n", (hits + 1), (total));
			return true;
		}
		System.out.printf(
				"Not Matching! (Interval1: %s, Interval2: %s, Calculated: %b, Expected: %b) Score: %d/%d \r\n",
				a.toString(), b.toString(), equals, expect, (hits), (total));
		return false;
	}

	public static boolean checkTimestamp(Timestamp t, int y, int mm, int d, int h, int m, Interval z, int hits,
			int total) {
		String s;
		// Model Timestampovog toString-a
		s = String.format("%4d-%02d-%02d %02d:%02d(UTC %s)", y, mm, d, h, m, z.toString());

		if (s.equals(t.toString())) {
			System.out.printf("Matching! Score: %d/%d \r\n", (hits + 1), (total));
			return true;
		}
		System.out.printf("Not Matching! (Timestamp: %s, Expected: %s) Score: %d/%d \r\n", t.toString(), s, (hits),
				(total));
		return false;
	}

	public static boolean checkTimestampEquality(Timestamp a, Timestamp b, boolean expect, int type, int hits,
			int total) {
		boolean equals = true;
		if (type == 1) {
			equals = a.equals(b);
		} else if (type == 2) {
			equals = a.greaterThan(b);
		} else if (type == 3) {
			equals = a.lessThan(b);
		}

		if (equals == expect) {
			System.out.printf("Matching! Score: %d/%d \r\n", (hits + 1), (total));
			return true;
		}
		System.out.printf(
				"Not Matching! (Timestamp1: %s, Timestamp2: %s, Calculated: %b, Expected: %b) Score: %d/%d \r\n",
				a.toString(), b.toString(), equals, expect, (hits), (total));
		return false;
	}

	public static void test() {
		Interval zero = new Interval(0, 0, 0, true);
		int total_cnt = 0;
		int correct_cnt = 0;

		// INTERVAL ADD TEST
		// Two positive intervals add
		correct_cnt = (checkInterval(new Interval(5, 4, 20, true).add(new Interval(3, 5, 34, true)), 8, 9, 54, true,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(43, 3, 20, true).add(new Interval(21, 12, 49, true)), 64, 16, 9, true,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(210, 17, 37, true).add(new Interval(2, 13, 58, true)), 213, 7, 35,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(28, 20, 33, true).add(new Interval(28, 3, 32, true)), 57, 0, 5, true,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(0, 11, 5, true).add(new Interval(0, 12, 55, true)), 1, 0, 0, true,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Two negative intervals add
		correct_cnt = (checkInterval(new Interval(2, 17, 43, false).add(new Interval(3, 3, 3, false)), 5, 20, 46, false,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(25, 14, 18, false).add(new Interval(36, 5, 59, false)), 61, 20, 17,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(17, 9, 34, false).add(new Interval(2, 21, 42, false)), 20, 7, 16,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(17, 2, 15, false).add(new Interval(25, 5, 45, false)), 42, 8, 0,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(28831, 23, 44, false).add(new Interval(881992, 12, 38, false)),
				910824, 12, 22, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// First positive, second negative intervals add
		correct_cnt = (checkInterval(new Interval(4, 23, 44, true).add(new Interval(2, 11, 25, false)), 2, 12, 19, true,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(27, 5, 18, true).add(new Interval(19, 0, 52, false)), 8, 4, 26, true,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(54, 16, 11, true).add(new Interval(53, 23, 4, false)), 0, 17, 7, true,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(7, 7, 25, true).add(new Interval(25, 18, 49, false)), 18, 11, 24,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(55, 14, 55, true).add(new Interval(922, 23, 17, false)), 867, 8, 22,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(16, 18, 37, true).add(new Interval(23, 2, 2, false)), 6, 7, 25, false,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// First negative, second positive intervals add
		correct_cnt = (checkInterval(new Interval(148, 14, 34, false).add(new Interval(273, 15, 49, true)), 125, 1, 15,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(567, 4, 51, false).add(new Interval(1052, 21, 23, true)), 485, 16, 32,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(1111112, 5, 23, false).add(new Interval(1555555, 3, 13, true)),
				444442, 21, 50, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(555, 21, 41, false).add(new Interval(97, 11, 33, true)), 458, 10, 8,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(23, 22, 15, false).add(new Interval(18, 3, 43, true)), 5, 18, 32,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(123, 6, 55, false).add(new Interval(111, 7, 59, true)), 11, 22, 56,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// INTERVAL SUBTRACT TEST
		// First positive, second negative intervals subtract
		correct_cnt = (checkInterval(new Interval(5, 4, 20, true).subtract(new Interval(3, 5, 34, false)), 8, 9, 54,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(43, 3, 20, true).subtract(new Interval(21, 12, 49, false)), 64, 16, 9,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(210, 17, 37, true).subtract(new Interval(2, 13, 58, false)), 213, 7,
				35, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(28, 20, 33, true).subtract(new Interval(28, 3, 32, false)), 57, 0, 5,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(0, 11, 5, true).subtract(new Interval(0, 12, 55, false)), 1, 0, 0,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// First negative, second positive intervals subtract
		correct_cnt = (checkInterval(new Interval(2, 17, 43, false).subtract(new Interval(3, 3, 3, true)), 5, 20, 46,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(25, 14, 18, false).subtract(new Interval(36, 5, 59, true)), 61, 20,
				17, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(17, 9, 34, false).subtract(new Interval(2, 21, 42, true)), 20, 7, 16,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(17, 2, 15, false).subtract(new Interval(25, 5, 45, true)), 42, 8, 0,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(28831, 23, 44, false).subtract(new Interval(881992, 12, 38, true)),
				910824, 12, 22, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Two positive intervals subtract
		correct_cnt = (checkInterval(new Interval(4, 23, 44, true).subtract(new Interval(2, 11, 25, true)), 2, 12, 19,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(27, 5, 18, true).subtract(new Interval(19, 0, 52, true)), 8, 4, 26,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(54, 16, 11, true).subtract(new Interval(53, 23, 4, true)), 0, 17, 7,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(7, 7, 25, true).subtract(new Interval(25, 18, 49, true)), 18, 11, 24,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(55, 14, 55, true).subtract(new Interval(922, 23, 17, true)), 867, 8,
				22, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(16, 18, 37, true).subtract(new Interval(23, 2, 2, true)), 6, 7, 25,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Two negative intervals subtract
		correct_cnt = (checkInterval(new Interval(148, 14, 34, false).subtract(new Interval(273, 15, 49, false)), 125,
				1, 15, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(567, 4, 51, false).subtract(new Interval(1052, 21, 23, false)), 485,
				16, 32, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(1111112, 5, 23, false).subtract(new Interval(1555555, 3, 13, false)),
				444442, 21, 50, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(555, 21, 41, false).subtract(new Interval(97, 11, 33, false)), 458,
				10, 8, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(23, 22, 15, false).subtract(new Interval(18, 3, 43, false)), 5, 18,
				32, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(new Interval(123, 6, 55, false).subtract(new Interval(111, 7, 59, false)), 11, 22,
				56, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// INTERVAL EQUALS TEST
		correct_cnt = (checkIntervalEquality(new Interval(23, 15, 45, true), new Interval(23, 15, 45, true), true, 1,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(18881, 1, 17, true), new Interval(18881, 1, 17, true), true,
				1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(323151, 0, 1, false), new Interval(323151, 0, 1, false), true,
				1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(2, 3, 5, true), new Interval(1, 4, 49, true), false, 1,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(2881, 23, 17, false), new Interval(2881, 23, 18, false),
				false, 1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(12, 2, 23, false), new Interval(13, 2, 23, false), false, 1,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(23, 23, 23, false), new Interval(23, 23, 23, true), false, 1,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(0, 0, 0, true), new Interval(0, 0, 0, false), true, 1,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// INTERVAL GREATER TEST
		correct_cnt = (checkIntervalEquality(new Interval(25, 15, 45, true), new Interval(23, 15, 45, true), true, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(88, 23, 9, true), new Interval(88, 4, 36, true), true, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(18881, 2, 31, true), new Interval(18881, 2, 5, true), true, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(967, 17, 18, true), new Interval(967, 17, 18, true), false, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(19956, 18, 45, true), new Interval(999999, 15, 45, true),
				false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(171, 13, 47, true), new Interval(171, 21, 45, true), false, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(881, 0, 17, true), new Interval(881, 0, 45, true), false, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		correct_cnt = (checkIntervalEquality(new Interval(25, 15, 45, false), new Interval(23, 15, 45, false), false, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(88, 23, 9, false), new Interval(88, 4, 36, false), false, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(18881, 2, 31, false), new Interval(18881, 2, 5, false), false,
				2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(967, 17, 18, false), new Interval(967, 17, 18, false), false,
				2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(19956, 18, 45, false), new Interval(999999, 15, 45, false),
				true, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(171, 13, 47, false), new Interval(171, 21, 45, false), true,
				2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(881, 0, 17, false), new Interval(881, 0, 45, false), true, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		correct_cnt = (checkIntervalEquality(new Interval(1367, 23, 17, true), new Interval(111881, 0, 45, false), true,
				2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(252, 0, 0, true), new Interval(12, 17, 12, false), true, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(12, 0, 17, false), new Interval(3333, 19, 45, true), false, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(1880991, 23, 17, false), new Interval(8411, 0, 45, true),
				false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(91, 4, 41, true), new Interval(91, 4, 41, false), true, 2,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// INTERVAL GREATER TEST
		correct_cnt = (checkIntervalEquality(new Interval(25, 15, 45, true), new Interval(23, 15, 45, true), false, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(88, 23, 9, true), new Interval(88, 4, 36, true), false, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(18881, 2, 31, true), new Interval(18881, 2, 5, true), false,
				3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(967, 17, 18, true), new Interval(967, 17, 18, true), false, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(19956, 18, 45, true), new Interval(999999, 15, 45, true),
				true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(171, 13, 47, true), new Interval(171, 21, 45, true), true, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(881, 0, 17, true), new Interval(881, 0, 45, true), true, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		correct_cnt = (checkIntervalEquality(new Interval(25, 15, 45, false), new Interval(23, 15, 45, false), true, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(88, 23, 9, false), new Interval(88, 4, 36, false), true, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(18881, 2, 31, false), new Interval(18881, 2, 5, false), true,
				3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(967, 17, 18, false), new Interval(967, 17, 18, false), false,
				3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(19956, 18, 45, false), new Interval(999999, 15, 45, false),
				false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(171, 13, 47, false), new Interval(171, 21, 45, false), false,
				3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(881, 0, 17, false), new Interval(881, 0, 45, false), false, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		correct_cnt = (checkIntervalEquality(new Interval(1367, 23, 17, true), new Interval(111881, 0, 45, false),
				false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(252, 0, 0, true), new Interval(12, 17, 12, false), false, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(12, 0, 17, false), new Interval(3333, 19, 45, true), true, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(1880991, 23, 17, false), new Interval(8411, 0, 45, true),
				true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkIntervalEquality(new Interval(91, 4, 41, true), new Interval(91, 4, 41, false), false, 3,
				correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// TIMESTAMP ADD TEST
		// Positive interval test
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 12, 8, 15, zero).add(new Interval(7, 6, 27, true)), 2022,
				3, 19, 14, 42, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 12, 16, 37, zero).add(new Interval(15, 3, 56, true)), 2022,
				3, 27, 20, 33, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 12, 2, 21, zero).add(new Interval(3, 23, 42, true)), 2022,
				3, 16, 2, 3, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 12, 13, 17, zero).add(new Interval(19, 10, 55, true)),
				2022, 4, 1, 0, 12, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 2, 12, 23, 5, zero).add(new Interval(37, 10, 2, true)), 2006,
				3, 22, 9, 7, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 6, 3, 15, 25, zero).add(new Interval(56, 2, 48, true)), 2006,
				7, 29, 18, 13, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 12, 20, 20, 54, zero).add(new Interval(15, 4, 1, true)), 2007,
				1, 5, 0, 55, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(1981, 5, 3, 17, 17, zero).add(new Interval(241, 8, 23, true)), 1981,
				12, 31, 1, 40, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(1781, 2, 20, 13, 29, zero).add(new Interval(8761, 18, 41, true)),
				1805, 2, 17, 8, 10, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(1693, 8, 11, 6, 45, zero).add(new Interval(6666666, 3, 23, true)),
				19946, 4, 29, 10, 8, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Negative interval test
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 19, 14, 42, zero).add(new Interval(7, 6, 27, false)), 2022,
				3, 12, 8, 15, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 27, 20, 33, zero).add(new Interval(15, 3, 56, false)),
				2022, 3, 12, 16, 37, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 16, 2, 3, zero).add(new Interval(3, 23, 42, false)), 2022,
				3, 12, 2, 21, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 4, 1, 0, 12, zero).add(new Interval(19, 10, 55, false)), 2022,
				3, 12, 13, 17, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 3, 22, 9, 7, zero).add(new Interval(37, 10, 2, false)), 2006,
				2, 12, 23, 5, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 7, 29, 18, 13, zero).add(new Interval(56, 2, 48, false)),
				2006, 6, 3, 15, 25, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2007, 1, 5, 0, 55, zero).add(new Interval(15, 4, 1, false)), 2006,
				12, 20, 20, 54, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(1981, 12, 31, 1, 40, zero).add(new Interval(241, 8, 23, false)),
				1981, 5, 3, 17, 17, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(1805, 2, 17, 8, 10, zero).add(new Interval(8761, 18, 41, false)),
				1781, 2, 20, 13, 29, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(19946, 4, 29, 10, 8, zero).add(new Interval(6666666, 3, 23, false)),
				1693, 8, 11, 6, 45, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// TIMESTAMP SUBTRACT TEST
		// Negative interval test
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 12, 8, 15, zero).subtract(new Interval(7, 6, 27, false)),
				2022, 3, 19, 14, 42, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 12, 16, 37, zero).subtract(new Interval(15, 3, 56, false)),
				2022, 3, 27, 20, 33, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 12, 2, 21, zero).subtract(new Interval(3, 23, 42, false)),
				2022, 3, 16, 2, 3, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(
				new Timestamp(2022, 3, 12, 13, 17, zero).subtract(new Interval(19, 10, 55, false)), 2022, 4, 1, 0, 12,
				zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 2, 12, 23, 5, zero).subtract(new Interval(37, 10, 2, false)),
				2006, 3, 22, 9, 7, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 6, 3, 15, 25, zero).subtract(new Interval(56, 2, 48, false)),
				2006, 7, 29, 18, 13, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 12, 20, 20, 54, zero).subtract(new Interval(15, 4, 1, false)),
				2007, 1, 5, 0, 55, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(1981, 5, 3, 17, 17, zero).subtract(new Interval(241, 8, 23, false)),
				1981, 12, 31, 1, 40, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(
				new Timestamp(1781, 2, 20, 13, 29, zero).subtract(new Interval(8761, 18, 41, false)), 1805, 2, 17, 8,
				10, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(
				new Timestamp(1693, 8, 11, 6, 45, zero).subtract(new Interval(6666666, 3, 23, false)), 19946, 4, 29, 10,
				8, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Positive interval test
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 19, 14, 42, zero).subtract(new Interval(7, 6, 27, true)),
				2022, 3, 12, 8, 15, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 27, 20, 33, zero).subtract(new Interval(15, 3, 56, true)),
				2022, 3, 12, 16, 37, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 3, 16, 2, 3, zero).subtract(new Interval(3, 23, 42, true)),
				2022, 3, 12, 2, 21, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2022, 4, 1, 0, 12, zero).subtract(new Interval(19, 10, 55, true)),
				2022, 3, 12, 13, 17, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 3, 22, 9, 7, zero).subtract(new Interval(37, 10, 2, true)),
				2006, 2, 12, 23, 5, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2006, 7, 29, 18, 13, zero).subtract(new Interval(56, 2, 48, true)),
				2006, 6, 3, 15, 25, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(2007, 1, 5, 0, 55, zero).subtract(new Interval(15, 4, 1, true)),
				2006, 12, 20, 20, 54, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(new Timestamp(1981, 12, 31, 1, 40, zero).subtract(new Interval(241, 8, 23, true)),
				1981, 5, 3, 17, 17, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(
				new Timestamp(1805, 2, 17, 8, 10, zero).subtract(new Interval(8761, 18, 41, true)), 1781, 2, 20, 13, 29,
				zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkTimestamp(
				new Timestamp(19946, 4, 29, 10, 8, zero).subtract(new Interval(6666666, 3, 23, true)), 1693, 8, 11, 6,
				45, zero, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// TIMESTAMP DIFFERENCE TEST
		// Zero offset difference - Rezultat true
		correct_cnt = (checkInterval(
				new Timestamp(2022, 3, 19, 14, 42, zero).subtract(new Timestamp(2022, 3, 12, 8, 15, zero)), 7, 6, 27,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2022, 3, 27, 20, 33, zero).subtract(new Timestamp(2022, 3, 12, 16, 37, zero)), 15, 3, 56,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2022, 3, 16, 2, 3, zero).subtract(new Timestamp(2022, 3, 12, 2, 21, zero)), 3, 23, 42,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2022, 4, 1, 0, 12, zero).subtract(new Timestamp(2022, 3, 12, 13, 17, zero)), 19, 10, 55,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2006, 3, 22, 9, 7, zero).subtract(new Timestamp(2006, 2, 12, 23, 5, zero)), 37, 10, 2,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2006, 7, 29, 18, 13, zero).subtract(new Timestamp(2006, 6, 3, 15, 25, zero)), 56, 2, 48,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2007, 1, 5, 0, 55, zero).subtract(new Timestamp(2006, 12, 20, 20, 54, zero)), 15, 4, 1,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1981, 12, 31, 1, 40, zero).subtract(new Timestamp(1981, 5, 3, 17, 17, zero)), 241, 8, 23,
				true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1805, 2, 17, 8, 10, zero).subtract(new Timestamp(1781, 2, 20, 13, 29, zero)), 8761, 18,
				41, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(19946, 4, 29, 10, 8, zero).subtract(new Timestamp(1693, 8, 11, 6, 45, zero)), 6666666, 3,
				23, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Zero offset difference - Rezultat false
		correct_cnt = (checkInterval(
				new Timestamp(2022, 3, 12, 8, 15, zero).subtract(new Timestamp(2022, 3, 19, 14, 42, zero)), 7, 6, 27,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2022, 3, 12, 16, 37, zero).subtract(new Timestamp(2022, 3, 27, 20, 33, zero)), 15, 3, 56,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2022, 3, 12, 2, 21, zero).subtract(new Timestamp(2022, 3, 16, 2, 3, zero)), 3, 23, 42,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2022, 3, 12, 13, 17, zero).subtract(new Timestamp(2022, 4, 1, 0, 12, zero)), 19, 10, 55,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2006, 2, 12, 23, 5, zero).subtract(new Timestamp(2006, 3, 22, 9, 7, zero)), 37, 10, 2,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2006, 6, 3, 15, 25, zero).subtract(new Timestamp(2006, 7, 29, 18, 13, zero)), 56, 2, 48,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2006, 12, 20, 20, 54, zero).subtract(new Timestamp(2007, 1, 5, 0, 55, zero)), 15, 4, 1,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1981, 5, 3, 17, 17, zero).subtract(new Timestamp(1981, 12, 31, 1, 40, zero)), 241, 8, 23,
				false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1781, 2, 20, 13, 29, zero).subtract(new Timestamp(1805, 2, 17, 8, 10, zero)), 8761, 18,
				41, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1693, 8, 11, 6, 45, zero).subtract(new Timestamp(19946, 4, 29, 10, 8, zero)), 6666666, 3,
				23, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Full hour "random" offset applied
		correct_cnt = (checkInterval(
				new Timestamp(1841, 11, 27, 15, 45, new Interval(0, 9, 0, true))
						.subtract(new Timestamp(1844, 2, 28, 15, 32, new Interval(0, 2, 0, true))),
				823, 6, 47, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1912, 10, 9, 3, 4, new Interval(0, 4, 0, true))
						.subtract(new Timestamp(1912, 7, 9, 9, 32, new Interval(0, 11, 0, false))),
				91, 2, 32, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2652, 1, 14, 14, 32, new Interval(0, 6, 0, false))
						.subtract(new Timestamp(2676, 9, 7, 1, 31, new Interval(0, 7, 0, true))),
				9001, 21, 59, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(3991, 11, 2, 23, 0, new Interval(0, 11, 0, false))
						.subtract(new Timestamp(3991, 10, 18, 23, 21, new Interval(0, 3, 0, false))),
				15, 7, 39, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1997, 7, 27, 1, 10, new Interval(0, 8, 0, true))
						.subtract(new Timestamp(2022, 11, 16, 21, 49, new Interval(0, 1, 0, false))),
				9244, 5, 39, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// Half hour "random" offset applied
		correct_cnt = (checkInterval(
				new Timestamp(1841, 11, 27, 16, 15, new Interval(0, 9, 30, true))
						.subtract(new Timestamp(1844, 2, 28, 16, 2, new Interval(0, 2, 30, true))),
				823, 6, 47, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1912, 10, 9, 3, 34, new Interval(0, 4, 30, true))
						.subtract(new Timestamp(1912, 7, 9, 9, 2, new Interval(0, 11, 30, false))),
				91, 2, 32, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(2652, 1, 14, 14, 2, new Interval(0, 6, 30, false))
						.subtract(new Timestamp(2676, 9, 7, 2, 1, new Interval(0, 7, 30, true))),
				9001, 21, 59, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(3991, 11, 2, 22, 30, new Interval(0, 11, 30, false))
						.subtract(new Timestamp(3991, 10, 18, 22, 51, new Interval(0, 3, 30, false))),
				15, 7, 39, true, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;
		correct_cnt = (checkInterval(
				new Timestamp(1997, 7, 27, 1, 40, new Interval(0, 8, 30, true))
						.subtract(new Timestamp(2022, 11, 16, 21, 19, new Interval(0, 1, 30, false))),
				9244, 5, 39, false, correct_cnt, ++total_cnt)) ? (correct_cnt + 1) : correct_cnt;

		// TIMESTAMP EQUALS TEST
		correct_cnt = (checkTimestampEquality(new Timestamp(1693, 8, 11, 6, 45, zero),
				new Timestamp(1693, 8, 11, 6, 45, zero), true, 1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1881, 7, 4, 1, 18, new Interval(0, 6, 0, true)),
				new Timestamp(1881, 7, 3, 15, 18, new Interval(0, 4, 0, false)), true, 1, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1994, 1, 31, 2, 18, zero),
				new Timestamp(1993, 1, 31, 2, 18, zero), false, 1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1993, 2, 31, 2, 18, zero),
				new Timestamp(1993, 1, 31, 2, 18, zero), false, 1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1993, 1, 30, 2, 18, zero),
				new Timestamp(1993, 1, 31, 2, 18, zero), false, 1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1993, 1, 31, 1, 18, zero),
				new Timestamp(1993, 1, 31, 2, 18, zero), false, 1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1993, 1, 31, 2, 17, zero),
				new Timestamp(1993, 1, 31, 2, 18, zero), false, 1, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1993, 1, 31, 2, 18, new Interval(0, 1, 0, true)),
				new Timestamp(1993, 1, 31, 2, 18, new Interval(0, 1, 0, false)), false, 1, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1993, 1, 31, 2, 18, new Interval(0, 0, 0, true)),
				new Timestamp(1993, 1, 31, 2, 18, new Interval(0, 0, 0, false)), true, 1, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 27, 12, 23, new Interval(0, 11, 0, false)),
				new Timestamp(2913, 2, 28, 10, 23, new Interval(0, 11, 0, true)), true, 1, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;

		// TIMESTAMP GREATER TEST
		correct_cnt = (checkTimestampEquality(new Timestamp(2914, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), true, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1921, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 3, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), true, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 3, 28, 12, 23, zero), false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 27, 12, 23, zero), true, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 27, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 13, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), true, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 13, 23, zero), false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 24, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), true, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 24, zero), false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1878, 7, 14, 23, 47, new Interval(0, 5, 0, false)),
				new Timestamp(1878, 7, 15, 3, 47, zero), true, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1878, 7, 15, 6, 24, new Interval(0, 11, 0, false)),
				new Timestamp(1878, 7, 16, 0, 23, new Interval(0, 7, 0, true)), true, 2, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2001, 9, 11, 12, 8, new Interval(0, 3, 0, true)),
				new Timestamp(2001, 9, 11, 6, 8, new Interval(0, 3, 0, false)), false, 2, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2023, 1, 1, 5, 30, new Interval(0, 6, 0, true)),
				new Timestamp(2022, 12, 31, 23, 31, zero), false, 2, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;

		// TIMESTAMP LESSER TEST
		correct_cnt = (checkTimestampEquality(new Timestamp(2914, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1921, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 3, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 3, 28, 12, 23, zero), true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 27, 12, 23, zero), false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 27, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 13, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 13, 23, zero), true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 24, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 24, zero), true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2913, 2, 28, 12, 23, zero),
				new Timestamp(2913, 2, 28, 12, 23, zero), false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1878, 7, 14, 23, 47, new Interval(0, 5, 0, false)),
				new Timestamp(1878, 7, 15, 3, 47, zero), false, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(1878, 7, 15, 6, 24, new Interval(0, 11, 0, false)),
				new Timestamp(1878, 7, 16, 0, 23, new Interval(0, 7, 0, true)), false, 3, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2001, 9, 11, 12, 8, new Interval(0, 3, 0, true)),
				new Timestamp(2001, 9, 11, 6, 8, new Interval(0, 3, 0, false)), false, 3, correct_cnt, ++total_cnt))
						? (correct_cnt + 1)
						: correct_cnt;
		correct_cnt = (checkTimestampEquality(new Timestamp(2023, 1, 1, 5, 30, new Interval(0, 6, 0, true)),
				new Timestamp(2022, 12, 31, 23, 31, zero), true, 3, correct_cnt, ++total_cnt)) ? (correct_cnt + 1)
						: correct_cnt;

		System.out.println("Total score:" + correct_cnt + "/" + total_cnt);
		System.out.println(
				String.format("Procenat uspesnosti: %.2f", (double) correct_cnt * 100 / (double) total_cnt) + "%");

	}

	public static void main(String[] args) {
		test();
	}

}
