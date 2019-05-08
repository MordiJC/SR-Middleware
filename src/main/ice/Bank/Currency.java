//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.2
//
// <auto-generated>
//
// Generated from file `bank.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Bank;

public enum Currency implements java.io.Serializable
{
    PLN(0),
    EUR(1),
    USD(2),
    GBP(3);

    public int value()
    {
        return _value;
    }

    public static Currency valueOf(int v)
    {
        switch(v)
        {
        case 0:
            return PLN;
        case 1:
            return EUR;
        case 2:
            return USD;
        case 3:
            return GBP;
        }
        return null;
    }

    private Currency(int v)
    {
        _value = v;
    }

    public void ice_write(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeEnum(_value, 3);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, Currency v)
    {
        if(v == null)
        {
            ostr.writeEnum(Bank.Currency.PLN.value(), 3);
        }
        else
        {
            ostr.writeEnum(v.value(), 3);
        }
    }

    public static Currency ice_read(com.zeroc.Ice.InputStream istr)
    {
        int v = istr.readEnum(3);
        return validate(v);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Currency> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Currency v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            ice_write(ostr, v);
        }
    }

    public static java.util.Optional<Currency> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            return java.util.Optional.of(ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static Currency validate(int v)
    {
        final Currency e = valueOf(v);
        if(e == null)
        {
            throw new com.zeroc.Ice.MarshalException("enumerator value " + v + " is out of range");
        }
        return e;
    }

    private final int _value;
}