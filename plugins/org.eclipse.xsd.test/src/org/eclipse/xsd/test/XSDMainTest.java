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
 * $Id: XSDMainTest.java,v 1.4.2.1 2005/06/08 18:26:23 nickb Exp $
 */
package org.eclipse.xsd.test;


import java.io.File;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.eclipse.core.runtime.IPlatformRunnable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDInclude;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.util.XSDPrototypicalSchema;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;


/**
 * Supports command line invocation to drive XML Schema model tests.
 * It handles both {@link #run headless} invocation and {@link #main standalone} invocation.
 * <p>
 * You can execute one of these test by running
 *<pre>
 *  xsd-test.bat
 *</pre>
 * or
 *<pre>
 *  xsd-standalone-test.bat
 *</pre>
 * from the directory:
 *<pre>
 *  plugins/org.eclipse.xsd.test/test/
 *</pre>
 * </p>
 * @see #run
 * @see #main
 */
public class XSDMainTest // implements IPlatformRunnable 
{
  {
    // This is needed because we can't have the following in the plugin.xml
    //
    //   <extension point = "org.eclipse.emf.extension_parser">
    //     <parser type="xsd" class="org.eclipse.xsd.util.XSDResourceFactoryImpl"/>
    //   </extension>
    //
    // The com.ibm.etools.xsdmodel plugin, shipped with WSAD, has a conflicting registration for this.
    //
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
  }

  public static class Runnable extends XSDMainTest implements IPlatformRunnable
  {
  }

  /**
   * Executes a stand-alone test.
   * @param args an array of Strings from the command line.
   * @see #run
   */
  public static void main(String args[]) 
  {
    System.exit(((Integer)new XSDMainTest().run(args)).intValue());
  }

  /**
   * Creates an instance.
   */
  public XSDMainTest() 
  {
  }

  /**
   * Executes a headless workbench test.
   * If no arguments are provided, the {@link XSDPrototypicalSchema} will be tested;
   * otherwise each argument is taken to be the URI of a schema which will be loaded and printed.
   * @param object an array of Strings from the command line, each representing the URI of a schema.
   * @return <code>0</code> indicating success, or <code>1</code> indicating failure.
   * @see #main
   */
  public Object run(Object object) 
  {
    try
    {
      // If there are no arguments...
      //
      String[] arguments = (String[])object;
      if (arguments.length == 0) 
      {
        // Serialize the Purchase Order schema sample.
        //
        System.out.println("<!-- ** PurchaseOrderSchema ** -->");
        XSDSchema xsdPurchaseOrderSchema = XSDPrototypicalSchema.getPurchaseOrderSchema();
        if (xsdPurchaseOrderSchema.getElement() == null)
        {
          xsdPurchaseOrderSchema.updateElement();
        }
        XSDResourceImpl.serialize(System.out, xsdPurchaseOrderSchema.getElement());

        // Serialize the prototypical schema sample.
        //
        System.out.println("<!-- ** PrototypicalSchema ** -->");
        XSDSchema xsdSchema = XSDPrototypicalSchema.getPrototypicalSchema();
        if (xsdSchema.getElement() == null)
        {
          xsdSchema.updateElement();
        }
        XSDResourceImpl.serialize(System.out, xsdSchema.getElement());
        System.out.println("===== clone =====");

        XSDSchema clonedSchema = (XSDSchema)xsdSchema.cloneConcreteComponent(true, true);
        clonedSchema.setElement(null);
        clonedSchema.updateElement();

        XSDResourceImpl.serialize(System.out, clonedSchema.getElement());

        System.out.println("===== clone schema for schema =====");
        XSDSchema clonedSchemaForSchema = (XSDSchema)clonedSchema.getSchemaForSchema().cloneConcreteComponent(true, true);
        clonedSchemaForSchema.setElement(null);
        clonedSchemaForSchema.updateElement();
        XSDResourceImpl.serialize(System.out, clonedSchemaForSchema.getElement());
      }
      else if (arguments[0].equals("-printTest"))
      {
        XSDPrototypicalSchema xsdPrototypicalSchema = XSDPrototypicalSchema.getInstance();

        // Iterate over the schema arguments.
        //
        for (int i = 1; i < arguments.length; ++i)
        {
          xsdPrototypicalSchema.printSchema(arguments[i]);
        }
      }
      else if (arguments[0].equals("-createTest"))
      {
        Document document = (Document)XSDPrototypicalSchema.getPurchaseOrderSchema().getDocument().cloneNode(true);

        XSDSchema xsdSchema = XSDPrototypicalSchema.getInstance().createSchema(document.getDocumentElement());
        if (xsdSchema != null)
        {
          if (xsdSchema.getElement() == null)
          {
            xsdSchema.updateElement();
          }
          XSDResourceImpl.serialize(System.out, xsdSchema.getElement());
        }
      }
      else if (arguments[0].equals("-saveTest"))
      {
        XSDPrototypicalSchema.getInstance().savePurchaseOrderSchema(arguments[1]);
      }
      else if (arguments[0].equals("-traceLoad"))
      {
        for (int i = 1; i < arguments.length; ++i)
        {
          XSDPrototypicalSchema.getInstance().traceLoading(arguments[i]);
        }
      }
      else if (arguments[0].equals("-clone"))
      {
        XSDPrototypicalSchema.getInstance().printComponent
          (System.out,
           XSDPrototypicalSchema.getInstance().cloneComponent
             (XSDPrototypicalSchema.getPurchaseOrderSchema(), false));

        XSDPrototypicalSchema.getInstance().printComponent
          (System.out,
           XSDPrototypicalSchema.getInstance().cloneComponent
             (XSDPrototypicalSchema.getPurchaseOrderSchema(), true));
      }
      else if (arguments[0].equals("-crossReferenceTest"))
      {
        XSDPrototypicalSchema.getInstance().crossReferenceTest(System.out);
      }
      else if (arguments[0].equals("-validate"))
      {
        // Iterate over the schema arguments.
        //
        for (int i = 1; i < arguments.length; ++i)
        {
          validate(arguments[i]);
        }
      }
      else
      {
        // Iterate over the schema arguments.
        //
        for (int i = 0; i < arguments.length; ++i)
        {
          System.out.println("<!-- << " + arguments[i] + " >> -->");
          loadAndPrint(arguments[i]);
        }
      }

      return new Integer(0);
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
      return new Integer(1);
    }
  }

  /**
   * Prints a header tag for the given schema.
   * @param xsdSchema a schema.
   */
  protected void printSchemaStart(XSDSchema xsdSchema)
  {
    System.out.print("<schema targetNamespace=\"");
    if (xsdSchema.getTargetNamespace() != null)
    {
      System.out.print(xsdSchema.getTargetNamespace());
    }
    System.out.print("\" schemaLocation=\"");
    if (xsdSchema.getSchemaLocation() != null)
    {
      System.out.print(xsdSchema.getSchemaLocation());
    }
    System.out.print("\">");
  }

  /**
   * Prints directive tags for those directives that reference the given schema.
   * @param indent the indentation string to print at the start of each line.
   * @param xsdSchema a schema.
   */
  protected void printDirectives(String indent, XSDSchema xsdSchema)
  {
    System.out.print(indent);
    printSchemaStart(xsdSchema);
    System.out.println();

    if (!xsdSchema.getReferencingDirectives().isEmpty())
    {
      System.out.println(indent + "  <referencingDirectives>");
      for (Iterator referencingDirectives = xsdSchema.getReferencingDirectives().iterator(); referencingDirectives.hasNext(); )
      {
        XSDSchemaDirective xsdSchemaDirective = (XSDSchemaDirective)referencingDirectives.next();
        XSDSchema referencingSchema = xsdSchemaDirective.getSchema();
        System.out.print(indent + "    ");
        printSchemaStart(referencingSchema);
        System.out.println();
        System.out.print(indent +  "      ");
        if (xsdSchemaDirective instanceof XSDImport)
        {
          XSDImport xsdImport = (XSDImport)xsdSchemaDirective;
          System.out.print("<import namespace=\"");
          if (xsdImport.getNamespace() != null)
          {
            System.out.print(xsdImport.getNamespace());
          }
          System.out.print("\" schemaLocation=\"");
        }
        else if (xsdSchemaDirective instanceof XSDRedefine)
        {
          System.out.print("<redefine schemaLocation=\"");
        }
        else if (xsdSchemaDirective instanceof XSDInclude)
        {
        System.out.print("<include schemaLocation=\"");
        }
        if (xsdSchemaDirective.getSchemaLocation() != null)
        {
          System.out.print(xsdSchemaDirective.getSchemaLocation());
        }
        System.out.println("\"/>");
        System.out.println(indent + "    </schema>");
      }
      System.out.println(indent + "  </referencingDirectives>");
    }

    if (!xsdSchema.getIncorporatedVersions().isEmpty())
    {
      System.out.println(indent + "  <incorporatedVersions>");
      for (Iterator incorporatedVersions = xsdSchema.getIncorporatedVersions().iterator(); incorporatedVersions.hasNext(); )
      {
        XSDSchema incorporatedVersion = (XSDSchema)incorporatedVersions.next();
        printDirectives(indent + "    ", incorporatedVersion);
      }
      System.out.println(indent + "  </incorporatedVersions>");
    }

    System.out.println(indent + "</schema>");
  }

  /**
   * Load the XML Schema file and print information about it.
   * @param xsdFile the URI of an XML Schema file.
   */
  public void loadAndPrint(String xsdFile) throws Exception
  {
    // This let's us test whether the string exists as a file.
    // It not, we try as a URI.
    //
    URI uri;
    File file = new File(xsdFile);
    if (file.isFile())
    {
      uri = URI.createFileURI(file.getCanonicalFile().toString());
    }
    else
    {
      uri = URI.createURI(xsdFile);
    }

    // Create a resource set, create a schema resource, and load the main schema file into it.
    //
    ResourceSet resourceSet = new ResourceSetImpl();
    XSDResourceImpl xsdMainResource = (XSDResourceImpl)resourceSet.createResource(URI.createURI("*.xsd"));
    xsdMainResource.setURI(uri);
    xsdMainResource.load(resourceSet.getLoadOptions());

    // Iterate over all the resources, i.e., the main resource and those that have been included or imported.
    //
    for (Iterator resources = resourceSet.getResources().iterator(); resources.hasNext(); )
    {
      Object resource = resources.next();
      if (resource instanceof XSDResourceImpl)
      {
        XSDResourceImpl xsdResource = (XSDResourceImpl)resource;
        XSDSchema xsdSchema = xsdResource.getSchema();

        System.out.println("<!-- ===== Schema Composition =====");
        printDirectives("  ", xsdSchema);
        System.out.println("-->");

        Element element = xsdSchema.getElement();
        if (element != null)
        {
          // Print the serialization of the model.
          //
          XSDResourceImpl.serialize(System.out, element);
        }
      }
    }

    // This removes the associated DOM element, creates a new associated DOM element, and then prints it.
    // This is a good test for how well serialization works for a model created "bottom up".
    //
    XSDSchema xsdMainSchema = xsdMainResource.getSchema();
    xsdMainSchema.setDocument(null);
    xsdMainSchema.setElement(null);
    xsdMainSchema.updateElement();
    System.out.println("<!-- [ " + xsdMainSchema.getSchemaLocation() + " ] -->");
    XSDResourceImpl.serialize(System.out, xsdMainSchema.getElement());
  }

  /**
   * Load the XML Schema file and print any diagnostics information about it.
   * @param xsdFile the URI of an XML Schema file.
   */
  public void validate(String xsdFile) throws Exception
  {
    // This let's us test whether the string exists as a file.
    // It not, we try as a URI.
    //
    URI uri;
    File file = new File(xsdFile);
    if (file.isFile())
    {
      uri = URI.createFileURI(file.getCanonicalFile().toString());
    }
    else
    {
      uri = URI.createURI(xsdFile);
    }

    // Create a resource set, create a schema resource, and load the main schema file into it.
    //
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getLoadOptions().put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);
    XSDResourceImpl xsdMainResource = (XSDResourceImpl)resourceSet.createResource(URI.createURI("*.xsd"));
    xsdMainResource.setURI(uri);
    xsdMainResource.load(resourceSet.getLoadOptions());

    // Iterate over all the resources, i.e., the main resource and those that have been included or imported.
    //
    for (Iterator resources = resourceSet.getResources().iterator(); resources.hasNext(); )
    {
      Object resource = resources.next();
      if (resource instanceof XSDResourceImpl)
      {
        XSDResourceImpl xsdResource = (XSDResourceImpl)resource;

        System.err.println("--> " + xsdResource.getURI());

        XSDSchema xsdSchema = xsdResource.getSchema();
        xsdSchema.validate();

        if (!xsdSchema.getAllDiagnostics().isEmpty())
        {
          for (Iterator i = xsdSchema.getAllDiagnostics().iterator(); i.hasNext(); )
          {
            XSDDiagnostic xsdDiagnostic = (XSDDiagnostic)i.next();
  
            String localizedSeverity = 
             XSDPlugin.INSTANCE.getString("_UI_XSDDiagnosticSeverity_" + xsdDiagnostic.getSeverity());

            System.err.println
              (XSDPlugin.INSTANCE.getString
                ("_UI_DiagnosticFileLineColumn_message", 
                 new Object [] 
                 { 
                   localizedSeverity, 
                   xsdDiagnostic.getLocationURI(), 
                   new Integer(xsdDiagnostic.getLine()), 
                   new Integer(xsdDiagnostic.getColumn()) 
                 }));

            System.err.println(xsdDiagnostic.getMessage());
          }
        }
      }
    }
  }
}
