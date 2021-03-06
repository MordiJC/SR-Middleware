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

public class LoanResponse implements java.lang.Cloneable,
                                     java.io.Serializable
{
    public Money baseCost;

    public Money foreignCost;

    public LoanResponse()
    {
        this.baseCost = new Money();
        this.foreignCost = new Money();
    }

    public LoanResponse(Money baseCost, Money foreignCost)
    {
        this.baseCost = baseCost;
        this.foreignCost = foreignCost;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        LoanResponse r = null;
        if(rhs instanceof LoanResponse)
        {
            r = (LoanResponse)rhs;
        }

        if(r != null)
        {
            if(this.baseCost != r.baseCost)
            {
                if(this.baseCost == null || r.baseCost == null || !this.baseCost.equals(r.baseCost))
                {
                    return false;
                }
            }
            if(this.foreignCost != r.foreignCost)
            {
                if(this.foreignCost == null || r.foreignCost == null || !this.foreignCost.equals(r.foreignCost))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Bank::LoanResponse");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, baseCost);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, foreignCost);
        return h_;
    }

    public LoanResponse clone()
    {
        LoanResponse c = null;
        try
        {
            c = (LoanResponse)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        Money.ice_write(ostr, this.baseCost);
        Money.ice_write(ostr, this.foreignCost);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.baseCost = Money.ice_read(istr);
        this.foreignCost = Money.ice_read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, LoanResponse v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public LoanResponse ice_read(com.zeroc.Ice.InputStream istr)
    {
        LoanResponse v = new LoanResponse();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<LoanResponse> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, LoanResponse v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<LoanResponse> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(LoanResponse.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final LoanResponse _nullMarshalValue = new LoanResponse();

    /** @hidden */
    public static final long serialVersionUID = -8419117854724963703L;
}
