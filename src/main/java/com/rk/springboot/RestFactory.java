package com.rk.springboot;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by srk on 26/10/17.
 */
@Component
public class RestFactory implements BeanFactoryPostProcessor, EmbeddedValueResolverAware {

    StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.resolver = resolver;
    }

    private String basePackage = "com.rk.springboot";

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        createBeanProxy(beanFactory, SpringRestService123.class);
    }


    private void createBeanProxy(ConfigurableListableBeanFactory beanFactory,Class<? extends Annotation> annotation) {
        Class<?> classe;
        Annotation[] annotations;
        try {
            setBasePackage(basePackage);
            annotations = AnnotationUtils.getAnnotations(annotation);
        } catch (Exception e) {
            throw new BeanInstantiationException(annotation, e.getMessage(), e);
        }
        /*BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        for (Annotation typeService : annotations) {
            //Annotation typeService = classType.getAnnotation(annotation);
            GenericBeanDefinition beanDef = new GenericBeanDefinition();
            beanDef.setBeanClass(getQueryServiceFactory(typeService));
            ConstructorArgumentValues cav = new ConstructorArgumentValues();
            cav.addIndexedArgumentValue(0, classType);
            cav.addIndexedArgumentValue(1, baseUri(classType,typeService));
            beanDef.setConstructorArgumentValues(cav);
            registry.registerBeanDefinition(classType.getName() + "Proxy", beanDef);
        }*/
    }

    private String baseUri(Annotation typeService){
        String baseUri = null;
        if(typeService instanceof SpringRestService123){
            baseUri = ((SpringRestService123)typeService).baseUri();
        }/*else if(typeService instanceof JaxrsRestService){
            baseUri = ((JaxrsRestService)typeService).baseUri();
        }*/
        if(baseUri!=null && !baseUri.isEmpty()){
            return baseUri = resolver.resolveStringValue(baseUri);
        }else{
            throw new IllegalStateException("Impossibile individuare una baseUri per l'interface :"+typeService);
        }
    }

    private static Class<? extends FactoryBean<?>> getQueryServiceFactory(Annotation typeService){
        if(typeService instanceof SpringRestService123){
            return com.github.ggeorgovassilis.springjsonmapper.spring.SpringRestInvokerProxyFactoryBean.class;
        }/*else if(typeService instanceof JaxrsRestService){
            return it.eng.rete2i.springjsonmapper.jaxrs.JaxRsInvokerProxyFactoryBean.class;
        }*/
        throw new IllegalStateException("Impossibile individuare una classe per l'interface :"+typeService);
    }
}
