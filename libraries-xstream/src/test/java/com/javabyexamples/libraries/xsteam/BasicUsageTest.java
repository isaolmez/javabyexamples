package com.javabyexamples.libraries.xsteam;

import static org.assertj.core.api.Assertions.assertThat;

import com.thoughtworks.xstream.XStream;
import java.io.InputStream;
import org.junit.Test;

public class BasicUsageTest {

    @Test
    public void shouldConvertToXml() {
        Employee employee = new Employee();
        employee.setName("John");
        employee.setLastname("Doe");
        employee.setPersonal(new PhoneNumber("0221"));
        employee.setWork(new PhoneNumber("0222"));

        XStream xstream = new XStream();
        String xmlRepresentation = xstream.toXML(employee);

        assertThat(xmlRepresentation).isEqualTo("<com.javabyexamples.libraries.xsteam.Employee>\n"
                + "  <name>John</name>\n"
                + "  <lastname>Doe</lastname>\n"
                + "  <personal>\n"
                + "    <number>0221</number>\n"
                + "  </personal>\n"
                + "  <work>\n"
                + "    <number>0222</number>\n"
                + "  </work>\n"
                + "</com.javabyexamples.libraries.xsteam.Employee>");
    }

    @Test
    public void shouldConvertToXml_WhenAliasIsSet() {
        Employee employee = new Employee();
        employee.setName("John");
        employee.setLastname("Doe");
        employee.setPersonal(new PhoneNumber("0221"));
        employee.setWork(new PhoneNumber("0222"));

        XStream xstream = new XStream();
        xstream.alias("employee", Employee.class);
        String xmlRepresentation = xstream.toXML(employee);

        assertThat(xmlRepresentation).isEqualTo("<employee>\n"
                + "  <name>John</name>\n"
                + "  <lastname>Doe</lastname>\n"
                + "  <personal>\n"
                + "    <number>0221</number>\n"
                + "  </personal>\n"
                + "  <work>\n"
                + "    <number>0222</number>\n"
                + "  </work>\n"
                + "</employee>");
    }

    @Test
    public void shouldConvertFromXml() {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("samples/person.xml");

        XStream xstream = new XStream();
        xstream.alias("employee", Employee.class);
        Employee employee = (Employee) xstream.fromXML(resourceAsStream);

        assertThat(employee.getName()).isEqualTo("John");
        assertThat(employee.getLastname()).isEqualTo("Doe");
        assertThat(employee.getPersonal().getNumber()).isEqualTo("0221");
        assertThat(employee.getWork().getNumber()).isEqualTo("0222");
    }
}