package com.javabyexamples.java.mapper.orika.optional;

import java.util.Optional;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

public class OptionalConverter<S, D> implements Converter<Optional<S>, Optional<D>> {

    private final Type<S> sourceType;
    private final Type<D> destinationType;
    private MapperFacade mapper;

    public OptionalConverter(final Type<S> sourceType, final Type<D> destinationType) {
        this.sourceType = sourceType;
        this.destinationType = destinationType;
    }

    public boolean canConvert(final Type<?> sourceType, final Type<?> destinationType) {
        final Type<?> sourceComponentType = sourceType.getComponentType();
        final Type<?> destinationComponentType = destinationType.getComponentType();

        return !(sourceComponentType == null || destinationComponentType == null)
          && this.sourceType.isAssignableFrom(sourceComponentType.getRawType())
          && this.destinationType.isAssignableFrom(destinationComponentType.getRawType());
    }

    public Optional<D> convert(final Optional<S> optionalSource, final Type<? extends Optional<D>> destinationType,
      final MappingContext mappingContext) {
        if (!optionalSource.isPresent()) {
            return Optional.empty();
        }

        final S source = optionalSource.get();

        return Optional.ofNullable(mapper.map(source, sourceType, this.destinationType, mappingContext));
    }

    public void setMapperFacade(final MapperFacade mapper) {
        this.mapper = mapper;
    }

    public Type<Optional<S>> getAType() {
        return getOptionalTypeOf(sourceType);
    }

    public Type<Optional<D>> getBType() {
        return getOptionalTypeOf(destinationType);
    }

    @SuppressWarnings("unchecked")
    private <T> Type<Optional<T>> getOptionalTypeOf(Type<T> type) {
        return (Type) TypeFactory.valueOf(Optional.class, type);
    }
}
