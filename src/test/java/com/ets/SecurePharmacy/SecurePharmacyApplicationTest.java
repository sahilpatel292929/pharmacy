package com.ets.SecurePharmacy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.internal.InheritingConfiguration;
import org.modelmapper.internal.TypeResolvingList;
import org.modelmapper.spi.NameTokenizer;
import org.modelmapper.spi.ValueReader;
import org.modelmapper.spi.ValueWriter;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurePharmacyApplicationTest {
    /**
     * Method under test: {@link SecurePharmacyApplication#productApi()}
     */
    @Test
    void testProductApi() {
        Docket actualProductApiResult = (new SecurePharmacyApplication()).productApi();
        assertTrue(actualProductApiResult.isEnabled());
        assertEquals("default", actualProductApiResult.getGroupName());
    }

    /**
     * Method under test: {@link SecurePharmacyApplication#modelMapper()}
     */
    @Test
    void testModelMapper() {
        ModelMapper actualModelMapperResult = (new SecurePharmacyApplication()).modelMapper();
        assertTrue(actualModelMapperResult.getTypeMaps().isEmpty());
        Configuration configuration = actualModelMapperResult.getConfiguration();
        assertEquals(11, configuration.getConverters().size());
        assertNull(configuration.getPropertyCondition());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertTrue(valueWriters instanceof TypeResolvingList);
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertTrue(valueReaders instanceof TypeResolvingList);
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
    }

    /**
     * Method under test: {@link SecurePharmacyApplication#getRestTemplate()}
     */
    @Test
    void testGetRestTemplate() {
        RestTemplate actualRestTemplate = (new SecurePharmacyApplication()).getRestTemplate();
        assertTrue(actualRestTemplate.getClientHttpRequestInitializers().isEmpty());
        UriTemplateHandler uriTemplateHandler = actualRestTemplate.getUriTemplateHandler();
        assertTrue(uriTemplateHandler instanceof DefaultUriBuilderFactory);
        assertTrue(actualRestTemplate.getRequestFactory() instanceof SimpleClientHttpRequestFactory);
        assertTrue(actualRestTemplate.getErrorHandler() instanceof DefaultResponseErrorHandler);
        List<HttpMessageConverter<?>> messageConverters = actualRestTemplate.getMessageConverters();
        assertEquals(7, messageConverters.size());
        assertTrue(actualRestTemplate.getInterceptors().isEmpty());
        assertTrue(((DefaultUriBuilderFactory) uriTemplateHandler).getDefaultUriVariables().isEmpty());
        assertEquals(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT,
                ((DefaultUriBuilderFactory) uriTemplateHandler).getEncodingMode());
        assertEquals(2, messageConverters.get(1).getSupportedMediaTypes().size());
        HttpMessageConverter<?> getResult = messageConverters.get(4);
        assertEquals(6, ((AllEncompassingFormHttpMessageConverter) getResult).getPartConverters().size());
        assertEquals(3, getResult.getSupportedMediaTypes().size());
        assertTrue(
                ((MappingJackson2XmlHttpMessageConverter) messageConverters.get(5)).getObjectMapper() instanceof XmlMapper);
        ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) messageConverters.get(6)).getObjectMapper();
        assertNull(objectMapper.getPropertyNamingStrategy());
        assertTrue(objectMapper.getPolymorphicTypeValidator() instanceof LaissezFaireSubTypeValidator);
        assertSame(objectMapper.getJsonFactory(), objectMapper.getFactory());
        assertTrue(objectMapper.getDeserializationContext() instanceof DefaultDeserializationContext.Impl);
        assertTrue(objectMapper.getSerializerProviderInstance() instanceof DefaultSerializerProvider.Impl);
        assertTrue(objectMapper.getVisibilityChecker() instanceof VisibilityChecker.Std);
        assertTrue(objectMapper.getSerializerProvider() instanceof DefaultSerializerProvider.Impl);
        assertTrue(objectMapper.getDateFormat() instanceof StdDateFormat);
        assertTrue(objectMapper.getSerializerFactory() instanceof BeanSerializerFactory);
        assertTrue(objectMapper.getSubtypeResolver() instanceof StdSubtypeResolver);
    }
}

