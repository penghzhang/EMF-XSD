/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDBooleanType.java,v 1.2.2.1 2005/06/08 18:26:23 nickb Exp $
 */
package org.eclipse.xsd.impl.type;

public class XSDBooleanType extends XSDAnySimpleType
{

  public Object getValue(String literal)
  {
    if ("true".equals(literal) || "1".equals(literal))
    {
      return Boolean.TRUE;
    }
    else if ("false".equals(literal) || "0".equals(literal))
    {
      return Boolean.FALSE;
    }
    return null;
  }
}
