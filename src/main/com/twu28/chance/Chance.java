package com.twu28.chance;

/**
 * Created with IntelliJ IDEA.
 * User: wschlegel
 * Date: 01/08/2012
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class Chance {

    private static final Integer ONE = 100;
    private Integer value;

    public Chance(Integer value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("value must not be less than 0 or larger than 1");
        }

        this.value = value;
    }

    public Chance not() {
        return new Chance(ONE - value);
    }

    public Chance and(Chance other) {
        return new Chance(this.value * other.value);
    }

    public Chance or(Chance other) {
        return new Chance(this.value + other.value - this.value * other.value) ;
    }

    public Chance complexOr(Chance other) {
        return (new Chance(this.value + other.value)).minus(and(other)) ;
    }

    public Chance deMorgansOr(Chance other) {
        // P(A||B) = !(!P(A) && !P(B))
        return (this.not().and(other.not())).not();
    }

    /*
     * Observations
     *
     * We first started with the 'or' method and found redundant code in the methods 'or' and 'and'.
     * The 'complexOr' method avoids that redundancy by (re-)using 'and'. However, now we need the 'minus' method to
     * calculate the result.
     *
     * The 'minus' method could be private so that it can never be called outside the context of this class.
     * However, using 'private' as visibility implies that the 'minus' method cannot be unit-tested in isolation.
     * Instead, it is tested implicitly by tests for the 'complexOr' method.
     *
     * Testing 'minus' via 'complexOr' never produces un-happy day scenarios such as 40-70 = -30. In order to
     * test against these scenarios the visibility could be opened to default visibility.
     *
     * This breaks encapsulation of the Chance class which may (or may not) be acceptable by ensuring that the
     * 'minus' method does not change the state of the object called.
     *
     */
    Chance minus(Chance other) {
        return new Chance(this.value - other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chance chance = (Chance) o;

        if (value != null ? !value.equals(chance.value) : chance.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
