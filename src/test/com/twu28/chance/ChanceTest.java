package com.twu28.chance;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: wschlegel
 * Date: 01/08/2012
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class ChanceTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativeValue() {
        new Chance(-4);
    }

    @Test
    public void shouldDeductChances() {
        assertThat((new Chance(70).minus(new Chance(30))),is(equalTo(new Chance(40))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativeResult() {
        new Chance(30).minus(new Chance(70));
    }

    @Test
    public void shouldDeductPrimitiveFloats() {
        assertThat(0.7F - 0.3F, is(equalTo(0.4F)));

    }

    @Test
    public void shouldDeductFloats() {
        assertThat((new Float(0.7F)) - (new Float(0.3F)), is(equalTo(new Float(0.4F))));

    }

    @Test
    public void shouldDeductDoubles() {
        assertThat((new Double(0.7)) - (new Double(0.3)),is(equalTo(new Double(0.4))));
    }

    @Test
    public void shouldDeductBigDecimals() {
        assertThat((new BigDecimal("0.7").subtract(new BigDecimal("0.3"))),is(equalTo(new BigDecimal("0.4"))));
    }



}
