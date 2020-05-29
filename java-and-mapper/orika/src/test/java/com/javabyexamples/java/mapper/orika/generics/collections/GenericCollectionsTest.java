package com.javabyexamples.java.mapper.orika.generics.collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.junit.jupiter.api.Test;

public class GenericCollectionsTest {

    @Test
    public void testParameterizedCollection_UsingRawTypes() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Group.class, GroupDto.class).byDefault().register();

        Person person = new Person();
        person.setName("Name");
        Group<Person> group = new Group<>();
        group.setMembers(Arrays.asList(person));

        final GroupDto<PersonDto> groupDto = factory.getMapperFacade().map(group, GroupDto.class);

        assertThat(groupDto.getMembers().get(0)).isNotInstanceOfAny(Person.class, PersonDto.class);
        try {
            final PersonDto personDto = groupDto.getMembers().get(0); // Throws ClassCastException
        } catch (Exception e) {
            e.printStackTrace();
            assertThat(e).isInstanceOf(ClassCastException.class);
        }
    }

    @Test
    public void testParameterizedCollection_UsingGenericTypes() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        Type<Group<Person>> sourceType = new TypeBuilder<Group<Person>>() {
        }.build();
        Type<GroupDto<PersonDto>> targetType = new TypeBuilder<GroupDto<PersonDto>>() {
        }.build();
        factory.classMap(sourceType, targetType).byDefault().register();

        Person person = new Person();
        person.setName("Name");
        Group<Person> group = new Group<>();
        group.setMembers(Arrays.asList(person));

        GroupDto<PersonDto> groupDto = factory.getMapperFacade().map(group, sourceType, targetType);

        assertThat(groupDto.getMembers().get(0)).isInstanceOf(PersonDto.class);
    }

    @Test
    public void testParameterizedCollection() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Inventory.class, InventoryDto.class).byDefault().register();

        final Inventory inventory = new Inventory();
        final HashMap<String, Object> map = new HashMap<>();
        map.put("numbers", Arrays.asList("1", "2", "3"));
        inventory.setData(map);

        final InventoryDto inventoryDto = factory.getMapperFacade().map(inventory, InventoryDto.class);

        assertThat(inventoryDto.getData().get("numbers")).isNotInstanceOf(List.class);
    }

    @Test
    public void testParameterizedCollection2() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Inventory.class, InventoryDto.class).byDefault().register();

        final Inventory inventory = new Inventory();
        final HashMap<String, Object> map = new HashMap<>();
        map.put("numbers", 1);
        inventory.setData(map);

        final InventoryDto inventoryDto = factory.getMapperFacade().map(inventory, InventoryDto.class);

        assertThat(inventoryDto.getData().get("numbers")).isInstanceOf(Integer.class);
    }

    @Test
    public void testParameterizedCollection_UsingCustomMapper() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Inventory.class, InventoryDto.class)
          .customize(new CustomMapper<Inventory, InventoryDto>() {
              @Override
              public void mapAtoB(Inventory inventory, InventoryDto inventoryDto, MappingContext context) {
                  inventoryDto.setData(inventory.getData());
              }
          })
          .byDefault().register();

        final Inventory inventory = new Inventory();
        final HashMap<String, Object> map = new HashMap<>();
        map.put("numbers", Arrays.asList("1", "2", "3"));
        inventory.setData(map);

        final InventoryDto inventoryDto = factory.getMapperFacade().map(inventory, InventoryDto.class);

        assertThat(inventoryDto.getData().get("numbers")).isInstanceOf(List.class);
    }

    @Test
    public void testCollection_UsingRawTypes() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(PersonGroup.class, PersonGroupDto.class).byDefault().register();

        Person person = new Person();
        person.setName("Name");
        PersonGroup group = new PersonGroup();
        group.setMembers(Arrays.asList(person));

        final PersonGroupDto personGroupDto = factory.getMapperFacade().map(group, PersonGroupDto.class);

        assertThat(personGroupDto.getMembers().get(0)).isInstanceOf(PersonDto.class);
        assertThat(personGroupDto.getMembers().get(0).getName()).isEqualTo(group.getMembers().get(0).getName());
    }
}
