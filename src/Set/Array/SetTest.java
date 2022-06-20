package Set.Array;

import Set.Array.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {

    Set set;
    Set set1;
    Set set2;

    int minbit = 0b10000000000000000000000000000000; //0 слева
    int maxbit = 0b00000000000000000000000000000001; //31 слева
    int sixteenthbit = 0b00000000000000001000000000000000; //16 слева
    int fifteenthbit = 0b00000000000000010000000000000000; //15 слева


    SetTest() {
        set = new Set(-64, 63);
    }

    @Test
    void clean() {
        set = new Set(-30, 30);
        set.Insert(0);
        set.Insert(15);
        set.Insert(-1);
        int[] test = new int[]{maxbit, minbit | fifteenthbit};
        assertArrayEquals(test, set.set);
        test[0] = 0;
        test[1] = 0;
        set.MakeNull();
        assertArrayEquals(test, set.set);
    }

    @Test
    void intersection() {
        set1 = new Set(-64, -32);
        set2 = new Set(0, 32);
        assertNull(set1.Intersection(set2));
        set1 = new Set(-128, 16);
        set2 = new Set(0, 32);
        set1.Insert(-128);
        set1.Insert(-32);
        set1.Insert(15);
        set2.Insert(0);
        set2.Insert(15);
        set2.Insert(32);
        int[] test = new int[1];
        test[0] = fifteenthbit;
        assertArrayEquals(test, set1.Intersection(set2).set);
        assertArrayEquals(test, set2.Intersection(set1).set);
        set2 = new Set(-32, 31);
        set2.Insert(-32);
        set2.Insert(-1);
        set2.Insert(16);
        set1.Insert(-32);
        set1.Insert(-1);
        set1.Insert(16);
        int[] test2 = new int[2];
        test2[0] = minbit | maxbit;
        test2[1] = sixteenthbit;
        assertArrayEquals(test2, set1.Intersection(set2).set);
        assertArrayEquals(test2, set2.Intersection(set1).set);
    }

    @Test
    void intersectionpositive() {
        set1 = new Set(0, 48);
        set2 = new Set(46, 63);
        set1.Insert(0);
        set1.Insert(32);
        set1.Insert(47);
        set2.Insert(47);
        set2.Insert(63);
        int[] test = new int[1];
        test[0] = fifteenthbit;
        assertArrayEquals(test, set1.Intersection(set2).set);
        assertArrayEquals(test, set2.Intersection(set1).set);
    }

    @Test
    void intersectionnegative() {
        set1 = new Set(-60, -32);
        set2 = new Set(0, 32);
        assertNull(set1.Intersection(set2));
        set1 = new Set(-128, 16);
        set2 = new Set(0, 32);
        set1.Insert(-128);
        set1.Insert(-32);
        set1.Insert(15);
        set2.Insert(0);
        set2.Insert(15);
        set2.Insert(32);
        int[] test = new int[1];
        test[0] = fifteenthbit;
        assertArrayEquals(test, set1.Intersection(set2).set);
        assertArrayEquals(test, set2.Intersection(set1).set);
        set2 = new Set(-32, 31);
        set2.Insert(-32);
        set2.Insert(-1);
        set2.Insert(16);
        set1.Insert(-32);
        set1.Insert(-1);
        set1.Insert(16);
        int[] test2 = new int[2];
        test2[0] = minbit | maxbit;
        test2[1] = sixteenthbit;
        assertArrayEquals(test2, set1.Intersection(set2).set);
        assertArrayEquals(test2, set2.Intersection(set1).set);
    }

    @Test
    void union() {
        set1 = new Set(32, 60);
        set2 = new Set(-128, -75);
        set1.Insert(32);
        set2.Insert(-128);
        int[] test2 = new int[6];
        test2[0] = minbit;
        test2[5] = minbit;
        assertArrayEquals(test2, set1.Union(set2).set);
        assertArrayEquals(test2, set2.Union(set1).set);
    }

    @Test
    void difference() {
        set1 = new Set(-4, -1);
        set2 = new Set(1, 5);
        assertNull(set1.Difference(set2));

        //set1<set2
        set1 = new Set(0, 31);
        set2 = new Set(-32, 31);

        set1.Insert(0);
        set1.Insert(15);
        set1.Insert(31);
        set2.Insert(15);
        set2.Insert(16);
        int[] test = new int[]{minbit | maxbit};
        assertArrayEquals(test, set1.Difference(set2).set);

        //set1>set2
        set1 = new Set(-120, 30);
        set2 = new Set(-60, -33);
        set1.Insert(-96);
        set1.Insert(-64);
        set1.Insert(-48);
        set1.Insert(-49);
        set1.Insert(-33);
        set1.Insert(0);
        set2.Insert(-49);
        set2.Insert(-33);

        test = new int[]{0, minbit, minbit | sixteenthbit, 0, minbit};
        assertArrayEquals(test, set1.Difference(set2).set);
    }

    @Test
    void insert() {
        Set set = new Set(-64, 48);
        int[] test = new int[4];
        test[0] = minbit; //-64
        test[1] = minbit | maxbit; //-32 -1
        test[2] = minbit | maxbit | fifteenthbit; //0 31 15
        test[3] = minbit | fifteenthbit; // 32 47
        set.Insert(-64);
        set.Insert(-32);
        set.Insert(-1);
        set.Insert(0);
        set.Insert(31);
        set.Insert(15);
        set.Insert(32);
        set.Insert(47);
        assertArrayEquals(test, set.set);
        test[2] |= sixteenthbit;
        set.Insert(16);
        assertArrayEquals(test, set.set);
        clean();
    }

    @Test
    void delete() {
        int[] test = new int[4];
        test[0] = minbit; //-64
        test[1] = minbit | maxbit; //-32 -1
        test[2] = minbit | maxbit | sixteenthbit | fifteenthbit; //0 31 16 15
        test[3] = minbit | fifteenthbit; // 32 47
        set.Insert(-64);
        set.Insert(-32);
        set.Insert(-1);
        set.Insert(0);
        set.Insert(31);
        set.Insert(15);
        set.Insert(16);
        set.Insert(32);
        set.Insert(47);
        assertArrayEquals(test, set.set);
        set.Delete(-1);
        set.Delete(15);
        set.Delete(0);
        test[1] &= ~maxbit;
        test[2] &= ~(minbit | fifteenthbit);
        assertArrayEquals(test, set.set);
    }

    @Test
    void testmin() {
        set = new Set(-120, 120);
        set.Insert(-64);
        set.Insert(-32);
        set.Insert(-1);
        set.Insert(0);
        set.Insert(31);
        set.Insert(15);
        set.Insert(16);
        assertEquals(-64, set.Min());
        set.Delete(-64);
        set.Delete(-32);
        assertEquals(-1, set.Min());
        set.Delete(-1);
        assertEquals(0, set.Min());
    }

    @Test
    void max() {
        set = new Set(-120, 120);
        set.Insert(-64);
        set.Insert(-32);
        set.Insert(-1);
        set.Insert(0);
        set.Insert(31);
        set.Insert(32);
        set.Insert(15);
        set.Insert(47);
        set.Insert(63);
        assertEquals(63, set.Max());
        set.Delete(63);
        assertEquals(47, set.Max());
        set.Delete(47);
        assertEquals(32, set.Max());
        set.Delete(32);
        assertEquals(31, set.Max());
        set.Delete(31);
        assertEquals(15, set.Max());
        set.Delete(15);
        assertEquals(0, set.Max());
    }

    @Test
    void makeNull() {
        set = new Set(-64, 63);
        set.Insert(-64);
        set.Insert(-32);
        set.Insert(-1);
        set.Insert(0);
        set.Insert(31);
        set.Insert(32);
        set.Insert(15);
        set.Insert(47);
        set.Insert(63);
        set.MakeNull();
        int[] test = new int[4];
        assertArrayEquals(test, set.set);
    }

    @Test
    void member() {
        set = new Set(-120, 120);
        set.Insert(-64);
        set.Insert(-32);
        set.Insert(-1);
        set.Insert(0);
        set.Insert(31);
        set.Insert(32);
        set.Insert(15);
        set.Insert(63);
        assertTrue(set.Member(-64));
        assertTrue(set.Member(-32));
        assertTrue(set.Member(-1));
        assertTrue(set.Member(0));
        assertTrue(set.Member(31));
        assertTrue(set.Member(32));
        assertTrue(set.Member(15));
        assertTrue(set.Member(63));
        assertFalse(set.Member(-20));
        assertFalse(set.Member(16));
        assertFalse(set.Member(1));
    }

    @Test
    void isIntersection() {
        set1 = new Set(-64, -32);
        set2 = new Set(32, 64);
        assertFalse(set1.isIntersection(set2));
        set1 = new Set(-32, 32);
        set2 = new Set(0, 31);
        assertFalse(set1.isIntersection(set2));
        assertFalse(set2.isIntersection(set1));
        set1.Insert(31);
        set2.Insert(31);
        assertTrue(set1.isIntersection(set2));
        assertTrue(set2.isIntersection(set1));
    }

    @Test
    void find() {
        set1 = new Set(-32, 0);
        set2 = new Set(0, 31);
        set1.Insert(0);
        set1.Insert(-32);
        set2.Insert(0);
        set2.Insert(15);
        set2.Insert(16);
        assertEquals(set1, set1.Find(set2, 0));
        assertEquals(set1, set1.Find(set2, -32));
        assertEquals(set2, set1.Find(set2, 15));
        assertEquals(set2, set1.Find(set2, 16));
        assertNull(set1.Find(set2, 5));
        assertNull(set1.Find(set2, -100));
    }

    @Test
    void equal() {
        //общая часть set1 левее set2
        set1 = new Set(-32, 31);
        set2 = new Set(0, 63);
        //равные с одинм элементом
        set1.Insert(0);
        set2.Insert(0);
        assertTrue(set1.Equal(set2));
        //неравные из за одного смежного элемента
        set1.Insert(15);
        assertFalse(set1.Equal(set2));
        set1.Delete(15);
        assertTrue(set1.Equal(set2));
        //неравные из за одного несмежного элемента
        set1.Insert(-1);
        assertFalse(set1.Equal(set2));
        set1.Delete(-1);
        assertTrue(set1.Equal(set2));

        //ничего общего
        set1 = new Set(-32, -1);
        set2 = new Set(0, 31);
        //оба пустые
        assertTrue(set1.Equal(set2));
        //неравный смежный элемент
        set1.Insert(-1);
        assertFalse(set1.Equal(set2));

        //общая часть set1 вмещает в себя set2
        set1 = new Set(-32, 63);
        set2 = new Set(0, 31);
        //пустые
        assertTrue(set1.Equal(set2));
        //неравный смежный элемент
        set1.Insert(-1);
        assertFalse(set1.Equal(set2));
        set1.Delete(-1);
        assertTrue(set1.Equal(set2));
        //один общий элемент
        set1.Insert(31);
        set2.Insert(31);
        assertTrue(set1.Equal(set2));
        //один доп несовпадающий элемент
        set1.Insert(63);
        assertFalse(set1.Equal(set2));
    }

    @Test
    void merge() {
        set1 = new Set(-32, 31);
        set2 = new Set(-64, -33);
        set1.Insert(0);
        set1.Insert(31);
        set2.Insert(-64);
        set2.Insert(-33);
        int[] test = new int[]{minbit | maxbit, 0, minbit | maxbit};
        Set set3 = set1.Merge(set2);
        assertArrayEquals(test, set3.set);
        set1 = new Set(32, 63);
        set2 = new Set(-32, 31);
        set1.Insert(32);
        set1.Insert(63);
        set2.Insert(-32);
        set2.Insert(-1);
        test = new int[]{minbit | maxbit, 0, minbit | maxbit};
        set3 = set1.Merge(set2);
        assertArrayEquals(test, set3.set);
    }

    @Test
    void assign(){
        set1 = new Set(0, 63);
        set1.Insert(0);
        set1.Insert(32);
        int[] test = new int[]{minbit, minbit};
        Set set3 = new Set(0,32).Assign(set1);
        assertArrayEquals(test, set3.set);
    }
}