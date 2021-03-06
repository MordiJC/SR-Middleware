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

public class InvalidUuidException extends com.zeroc.Ice.UserException
{
    public InvalidUuidException()
    {
        this.uuid = "";
    }

    public InvalidUuidException(Throwable cause)
    {
        super(cause);
        this.uuid = "";
    }

    public InvalidUuidException(String uuid)
    {
        this.uuid = uuid;
    }

    public InvalidUuidException(String uuid, Throwable cause)
    {
        super(cause);
        this.uuid = uuid;
    }

    public String ice_id()
    {
        return "::Bank::InvalidUuidException";
    }

    public String uuid;

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::Bank::InvalidUuidException", -1, true);
        ostr_.writeString(uuid);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        uuid = istr_.readString();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = -1384181642084594388L;
}
