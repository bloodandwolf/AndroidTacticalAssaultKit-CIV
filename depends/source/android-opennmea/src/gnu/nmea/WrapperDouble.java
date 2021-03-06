/*
 * OpenNMEA - A Java library for parsing NMEA 0183 data sentences
 * Copyright (C)2006 Joey Gannon
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package gnu.nmea;

/**
 * A wrapper object for the <code>double</code> datatype that stores a
 * description string in addition to the data value
 * 
 * @author Joey Gannon
 * @version 1.00, 08/11/06
 * @since OpenNMEA v0.1
 */
public class WrapperDouble implements Wrapper
{
    private double value;
    private String description;

    /**
     * Constructs a <code>double</code> wrapper with a blank description string
     * @param s the string containing the numerical data for the wrapper
     */
    public WrapperDouble(String s)
    {
        try {
            value = Double.parseDouble(s);
        } catch (Exception e) {
            value = Double.NaN;
        }
        description = "";
    }

    /**
     * Constructs a <code>double</code> wrapper with a specific description string
     * @param s the string containing the numerical data for the wrapper
     * @param t the string describing the contents of the wrapper
     */
    public WrapperDouble(String s, String t)
    {
        try {
            value = new Double(Double.parseDouble(s));
        } catch (Exception e) {
            value = Double.NaN;
        }
        description = t;
    }

    /**
     * Returns the floating-point value of the wrapped type
     * (identical to {@link #doubleValue()}) 
     * @return the value as a double
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Returns the boolean value of the wrapped type
     * @return the value as a boolean
     */
    public boolean booleanValue()
    {
        return (value != 0.0) && (value != Double.NaN);
    }

    /**
     * Returns the integer value of the wrapped type
     * @return the value as an int
     */
    public int intValue()
    {
        if (value == Double.NaN)
            return Integer.MIN_VALUE;
        return (int) value;
    }

    /**
     * Returns the floating-point value of the wrapped type
     * @return the value as a double
     */
    public double doubleValue()
    {
        return value;
    }

    /**
     * Determines if the wrapped type contains a number
     * @return true is the wrapper contains a number, false if it contains NaN
     */
    public boolean isValid()
    {
        return (value != Double.NaN);
    }

    /**
     * Determines if this WrapperDouble is equal to another object
     * <p>
     * This method returns false if <code>o</code> is not a Wrapper.
     * @return true if the objects are equal, false otherwise
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Wrapper))
            return false;
        return (doubleValue() == ((Wrapper) o).doubleValue());
    }

    /**
     * Compares this WrapperDouble to another Wrapper numerically
     * @return <code>0</code> if the Wrappers are equal, <code>1</code>
     *         if <code>this</code> is greater than <code>w</code>,
     *         <code>-1</code> if <code>this</code> is less than
     *         <code>w</code>
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Wrapper w)
    {
        return Double.compare(doubleValue(), w.doubleValue());
    }

    /**
     * Returns a string representation of this wrapper
     * <p>
     * If the wrapper has a description string, this method returns
     * "<code>description</code>: <code>value</code>". Otherwise, this
     * method returns "<code>value</code>".
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return description + (description.equals("") ? "" : ": ") + value;
    }
}
