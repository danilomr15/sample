package br.com.danilomr.java11.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Java11NewFeaturesTest {

    @Test
    @DisplayName("New String Methods")
    /**
     * lines() - quebra automaticamente o \n em linhas;
     * strip - funciona como o trim, porém remove todos os caracteres unicode que geram espaços em branco
     */
    public void test1() {
        String multilineString = "Baeldung helps \n \n developers \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
        assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");
    }

    @Test
    @DisplayName("New File Methods")
    /**
     * Files.writeString - Facilita a criação de um arquivo de texto
     * Files.readString - Facilita a leitura de um arquivo de texto
     */
    public void test2() throws IOException {
        final Path tempDir = Files.createTempDirectory("tmp").toAbsolutePath();
        Path filePath = Files.writeString(Files.createTempFile(tempDir, "demo", ".txt"), "Sample text");
        String fileContent = Files.readString(filePath);
        assertThat(fileContent).isEqualTo("Sample text");
    }

    @Test
    @DisplayName("Collection to Array")
    /**
     * toArray - Novo método da interface Collection que transforma facilmente uma collection em um array
     */
    public void test3() {
        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String[] sampleArray = sampleList.toArray(String[]::new);
        assertThat(sampleArray).containsExactly("Java", "Kotlin");
    }

    @Test
    @DisplayName("The Not Predicate Method")
    /**
     * Predicate.not - Novo método que valida uma negação de um equals (!equals)
     */
    public void test4() {
        List<String> sampleList = Arrays.asList("Java", "\n \n", "Kotlin", " ");
        List withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        assertThat(withoutBlanks).containsExactly("Java", "Kotlin");
    }

    @Test
    @DisplayName("Local-Variable Syntax for Lambda")
    public void test5() {
        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String resultString = sampleList.stream()
                .map((@Nonnull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        assertThat(resultString).isEqualTo("JAVA, KOTLIN");
    }


}
