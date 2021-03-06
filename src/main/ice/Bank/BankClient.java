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

public class BankClient implements java.lang.Cloneable,
                                   java.io.Serializable
{
    public UserData userData;

    public AccountType accountType;

    public Money balance;

    public BankClient()
    {
        this.userData = new UserData();
        this.accountType = AccountType.STANDARD;
        this.balance = new Money();
    }

    public BankClient(UserData userData, AccountType accountType, Money balance)
    {
        this.userData = userData;
        this.accountType = accountType;
        this.balance = balance;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        BankClient r = null;
        if(rhs instanceof BankClient)
        {
            r = (BankClient)rhs;
        }

        if(r != null)
        {
            if(this.userData != r.userData)
            {
                if(this.userData == null || r.userData == null || !this.userData.equals(r.userData))
                {
                    return false;
                }
            }
            if(this.accountType != r.accountType)
            {
                if(this.accountType == null || r.accountType == null || !this.accountType.equals(r.accountType))
                {
                    return false;
                }
            }
            if(this.balance != r.balance)
            {
                if(this.balance == null || r.balance == null || !this.balance.equals(r.balance))
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
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Bank::BankClient");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, userData);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, accountType);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, balance);
        return h_;
    }

    public BankClient clone()
    {
        BankClient c = null;
        try
        {
            c = (BankClient)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        UserData.ice_write(ostr, this.userData);
        AccountType.ice_write(ostr, this.accountType);
        Money.ice_write(ostr, this.balance);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.userData = UserData.ice_read(istr);
        this.accountType = AccountType.ice_read(istr);
        this.balance = Money.ice_read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, BankClient v)
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

    static public BankClient ice_read(com.zeroc.Ice.InputStream istr)
    {
        BankClient v = new BankClient();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<BankClient> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, BankClient v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<BankClient> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(BankClient.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final BankClient _nullMarshalValue = new BankClient();

    /** @hidden */
    public static final long serialVersionUID = -55261867050263605L;
}
