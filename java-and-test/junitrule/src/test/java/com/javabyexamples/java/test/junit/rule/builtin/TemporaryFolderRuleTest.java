package com.javabyexamples.java.test.junit.rule.builtin;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TemporaryFolderRuleTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void shouldCreateNewFile() throws IOException {
        File file = temporaryFolder.newFile();

        assertThat(file.isFile()).isTrue();
        assertThat(file.isDirectory()).isFalse();
    }

    @Test
    public void shouldCreateNewFileWithGivenName() throws IOException {
        String fileName = "test.txt";
        File file = temporaryFolder.newFile(fileName);

        assertThat(file.getName()).isEqualTo(fileName);
        assertThat(file.isFile()).isTrue();
        assertThat(file.isDirectory()).isFalse();
    }

    @Test
    public void shouldCreateNewFolder() throws IOException {
        File folder = temporaryFolder.newFolder();

        assertThat(folder.isFile()).isFalse();
        assertThat(folder.isDirectory()).isTrue();
    }

    @Test
    public void shouldCreateNewFolderWithGivenName() throws IOException {
        String folderName = "test";

        File folder = temporaryFolder.newFolder(folderName);

        assertThat(folder.getName()).isEqualTo(folderName);
        assertThat(folder.isFile()).isFalse();
        assertThat(folder.isDirectory()).isTrue();
    }

}
