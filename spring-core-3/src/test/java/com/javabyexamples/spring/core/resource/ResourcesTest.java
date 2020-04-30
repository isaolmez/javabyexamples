package com.javabyexamples.spring.core.resource;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.MalformedURLException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.UrlResource;

public class ResourcesTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void givenUrlResource_HandlesHttp() throws MalformedURLException {
        final UrlResource urlResource = new UrlResource("http://www.javabyexamples.com");

        assertThat(urlResource.exists()).isTrue();
        assertThat(urlResource.isFile()).isFalse();
        assertThat(urlResource.isReadable()).isTrue();
        assertThat(urlResource.isOpen()).isFalse();
    }

    @Test
    public void givenUrlResource_HandlesFile() throws Exception {
        final File file = temporaryFolder.newFile("test1");
        final UrlResource urlResource = new UrlResource("file://" + file.getAbsolutePath());

        assertThat(urlResource.exists()).isTrue();
        assertThat(urlResource.isFile()).isTrue();
        assertThat(urlResource.isReadable()).isTrue();
        assertThat(urlResource.isOpen()).isFalse();
    }

    @Test(expected = MalformedURLException.class)
    public void givenUrlResource_ErrorsWithoutProtocol() throws MalformedURLException {
        new UrlResource("/tmp/test.txt");
    }

    @Test
    public void givenClassPathResource_HandlesFile() throws MalformedURLException {
        final ClassPathResource classPathResource = new ClassPathResource("resource/test-rules.txt");

        assertThat(classPathResource.exists()).isTrue();
        assertThat(classPathResource.isFile()).isTrue();
        assertThat(classPathResource.isReadable()).isTrue();
        assertThat(classPathResource.isOpen()).isFalse();
    }

    @Test
    public void givenClassPathResource_NotHandles_WithPrefix() throws MalformedURLException {
        final ClassPathResource classPathResource = new ClassPathResource("classpath:resource/test-rules.txt");

        assertThat(classPathResource.exists()).isFalse();
    }

    @Test
    public void givenFileSystemResource_HandlesFile() throws Exception {
        final File temp1 = temporaryFolder.newFile("temp1");
        System.out.println(temp1.getAbsolutePath());
        final FileSystemResource fileSystemResource = new FileSystemResource(temp1.getAbsolutePath());

        assertThat(fileSystemResource.exists()).isTrue();
        assertThat(fileSystemResource.isFile()).isTrue();
        assertThat(fileSystemResource.isReadable()).isTrue();
        assertThat(fileSystemResource.isOpen()).isFalse();
    }

    @Test
    public void givenFileSystemResource_NotHandles_WithPrefix() throws Exception {
        final File temp1 = temporaryFolder.newFile("temp1");
        final FileSystemResource fileSystemResource = new FileSystemResource("file://" + temp1.getAbsolutePath());

        assertThat(fileSystemResource.exists()).isFalse();
    }

    @Test
    public void givenFileUrlResource_HandlesFile() throws Exception {
        final File temp1 = temporaryFolder.newFile("temp1");
        System.out.println(temp1.getAbsolutePath());
        final FileUrlResource fileUrlResource = new FileUrlResource(temp1.getAbsolutePath());

        assertThat(fileUrlResource.exists()).isTrue();
        assertThat(fileUrlResource.isFile()).isTrue();
        assertThat(fileUrlResource.isReadable()).isTrue();
        assertThat(fileUrlResource.isOpen()).isFalse();
    }

}
