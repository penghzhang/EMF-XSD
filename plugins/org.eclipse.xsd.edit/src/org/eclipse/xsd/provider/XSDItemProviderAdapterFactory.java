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
 * $Id: XSDItemProviderAdapterFactory.java,v 1.3.2.1 2005/06/08 18:26:23 nickb Exp $
 */
package org.eclipse.xsd.provider;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.xsd.util.XSDAdapterFactory;


/**
 * This is the factory that is used to provide the interfaces needed to support {@link org.eclipse.jface.viewers.Viewer}s.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support property sheets, see {@link org.eclipse.ui.views.properties.PropertySheet}.
 * Note that most of the adapters are shared among multiple instances.
 */
public class XSDItemProviderAdapterFactory 
  extends 
    XSDAdapterFactory 
  implements 
    ComposeableAdapterFactory, 
    IChangeNotifier,
    IDisposable
{
  /**
   *  This keeps track of the root adapter factory that delegates to this adapter factory.
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IDisposable}.
   */
  protected Disposable disposable = new Disposable();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   */
  protected Collection supportedTypes = new ArrayList();

  /**
   * This constructs an instance.
   */
  public XSDItemProviderAdapterFactory()
  {
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemPropertySource.class);
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(ITableItemLabelProvider.class);
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDAnnotation} instances.
   */
  protected XSDAnnotationItemProvider xsdAnnotationItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDAnnotation}.
   */
  public Adapter createXSDAnnotationAdapter()
  {
    if (xsdAnnotationItemProvider == null)
    {
      xsdAnnotationItemProvider = new XSDAnnotationItemProvider(this);
    }

    return xsdAnnotationItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDConcreteComponent} instances.
   */
  protected XSDConcreteComponentItemProvider xsdConcreteComponentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDConcreteComponent}.
   */
  public Adapter createXSDConcreteComponentAdapter()
  {
    if (xsdConcreteComponentItemProvider == null)
    {
      xsdConcreteComponentItemProvider = new XSDConcreteComponentItemProvider(this);
    }

    return xsdConcreteComponentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDComponent} instances.
   */
  protected XSDComponentItemProvider xsdComponentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDComponent}.
   */
  public Adapter createXSDComponentAdapter()
  {
    if (xsdComponentItemProvider == null)
    {
      xsdComponentItemProvider = new XSDComponentItemProvider(this);
    }

    return xsdComponentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDAttributeUse} instances.
   */
  protected XSDAttributeUseItemProvider xsdAttributeUseItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDAttributeUse}.
   */
  public Adapter createXSDAttributeUseAdapter()
  {
    if (xsdAttributeUseItemProvider == null)
    {
      xsdAttributeUseItemProvider = new XSDAttributeUseItemProvider(this);
    }

    return xsdAttributeUseItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDFeature} instances.
   */
  protected XSDFeatureItemProvider xsdFeatureItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDFeature}.
   */
  public Adapter createXSDFeatureAdapter()
  {
    if (xsdFeatureItemProvider == null)
    {
      xsdFeatureItemProvider = new XSDFeatureItemProvider(this);
    }

    return xsdFeatureItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDElementDeclaration} instances.
   */
  protected XSDElementDeclarationItemProvider xsdElementDeclarationItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDElementDeclaration}.
   */
  public Adapter createXSDElementDeclarationAdapter()
  {
    if (xsdElementDeclarationItemProvider == null)
    {
      xsdElementDeclarationItemProvider = new XSDElementDeclarationItemProvider(this);
    }

    return xsdElementDeclarationItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDSchema} instances.
   */
  protected XSDSchemaItemProvider xsdSchemaItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDSchema}.
   */
  public Adapter createXSDSchemaAdapter()
  {
    if (xsdSchemaItemProvider == null)
    {
      xsdSchemaItemProvider = new XSDSchemaItemProvider(this);
    }

    return xsdSchemaItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDComplexTypeDefinition} instances.
   */
  protected XSDComplexTypeDefinitionItemProvider xsdComplexTypeDefinitionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDComplexTypeDefinition}.
   */
  public Adapter createXSDComplexTypeDefinitionAdapter()
  {
    if (xsdComplexTypeDefinitionItemProvider == null)
    {
      xsdComplexTypeDefinitionItemProvider = new XSDComplexTypeDefinitionItemProvider(this);
    }

    return xsdComplexTypeDefinitionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDScope} instances.
   */
  protected XSDScopeItemProvider xsdScopeItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDScope}.
   */
  public Adapter createXSDScopeAdapter()
  {
    if (xsdScopeItemProvider == null)
    {
      xsdScopeItemProvider = new XSDScopeItemProvider(this);
    }

    return xsdScopeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDTypeDefinition} instances.
   */
  protected XSDTypeDefinitionItemProvider xsdTypeDefinitionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDTypeDefinition}.
   */
  public Adapter createXSDTypeDefinitionAdapter()
  {
    if (xsdTypeDefinitionItemProvider == null)
    {
      xsdTypeDefinitionItemProvider = new XSDTypeDefinitionItemProvider(this);
    }

    return xsdTypeDefinitionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDSimpleTypeDefinition} instances.
   */
  protected XSDSimpleTypeDefinitionItemProvider xsdSimpleTypeDefinitionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDSimpleTypeDefinition}.
   */
  public Adapter createXSDSimpleTypeDefinitionAdapter()
  {
    if (xsdSimpleTypeDefinitionItemProvider == null)
    {
      xsdSimpleTypeDefinitionItemProvider = new XSDSimpleTypeDefinitionItemProvider(this);
    }

    return xsdSimpleTypeDefinitionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDComplexTypeContent} instances.
   */
  protected XSDComplexTypeContentItemProvider xsdComplexTypeContentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDComplexTypeContent}.
   */
  public Adapter createXSDComplexTypeContentAdapter()
  {
    if (xsdComplexTypeContentItemProvider == null)
    {
      xsdComplexTypeContentItemProvider = new XSDComplexTypeContentItemProvider(this);
    }

    return xsdComplexTypeContentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDParticle} instances.
   */
  protected XSDParticleItemProvider xsdParticleItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDParticle}.
   */
  public Adapter createXSDParticleAdapter()
  {
    if (xsdParticleItemProvider == null)
    {
      xsdParticleItemProvider = new XSDParticleItemProvider(this);
    }

    return xsdParticleItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDModelGroup} instances.
   */
  protected XSDModelGroupItemProvider xsdModelGroupItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDModelGroup}.
   */
  public Adapter createXSDModelGroupAdapter()
  {
    if (xsdModelGroupItemProvider == null)
    {
      xsdModelGroupItemProvider = new XSDModelGroupItemProvider(this);
    }

    return xsdModelGroupItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDTerm} instances.
   */
  protected XSDTermItemProvider xsdTermItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDTerm}.
   */
  public Adapter createXSDTermAdapter()
  {
    if (xsdTermItemProvider == null)
    {
      xsdTermItemProvider = new XSDTermItemProvider(this);
    }

    return xsdTermItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDParticleContent} instances.
   */
  protected XSDParticleContentItemProvider xsdParticleContentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDParticleContent}.
   */
  public Adapter createXSDParticleContentAdapter()
  {
    if (xsdParticleContentItemProvider == null)
    {
      xsdParticleContentItemProvider = new XSDParticleContentItemProvider(this);
    }

    return xsdParticleContentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDModelGroupDefinition} instances.
   */
  protected XSDModelGroupDefinitionItemProvider xsdModelGroupDefinitionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDModelGroupDefinition}.
   */
  public Adapter createXSDModelGroupDefinitionAdapter()
  {
    if (xsdModelGroupDefinitionItemProvider == null)
    {
      xsdModelGroupDefinitionItemProvider = new XSDModelGroupDefinitionItemProvider(this);
    }

    return xsdModelGroupDefinitionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDRedefineContent} instances.
   */
  protected XSDRedefineContentItemProvider xsdRedefineContentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDRedefineContent}.
   */
  public Adapter createXSDRedefineContentAdapter()
  {
    if (xsdRedefineContentItemProvider == null)
    {
      xsdRedefineContentItemProvider = new XSDRedefineContentItemProvider(this);
    }

    return xsdRedefineContentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDRedefinableComponent} instances.
   */
  protected XSDRedefinableComponentItemProvider xsdRedefinableComponentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDRedefinableComponent}.
   */
  public Adapter createXSDRedefinableComponentAdapter()
  {
    if (xsdRedefinableComponentItemProvider == null)
    {
      xsdRedefinableComponentItemProvider = new XSDRedefinableComponentItemProvider(this);
    }

    return xsdRedefinableComponentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDAttributeGroupDefinition} instances.
   */
  protected XSDAttributeGroupDefinitionItemProvider xsdAttributeGroupDefinitionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDAttributeGroupDefinition}.
   */
  public Adapter createXSDAttributeGroupDefinitionAdapter()
  {
    if (xsdAttributeGroupDefinitionItemProvider == null)
    {
      xsdAttributeGroupDefinitionItemProvider = new XSDAttributeGroupDefinitionItemProvider(this);
    }

    return xsdAttributeGroupDefinitionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDAttributeGroupContent} instances.
   */
  protected XSDAttributeGroupContentItemProvider xsdAttributeGroupContentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDAttributeGroupContent}.
   */
  public Adapter createXSDAttributeGroupContentAdapter()
  {
    if (xsdAttributeGroupContentItemProvider == null)
    {
      xsdAttributeGroupContentItemProvider = new XSDAttributeGroupContentItemProvider(this);
    }

    return xsdAttributeGroupContentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDWildcard} instances.
   */
  protected XSDWildcardItemProvider xsdWildcardItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDWildcard}.
   */
  public Adapter createXSDWildcardAdapter()
  {
    if (xsdWildcardItemProvider == null)
    {
      xsdWildcardItemProvider = new XSDWildcardItemProvider(this);
    }

    return xsdWildcardItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDNamedComponent} instances.
   */
  protected XSDNamedComponentItemProvider xsdNamedComponentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDNamedComponent}.
   */
  public Adapter createXSDNamedComponentAdapter()
  {
    if (xsdNamedComponentItemProvider == null)
    {
      xsdNamedComponentItemProvider = new XSDNamedComponentItemProvider(this);
    }

    return xsdNamedComponentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDIdentityConstraintDefinition} instances.
   */
  protected XSDIdentityConstraintDefinitionItemProvider xsdIdentityConstraintDefinitionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDIdentityConstraintDefinition}.
   */
  public Adapter createXSDIdentityConstraintDefinitionAdapter()
  {
    if (xsdIdentityConstraintDefinitionItemProvider == null)
    {
      xsdIdentityConstraintDefinitionItemProvider = new XSDIdentityConstraintDefinitionItemProvider(this);
    }

    return xsdIdentityConstraintDefinitionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDXPathDefinition} instances.
   */
  protected XSDXPathDefinitionItemProvider xsdxPathDefinitionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDXPathDefinition}.
   */
  public Adapter createXSDXPathDefinitionAdapter()
  {
    if (xsdxPathDefinitionItemProvider == null)
    {
      xsdxPathDefinitionItemProvider = new XSDXPathDefinitionItemProvider(this);
    }

    return xsdxPathDefinitionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDNotationDeclaration} instances.
   */
  protected XSDNotationDeclarationItemProvider xsdNotationDeclarationItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDNotationDeclaration}.
   */
  public Adapter createXSDNotationDeclarationAdapter()
  {
    if (xsdNotationDeclarationItemProvider == null)
    {
      xsdNotationDeclarationItemProvider = new XSDNotationDeclarationItemProvider(this);
    }

    return xsdNotationDeclarationItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDSchemaContent} instances.
   */
  protected XSDSchemaContentItemProvider xsdSchemaContentItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDSchemaContent}.
   */
  public Adapter createXSDSchemaContentAdapter()
  {
    if (xsdSchemaContentItemProvider == null)
    {
      xsdSchemaContentItemProvider = new XSDSchemaContentItemProvider(this);
    }

    return xsdSchemaContentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDAttributeDeclaration} instances.
   */
  protected XSDAttributeDeclarationItemProvider xsdAttributeDeclarationItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDAttributeDeclaration}.
   */
  public Adapter createXSDAttributeDeclarationAdapter()
  {
    if (xsdAttributeDeclarationItemProvider == null)
    {
      xsdAttributeDeclarationItemProvider = new XSDAttributeDeclarationItemProvider(this);
    }

    return xsdAttributeDeclarationItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDSchemaDirective} instances.
   */
  protected XSDSchemaDirectiveItemProvider xsdSchemaDirectiveItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDSchemaDirective}.
   */
  public Adapter createXSDSchemaDirectiveAdapter()
  {
    if (xsdSchemaDirectiveItemProvider == null)
    {
      xsdSchemaDirectiveItemProvider = new XSDSchemaDirectiveItemProvider(this);
    }

    return xsdSchemaDirectiveItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDImport} instances.
   */
  protected XSDImportItemProvider xsdImportItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDImport}.
   */
  public Adapter createXSDImportAdapter()
  {
    if (xsdImportItemProvider == null)
    {
      xsdImportItemProvider = new XSDImportItemProvider(this);
    }

    return xsdImportItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDSchemaCompositor} instances.
   */
  protected XSDSchemaCompositorItemProvider xsdSchemaCompositorItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDSchemaCompositor}.
   */
  public Adapter createXSDSchemaCompositorAdapter()
  {
    if (xsdSchemaCompositorItemProvider == null)
    {
      xsdSchemaCompositorItemProvider = new XSDSchemaCompositorItemProvider(this);
    }

    return xsdSchemaCompositorItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDRedefine} instances.
   */
  protected XSDRedefineItemProvider xsdRedefineItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDRedefine}.
   */
  public Adapter createXSDRedefineAdapter()
  {
    if (xsdRedefineItemProvider == null)
    {
      xsdRedefineItemProvider = new XSDRedefineItemProvider(this);
    }

    return xsdRedefineItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDInclude} instances.
   */
  protected XSDIncludeItemProvider xsdIncludeItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDInclude}.
   */
  public Adapter createXSDIncludeAdapter()
  {
    if (xsdIncludeItemProvider == null)
    {
      xsdIncludeItemProvider = new XSDIncludeItemProvider(this);
    }

    return xsdIncludeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDFacet} instances.
   */
  protected XSDFacetItemProvider xsdFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDFacet}.
   */
  public Adapter createXSDFacetAdapter()
  {
    if (xsdFacetItemProvider == null)
    {
      xsdFacetItemProvider = new XSDFacetItemProvider(this);
    }

    return xsdFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDConstrainingFacet} instances.
   */
  protected XSDConstrainingFacetItemProvider xsdConstrainingFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDConstrainingFacet}.
   */
  public Adapter createXSDConstrainingFacetAdapter()
  {
    if (xsdConstrainingFacetItemProvider == null)
    {
      xsdConstrainingFacetItemProvider = new XSDConstrainingFacetItemProvider(this);
    }

    return xsdConstrainingFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDFixedFacet} instances.
   */
  protected XSDFixedFacetItemProvider xsdFixedFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDFixedFacet}.
   */
  public Adapter createXSDFixedFacetAdapter()
  {
    if (xsdFixedFacetItemProvider == null)
    {
      xsdFixedFacetItemProvider = new XSDFixedFacetItemProvider(this);
    }

    return xsdFixedFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDFractionDigitsFacet} instances.
   */
  protected XSDFractionDigitsFacetItemProvider xsdFractionDigitsFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDFractionDigitsFacet}.
   */
  public Adapter createXSDFractionDigitsFacetAdapter()
  {
    if (xsdFractionDigitsFacetItemProvider == null)
    {
      xsdFractionDigitsFacetItemProvider = new XSDFractionDigitsFacetItemProvider(this);
    }

    return xsdFractionDigitsFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDLengthFacet} instances.
   */
  protected XSDLengthFacetItemProvider xsdLengthFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDLengthFacet}.
   */
  public Adapter createXSDLengthFacetAdapter()
  {
    if (xsdLengthFacetItemProvider == null)
    {
      xsdLengthFacetItemProvider = new XSDLengthFacetItemProvider(this);
    }

    return xsdLengthFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMaxFacet} instances.
   */
  protected XSDMaxFacetItemProvider xsdMaxFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMaxFacet}.
   */
  public Adapter createXSDMaxFacetAdapter()
  {
    if (xsdMaxFacetItemProvider == null)
    {
      xsdMaxFacetItemProvider = new XSDMaxFacetItemProvider(this);
    }

    return xsdMaxFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMaxExclusiveFacet} instances.
   */
  protected XSDMaxExclusiveFacetItemProvider xsdMaxExclusiveFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMaxExclusiveFacet}.
   */
  public Adapter createXSDMaxExclusiveFacetAdapter()
  {
    if (xsdMaxExclusiveFacetItemProvider == null)
    {
      xsdMaxExclusiveFacetItemProvider = new XSDMaxExclusiveFacetItemProvider(this);
    }

    return xsdMaxExclusiveFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDEnumerationFacet} instances.
   */
  protected XSDEnumerationFacetItemProvider xsdEnumerationFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDEnumerationFacet}.
   */
  public Adapter createXSDEnumerationFacetAdapter()
  {
    if (xsdEnumerationFacetItemProvider == null)
    {
      xsdEnumerationFacetItemProvider = new XSDEnumerationFacetItemProvider(this);
    }

    return xsdEnumerationFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDRepeatableFacet} instances.
   */
  protected XSDRepeatableFacetItemProvider xsdRepeatableFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDRepeatableFacet}.
   */
  public Adapter createXSDRepeatableFacetAdapter()
  {
    if (xsdRepeatableFacetItemProvider == null)
    {
      xsdRepeatableFacetItemProvider = new XSDRepeatableFacetItemProvider(this);
    }

    return xsdRepeatableFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDPatternFacet} instances.
   */
  protected XSDPatternFacetItemProvider xsdPatternFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDPatternFacet}.
   */
  public Adapter createXSDPatternFacetAdapter()
  {
    if (xsdPatternFacetItemProvider == null)
    {
      xsdPatternFacetItemProvider = new XSDPatternFacetItemProvider(this);
    }

    return xsdPatternFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMinFacet} instances.
   */
  protected XSDMinFacetItemProvider xsdMinFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMinFacet}.
   */
  public Adapter createXSDMinFacetAdapter()
  {
    if (xsdMinFacetItemProvider == null)
    {
      xsdMinFacetItemProvider = new XSDMinFacetItemProvider(this);
    }

    return xsdMinFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMinExclusiveFacet} instances.
   */
  protected XSDMinExclusiveFacetItemProvider xsdMinExclusiveFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMinExclusiveFacet}.
   */
  public Adapter createXSDMinExclusiveFacetAdapter()
  {
    if (xsdMinExclusiveFacetItemProvider == null)
    {
      xsdMinExclusiveFacetItemProvider = new XSDMinExclusiveFacetItemProvider(this);
    }

    return xsdMinExclusiveFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMinInclusiveFacet} instances.
   */
  protected XSDMinInclusiveFacetItemProvider xsdMinInclusiveFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMinInclusiveFacet}.
   */
  public Adapter createXSDMinInclusiveFacetAdapter()
  {
    if (xsdMinInclusiveFacetItemProvider == null)
    {
      xsdMinInclusiveFacetItemProvider = new XSDMinInclusiveFacetItemProvider(this);
    }

    return xsdMinInclusiveFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMaxInclusiveFacet} instances.
   */
  protected XSDMaxInclusiveFacetItemProvider xsdMaxInclusiveFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMaxInclusiveFacet}.
   */
  public Adapter createXSDMaxInclusiveFacetAdapter()
  {
    if (xsdMaxInclusiveFacetItemProvider == null)
    {
      xsdMaxInclusiveFacetItemProvider = new XSDMaxInclusiveFacetItemProvider(this);
    }

    return xsdMaxInclusiveFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMaxLengthFacet} instances.
   */
  protected XSDMaxLengthFacetItemProvider xsdMaxLengthFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMaxLengthFacet}.
   */
  public Adapter createXSDMaxLengthFacetAdapter()
  {
    if (xsdMaxLengthFacetItemProvider == null)
    {
      xsdMaxLengthFacetItemProvider = new XSDMaxLengthFacetItemProvider(this);
    }

    return xsdMaxLengthFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDMinLengthFacet} instances.
   */
  protected XSDMinLengthFacetItemProvider xsdMinLengthFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMinLengthFacet}.
   */
  public Adapter createXSDMinLengthFacetAdapter()
  {
    if (xsdMinLengthFacetItemProvider == null)
    {
      xsdMinLengthFacetItemProvider = new XSDMinLengthFacetItemProvider(this);
    }

    return xsdMinLengthFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDTotalDigitsFacet} instances.
   */
  protected XSDTotalDigitsFacetItemProvider xsdTotalDigitsFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDTotalDigitsFacet}.
   */
  public Adapter createXSDTotalDigitsFacetAdapter()
  {
    if (xsdTotalDigitsFacetItemProvider == null)
    {
      xsdTotalDigitsFacetItemProvider = new XSDTotalDigitsFacetItemProvider(this);
    }

    return xsdTotalDigitsFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDWhiteSpaceFacet} instances.
   */
  protected XSDWhiteSpaceFacetItemProvider xsdWhiteSpaceFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDWhiteSpaceFacet}.
   */
  public Adapter createXSDWhiteSpaceFacetAdapter()
  {
    if (xsdWhiteSpaceFacetItemProvider == null)
    {
      xsdWhiteSpaceFacetItemProvider = new XSDWhiteSpaceFacetItemProvider(this);
    }

    return xsdWhiteSpaceFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDFundamentalFacet} instances.
   */
  protected XSDFundamentalFacetItemProvider xsdFundamentalFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDFundamentalFacet}.
   */
  public Adapter createXSDFundamentalFacetAdapter()
  {
    if (xsdFundamentalFacetItemProvider == null)
    {
      xsdFundamentalFacetItemProvider = new XSDFundamentalFacetItemProvider(this);
    }

    return xsdFundamentalFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDBoundedFacet} instances.
   */
  protected XSDBoundedFacetItemProvider xsdBoundedFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDBoundedFacet}.
   */
  public Adapter createXSDBoundedFacetAdapter()
  {
    if (xsdBoundedFacetItemProvider == null)
    {
      xsdBoundedFacetItemProvider = new XSDBoundedFacetItemProvider(this);
    }

    return xsdBoundedFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDCardinalityFacet} instances.
   */
  protected XSDCardinalityFacetItemProvider xsdCardinalityFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDCardinalityFacet}.
   */
  public Adapter createXSDCardinalityFacetAdapter()
  {
    if (xsdCardinalityFacetItemProvider == null)
    {
      xsdCardinalityFacetItemProvider = new XSDCardinalityFacetItemProvider(this);
    }

    return xsdCardinalityFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDNumericFacet} instances.
   */
  protected XSDNumericFacetItemProvider xsdNumericFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDNumericFacet}.
   */
  public Adapter createXSDNumericFacetAdapter()
  {
    if (xsdNumericFacetItemProvider == null)
    {
      xsdNumericFacetItemProvider = new XSDNumericFacetItemProvider(this);
    }

    return xsdNumericFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDOrderedFacet} instances.
   */
  protected XSDOrderedFacetItemProvider xsdOrderedFacetItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDOrderedFacet}.
   */
  public Adapter createXSDOrderedFacetAdapter()
  {
    if (xsdOrderedFacetItemProvider == null)
    {
      xsdOrderedFacetItemProvider = new XSDOrderedFacetItemProvider(this);
    }

    return xsdOrderedFacetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.xsd.XSDDiagnostic} instances.
   */
  protected XSDDiagnosticItemProvider xsdDiagnosticItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDDiagnostic}.
   */
  public Adapter createXSDDiagnosticAdapter()
  {
    if (xsdDiagnosticItemProvider == null)
    {
      xsdDiagnosticItemProvider = new XSDDiagnosticItemProvider(this);
    }

    return xsdDiagnosticItemProvider;
  }
  /**
   * This returns the root adapter factory that contains this factory.
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory.
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  public boolean isFactoryForType(Object type)
  {
    return super.isFactoryForType(type) || supportedTypes.contains(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter.
   */
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  public Object adapt(Object object, Object type)
  {
    // This is a kludge to deal with enumerators, which crash the doSwitch.
    //
    if (object instanceof EObject && ((EObject)object).eClass() == null)
    {
      return null;
    }

    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class) || (((Class)type).isInstance(adapter)))
      {
        return adapter;
      }
    }

    return null;
  }

  public Adapter adaptNew(Notifier object, Object type)
  {
    Adapter result = super.adaptNew(object, type);
    disposable.add(result);
    return result;
  }

  /**
   * This adds a listener.
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  public void dispose()
  {
    disposable.dispose();
  }
}
