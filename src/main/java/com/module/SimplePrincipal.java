package com.module;

import java.security.Principal;

/**
 * Created by Song on 2017/3/16.
 */
public class SimplePrincipal implements Principal {
    /**
     Constructs	a	SimplePrincipal to	hold	a	description	and	a	value.
     @param role Name the	role	name
     */
    private	String descr;
    private	String value;

    public	SimplePrincipal(String	descr,	String	value)	{
        this.descr = descr;	this.value = value;
    }

    public String getName() { return descr + "=" +	value;}
    public boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;
        if (otherObject ==	null) return false;
        if (getClass() != otherObject.getClass()) return false;
        SimplePrincipal other =	(SimplePrincipal) otherObject;
        return getName().equals(other.getName());
    }
    public int hashCode() {	return getName().hashCode(); }
}
