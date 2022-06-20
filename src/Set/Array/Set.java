package Set.Array;

import java.util.NoSuchElementException;

public class Set implements ADT_Set {


    int[] set;
    private final int min;
    private final int max;
    // Относительное местоположение элемента, где содержится 0
    private final int zeroIndex;

    /**
     * @param min минимально возможный элемент
     * @param max максимально возможный элемент
     */
    public Set(int min, int max) {
        this.min = min;
        this.max = max;
        int size;
        if (min < 0) {
            size = max / 32 - (min + 1) / 32 + 2; //min=-32 отрицательный min/32=1
            zeroIndex = -((min + 1) / 32) + 1; //min=-32
        } else {
            size = max / 32 - min / 32 + 1;
            zeroIndex = -(min / 32);
        }
        set = new int[size];
    }

    @Override
    public Set Intersection(Set other) {
        if (this.min > other.max || this.max < other.min)
            return null;
        Set result = new Set(Math.max(this.min, other.min), Math.min(this.max, other.max));

//        int shift = Math.abs(this.zeroIndex - other.zeroIndex);
//        if (this.zeroIndex < other.zeroIndex) {
//            for (int i = 0; i < result.set.length; i++) {
//                result.set[i] = this.set[i] & other.set[i + shift];
//            }
//        } else {
//            for (int i = 0; i < result.set.length; i++) {
//                result.set[i] = this.set[i + shift] & other.set[i];
//            }
//        }
        int shiftA = Math.abs(this.zeroIndex - result.zeroIndex);
        for (int i = 0; i < result.set.length; i++) {
            result.set[i] = this.set[i + shiftA];
        }
        int shiftB = Math.abs(other.zeroIndex - result.zeroIndex);
        for (int i = 0; i < result.set.length; i++) {
            result.set[i] &= other.set[i + shiftB];
        }
        return result;
    }

    @Override
    public Set Union(Set other) {
        Set result = new Set(Math.min(this.min, other.min), Math.max(this.max, other.max));

        int shift1 = Math.abs(this.zeroIndex - result.zeroIndex);
        for (int i = 0; i < this.set.length; i++) {
            result.set[i + shift1] |= this.set[i];
        }
        int shift2 = Math.abs(other.zeroIndex - result.zeroIndex);
        for (int i = 0; i < other.set.length; i++) {
            result.set[i + shift2] |= other.set[i];
        }
        return result;
    }

    @Override
    public Set Difference(Set other) {
        if (this.min > other.max || this.max < other.min)
            return null;
        Set result = new Set(this.min, this.max);
        result.copyFrom(this);

        int shift = Math.abs(this.zeroIndex - other.zeroIndex);
        int i;
//        if (this.zeroIndex < other.zeroIndex) {
//            for (i = 0; i < result.set.length && i < other.set.length; i++) {
//                result.set[i] = this.set[i] & ~other.set[i + shift];
//            }
//        } else {
//            for (i = 0; i < result.set.length && i < other.set.length; i++) {
//                result.set[i + shift] = this.set[i + shift] & ~other.set[i];
//            }
//        }
        if (this.zeroIndex >= other.zeroIndex) {
            for (i = 0; i < result.set.length && i < other.set.length; i++) {
                result.set[i + shift] &= ~other.set[i];
            }
        } else {
            for (i = 0; i < result.set.length && i < other.set.length; i++) {
                result.set[i] &= ~other.set[i + shift];
            }
        }
        return result;
    }

    private void copyFrom(Set set) {
        for (int i = 0; i < this.set.length; i++) {
            this.set[i] = set.set[i];
        }
    }

    @Override
    public void Insert(int x) {
        if (!isInRange(x))
            return;
        int pos = getPos(x);
        set[pos + zeroIndex] |= indexOf(x);
    }

    private int getPos(int x) {
        int pos;
        if (x < 0)
            pos = (x + 1) / 32 - 1;
        else
            pos = x / 32;
        return pos;
    }

    @Override
    public void Delete(int x) {
        int pos = getPos(x);
        set[pos + zeroIndex] &= ~indexOf(x);
    }

    @Override
    public int Min() {
        int i, j;
        for (i = 0; i < set.length; i++)
            if (set[i] != 0)
                break;
        if (i == set.length)
            throw new NoSuchElementException("Нет минимума");
        int x = Integer.MIN_VALUE;
        for (j = 0; j < 31; j++) {
            if ((set[i] & x) != 0)
                break;
            x >>>= 1;
        }
        if (j == 32)
            throw new NoSuchElementException("Нет минимума");
        return 32 * i + j + zeroIndex * (-32);
    }

    @Override
    public int Max() {
        int i, j;
        for (i = set.length - 1; i >= 0; i--)
            if (set[i] != 0)
                break;
        if (i == -1)
            throw new NoSuchElementException("Нет максимума");
        int x = 1;
        for (j = 31; j >= 0; j--) {
            if ((set[i] & x) != 0)
                break;
            x <<= 1;
        }
        if (j == -1)
            throw new NoSuchElementException("Нет максимума");
        return 32 * i + j + zeroIndex * (-32);
    }

    @Override
    public void MakeNull() {
        for (int i = 0; i < set.length; i++)
            set[i] = 0;
    }

    @Override
    public boolean Member(int x) {
        int pos = getPos(x);
        int b = set[pos + zeroIndex];
        return (b & indexOf(x)) != 0;
    }


    @Override
    public Set Find(Set s, int x) {
        int pos = getPos(x);
        if (isInRange(x) && (set[pos + zeroIndex] & indexOf(x)) != 0)
            return this;
        else if (s.isInRange(x) && (s.set[pos + s.zeroIndex] & indexOf(x)) != 0)
            return s;
        return null;
    }

    private boolean isInRange(int x) {
        return x >= min && x <= max;
    }

    @Override
    public Set Merge(Set other) {
        Set result = new Set(Math.min(this.min, other.min), Math.max(this.max, other.max));

        int shift1 = Math.abs(this.zeroIndex - result.zeroIndex);
        for (int i = 0; i < this.set.length; i++) {
            result.set[i + shift1] |= this.set[i];
        }
        int shift2 = Math.abs(other.zeroIndex - result.zeroIndex);
        for (int i = 0; i < other.set.length; i++) {
            result.set[i + shift2] |= other.set[i];
        }
        return result;
    }

    public boolean isIntersection(Set other) {
        if (this.min > other.max || this.max < other.min)
            return false;

        int len = Math.min(this.max, other.max) / 32 - Math.max(this.min, other.min) / 32 + 1;
        int shift = Math.abs(this.zeroIndex - other.zeroIndex);

        if (this.zeroIndex < other.zeroIndex) {
            for (int i = 0; i < len; i++)
                if ((set[i] & other.set[i + shift]) != 0)
                    return true;
        } else {
            for (int i = 0; i < len; i++)
                if ((set[i + shift] & other.set[i]) != 0)
                    return true;
        }

        return false;
    }


    @Override
    public boolean Equal(Set other) {
        int shift = Math.abs(this.zeroIndex - other.zeroIndex);
        int i;
        if (this.zeroIndex > other.zeroIndex) {
            for (i = 0; i < shift; i++)
                if (this.set[i] != 0) return false;
            for (i = 0; i + shift < this.set.length && i < other.set.length; i++)
                if (this.set[i + shift] != other.set[i]) return false;
        } else {
            for (i = 0; i < shift; i++)
                if (other.set[i] != 0) return false;
            for (i = 0; i < this.set.length && i + shift < other.set.length; i++)
                if (this.set[i] != other.set[i + shift]) return false;
        }
        i++;
        if (this.max > other.max) {
            for (; i < this.set.length; i++)
                if (this.set[i] != 0) return false;
        } else {
            for (; i < other.set.length; i++)
                if (other.set[i] != 0) return false;
        }

        return true;
    }

    @Override
    public Set Assign(Set other) {
        Set newSet = new Set(other.min, other.max);
        newSet.copyFrom(other);
        return newSet;
    }

    private int indexOf(int x) {
        if (x >= 0)
            return 1 << (31 - x % 32);
        else
            return 1 << -(x + 1) % 32;
//            return 1 << (31 - x) % 32;
    }
}
